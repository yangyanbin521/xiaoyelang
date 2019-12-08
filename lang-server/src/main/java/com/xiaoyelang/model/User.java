package com.xiaoyelang.model;

import lombok.Data;

import java.util.List;

/**
 * @ClassName User
 * @Author 杨彦斌
 * @Date 2019/9/19 11:41
 */
@Data
public class User {
    private Integer id;

    private String username;

    private String password;

    private String salt;
    private String aa;
    private String bb;

    // 一个用户有多个角色、对应user_role表
    private List<Role> roleList;

    /**
     * 密码盐.
     *重新对盐重新进行了定义，用户名+salt，这样就更加不容易被破解
     * @return
     */
    public String getCredentialsSalt() {
        return this.username + this.salt;
    }
}
