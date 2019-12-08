package com.xiaoyelang.service;


import com.xiaoyelang.model.User;

import java.util.Set;

/**
 * @ClassName LoginService
 * @Author 杨彦斌
 * @Date 2019/9/20 10:52
 */
public interface AuthService {
    Set<String> selectPermsByUserId(Integer id);
}
