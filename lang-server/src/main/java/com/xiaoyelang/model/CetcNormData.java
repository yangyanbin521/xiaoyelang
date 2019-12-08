package com.xiaoyelang.model;

/**
 * @ClassName CetcNormData
 * @Author 杨彦斌
 * @Date 2019/10/20 21:15
 */

import lombok.Data;

@Data
public class CetcNormData {
    /**
     * 主键
     */
    private Integer tid;
    /**
     * 指标数据抬头id
     */
    private Integer NormDataHeadId;
    /**
     * 指标id
     */
    private Integer NormId;
    /**
     * 指标值
     */
    private String normValue;
}
