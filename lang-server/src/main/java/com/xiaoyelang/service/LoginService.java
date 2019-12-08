package com.xiaoyelang.service;


import com.xiaoyelang.model.User;

/**
 * @ClassName LoginService
 * @Author 杨彦斌
 * @Date 2019/9/20 10:52
 *登录验证 ,注册
 * 此接口对于登录验证操作的接口
 */
public interface LoginService {
    User login(String username, String password);
    void register(User user);
}
