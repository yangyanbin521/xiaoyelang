package com.xiaoyelang.service.impl;


import com.xiaoyelang.common.exception.user.UserNotExistsException;
import com.xiaoyelang.model.User;
import com.xiaoyelang.service.LoginService;
import com.xiaoyelang.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @ClassName LoginServiceImpl
 * @Author 杨彦斌
 * @Date 2019/9/20 10:53
 */
@Service
public class LoginServiceImpl implements LoginService {
     @Autowired
     private UserService userService;
    //登录验证
    @Override
    public User login(String username, String password) {
// 用户名或密码为空 错误
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password))
        {
            throw new UserNotExistsException();
        }
        User user = userService.findUserByLoginName(username);
        if (user==null)
        {
            throw new UserNotExistsException();
        }
        return user;
    }

    /**
     * 注冊
     * @param user
     */
    @Override
    public void register(User user) {

        userService.register(user);
    }
}
