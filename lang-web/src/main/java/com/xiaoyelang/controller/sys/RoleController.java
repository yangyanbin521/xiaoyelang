package com.xiaoyelang.controller.sys;

import com.xiaoyelang.model.User;
import com.xiaoyelang.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName UserController
 * @Author 杨彦斌
 * @Date 2019/8/18 17:08
 */
@Controller
@RequestMapping("/role")
public class RoleController {
     @Autowired
     private UserService userService;

}
