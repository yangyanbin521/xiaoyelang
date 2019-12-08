package com.xiaoyelang.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Filter;

/**
 * @ClassName ShiroConfig
 * @Author 杨彦斌
 * @Date 2019/9/17 22:05
 */
@Configuration
public class ShiroConfig {
    /**
     * 创建 SiroFilterFactoryBean
     * * Filter Chain定义说明
     * * 1、一个URL可以配置多个Filter，使用逗号分隔
     * * 2、当设置多个过滤器时，全部验证通过，才视为通过
     * * 3、部分过滤器可指定参数，如perms，roles
     */
    @Bean
    //@Qualifier("securityManager")  与注入@Bean("securityManager") 名称一致
    public ShiroFilterFactoryBean getSiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //添加shiro内置过滤器
        /**
         * shiro 内置过滤器 可以实现权限的相关拦截
         *   常用过滤器:
         *       anon:  无需认证(登录)可以访问
         *       authc: 必须认证才可以访问
         *       user:  如果使用remeberMe功能可以直接访问
         *       perms: 该资源必须资源权限才可以访问
         *       role:  该资源必须获得角色权限才可以访问
         */
        // Shiro连接约束配置，即过滤链的定义
        Map<String, Filter> filters = new LinkedHashMap();
        LinkedHashMap<String, String> filter = new LinkedHashMap();

        // 对静态资源设置匿名访问(不需要认证的资源) 过滤
        filter.put("/favicon.ico**", "anon");
        filter.put("/ruoyi.png**", "anon");
        filter.put("/css/**", "anon");
        filter.put("/docs/**", "anon");
        filter.put("/fonts/**", "anon");
        filter.put("/img/**", "anon");
        filter.put("/ajax/**", "anon");
        filter.put("/js/**", "anon");
        filter.put("/login/**", "anon");
        filter.put("/druid/**", "anon");
        filter.put("/captcha/captchaImage**", "anon");
        filter.put("/testhtml", "anon");

        //授权过滤器
        //注意: 授权拦截后,shiro会自动跳转到未授权页面
        filter.put("/toUrl/addUser", "perms[user:add]");

        // 所有请求需要认证 过滤
        filter.put("/**", "authc");

        //过滤器
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filter);
        //修改未登录跳转的登录页面
        shiroFilterFactoryBean.setLoginUrl("/toUrl/login");
        //设置未授权页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/toUrl/unAuth");
        return shiroFilterFactoryBean;
    }

    /**
     * 创建 DefultWebSecurityManager
     */
    //@Qualifier("UserRealm")  通过这个注解 找到下面的UserRealm
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联Realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     * 创建 Realm
     */
    @Bean("userRealm") //注解:将方法返回的对象放入spring
    public UserRealm getRealm(HashedCredentialsMatcher matcher) {
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(matcher);
        return userRealm;
    }

    /**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     * ）
     *
     * @return
     */
    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(1024);//散列的次数，比如散列两次，相当于 md5(md5(""));
        return hashedCredentialsMatcher;
    }


}

