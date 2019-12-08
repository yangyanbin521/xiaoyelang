package com.xiaoyelang.model;

import lombok.Data;

import java.util.List;

/**
 * @ClassName Role
 * @Author 杨彦斌
 * @Date 2019/9/21 8:35
 */
@Data
public class Role {

    private Integer id;

    private String roleName;

    // 角色可有多个权限、对应role_auth
    private List<Auth> authList;
}
