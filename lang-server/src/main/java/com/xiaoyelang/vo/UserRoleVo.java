package com.xiaoyelang.vo;

import com.xiaoyelang.model.Role;
import lombok.Data;

import java.util.List;

/**
 * @ClassName UserRoleVo
 * @Author 杨彦斌
 * @Date 2019/10/18 18:25
 */
@Data
public class UserRoleVo {
    private Integer id;
    private String username;
    private String password;
    private String salt;
    private List<Role> roles;
}
