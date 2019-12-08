package com.xiaoyelang.quartz.controller;

import com.xiaoyelang.common.core.controller.BaseController;
import com.xiaoyelang.common.core.domain.AjaxResult;
import com.xiaoyelang.common.core.page.TableDataInfo;
import com.xiaoyelang.common.exception.job.TaskException;
import com.xiaoyelang.quartz.domain.SysJob;
import com.xiaoyelang.quartz.service.ISysJobService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 调度任务信息操作处理
 *
 * @author ruoyi
 */
@Controller
@RequestMapping("/monitor/job")
public class SysJobController extends BaseController {
    private String prefix = "monitor/job";

    @Autowired
    private ISysJobService jobService;

    @GetMapping()
    public String job() {
        return prefix + "/job";
    }

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysJob job) {
        startPage();
        List<SysJob> list = jobService.selectJobList(job);
        return getDataTable(list);
    }

    /*@RequiresPermissions("monitor:job:remove")*/
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) throws SchedulerException {
        jobService.deleteJobByIds(ids);
        return success();
    }

    /*@RequiresPermissions("monitor:job:detail")*/
    @GetMapping("/detail/{jobId}")
    public String detail(@PathVariable("jobId") Long jobId, ModelMap mmap) {
        mmap.put("name", "job");
        mmap.put("job", jobService.selectJobById(jobId));
        return prefix + "/detail";
    }

    /**
     * 任务调度状态修改
     */
    /*@RequiresPermissions("monitor:job:changeStatus")*/
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(SysJob job) throws SchedulerException {
        SysJob newJob = jobService.selectJobById(job.getJobId());
        newJob.setStatus(job.getStatus());
        return toAjax(jobService.changeStatus(newJob));
    }

    /**
     * 任务调度立即执行一次
     */
    /*@RequiresPermissions("monitor:job:changeStatus")*/
    @PostMapping("/run")
    @ResponseBody
    public AjaxResult run(SysJob job) throws SchedulerException {
        jobService.run(job);
        return success();
    }

    /**
     * 新增调度
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存调度
     */
    @RequiresPermissions("monitor:job:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysJob job) throws SchedulerException, TaskException {
        return toAjax(jobService.insertJob(job));
    }

    /**
     * 修改调度
     */
    @GetMapping("/edit/{jobId}")
    public String edit(@PathVariable("jobId") Long jobId, ModelMap mmap) {
        mmap.put("job", jobService.selectJobById(jobId));
        return prefix + "/edit";
    }

    /**
     * 修改保存调度
     */
    /*@RequiresPermissions("monitor:job:edit")*/
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysJob job) throws SchedulerException, TaskException {
        return toAjax(jobService.updateJob(job));
    }

    /**
     * 校验cron表达式是否有效
     */
    @PostMapping("/checkCronExpressionIsValid")
    @ResponseBody
    public boolean checkCronExpressionIsValid(SysJob job) {
        return jobService.checkCronExpressionIsValid(job.getCronExpression());
    }
}
