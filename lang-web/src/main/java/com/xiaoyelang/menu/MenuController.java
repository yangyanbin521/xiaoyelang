package com.xiaoyelang.menu;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName menuController
 * @Author 杨彦斌
 * @Date 2019/9/20 17:07
 */
@Controller
public class MenuController {
    /**
     * 跳转页面的方法
     * @param url 比如:
     *            unAuth :未授权页面
     *            add    :用户添加页面
     *            update :用户修改页面
     *            loginSuccess : 用户登录成功页面
     * @return
     */
    @GetMapping("/toUrl/{url}")
    public String tologin( @PathVariable("url") String url){
        return  "/user/"+url;
    }
    //跳转到add页面
    @GetMapping("/add")
    @RequiresPermissions("user:add")
    public String add(){
        return "/user/add";
    }
    //跳转到update页面

    @RequiresPermissions("user:update")
    @GetMapping("/update")
    public String update(){
        return "/user/update";
    }

    @GetMapping("/showUser")
    public String showUser(){
        return "/user/showUser";
    }
}
