package com.xiaoyelang.vo;

/**
 * @ClassName NormDataExcelVo
 * @Author 杨彦斌
 * @Date 2019/10/20 21:19
 */

import com.xiaoyelang.model.CetcNormData;
import lombok.Data;

import java.util.List;

@Data
public class NormDataExcelVo extends NormDataHaedExcelVo{
    /**
     *指标数据List
     */
    private List<CetcNormData> cetcNormDataList;
}
