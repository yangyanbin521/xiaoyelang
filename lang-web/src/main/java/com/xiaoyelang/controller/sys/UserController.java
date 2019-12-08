package com.xiaoyelang.controller.sys;

import com.xiaoyelang.base.PageResult;
import com.xiaoyelang.model.User;
import com.xiaoyelang.service.LoginService;
import com.xiaoyelang.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName UserController
 * @Author 杨彦斌
 * @Date 2019/8/18 17:08
 *
 *
 * 说明 :
 * UsernamePasswordToken参数说明
 *  String username;  //用户
 *  char[] password;  //密码
 *  boolean rememberMe; //记住密码
 *  String host;  //主机
 */
@Controller
@RequestMapping("/login")
public class UserController {
     @Autowired
     private UserService userService;
    @Autowired
    private LoginService loginService;
    //登录接口
    @PostMapping("/login")
    public String login( String userName ,  String password , Model model){
        /**
         * 使用shiro编写认证操作
         */
        //1.获取Subject
        Subject subject = SecurityUtils.getSubject();
        //2.用户名/密码的认证机制 (UsernamePasswordToken参数说明 ctrl +f 进行查看说明 在类名那个地方)
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        try {
            //3.执行登录方法
            subject.login(token);
            //获取用户信息的方法
            User user = (User)subject.getPrincipal();
            System.out.println("用户信息"+user.toString());
            //登陆成功
            //跳转到登录成功页面
            return  "redirect:/toUrl/loginSuccess";
        } catch (UnknownAccountException e) {
            //用户不存在
            model.addAttribute("msg","用户不存在");
            return  "user/login";
        } catch (AuthenticationException e) {
            //用户不存在
            model.addAttribute("msg","用户或者密码错误");
            return  "user/login";
        }


    }

    @RequestMapping("/toRegister")
    public String toRegister(){
        return "user/register";
    }

    /**
     * 注冊
     * @param user
     */
    @PostMapping("/register")
    public String register(User user){
        loginService.register(user);
        return  "user/login";
    }


    @RequestMapping("/userList")
    @ResponseBody
    public PageResult aa(){

       List<User> users = userService.findUser();
        PageResult a = new PageResult();
        a.setRows(users);
        return a;
    }


}
