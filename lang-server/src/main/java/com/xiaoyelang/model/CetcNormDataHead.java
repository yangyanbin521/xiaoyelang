package com.xiaoyelang.model;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName CetcNormDataHead
 * @Author 杨彦斌
 * @Date 2019/10/20 21:04
 */
@Data
public class CetcNormDataHead {
    /**
     * 主键
     */
    private Integer tid;
    /**
     *信息采集表id
     */
    private Integer infoGatherId;
    /**
     *单位表id
     */
    private Integer unitsId;
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
     *创建人
     */
    private String createBy;
    /**
     *创建时间
     */
    private Date createTime;
    /**
     *修改人
     */
    private String modifiedBy;
    /**
     *修改时间
     */
    private Date modifyTime;


}
