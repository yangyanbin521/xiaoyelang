package com.xiaoyelang.vo;

import lombok.Data;

/**
 * @ClassName NormDataHaedExcelVo
 * @Author 杨彦斌
 * @Date 2019/10/20 21:26
 */
@Data
public class NormDataHaedExcelVo {
    /**
     *年份
     */
    private Integer tyear;
    /**
     *月份
     */
    private Integer tmonth;
    /**
     *型号
     */
    private String model;
    /**
     *产品领域
     */
    private String productField;
    /**
     *备注
     */
    private String remark;

    /**
     *年份
     */
    private Integer id;
}
