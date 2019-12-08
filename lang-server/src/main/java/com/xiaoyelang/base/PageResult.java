package com.xiaoyelang.base;

import lombok.Data;

import java.util.List;

/**
 * @ClassName PageResult
 * @Author 杨彦斌
 * @Date 2019/10/17 18:00
 */
@Data
public class PageResult {
    private Integer total;
    private List rows;
}
