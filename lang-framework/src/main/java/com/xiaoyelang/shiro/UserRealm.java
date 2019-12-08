package com.xiaoyelang.shiro;
import com.xiaoyelang.common.exception.user.UserNotExistsException;
import com.xiaoyelang.model.User;
import com.xiaoyelang.service.AuthService;
import com.xiaoyelang.service.LoginService;
import com.xiaoyelang.service.RoleService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName UserRealm
 * @Author 杨彦斌
 * @Date 2019/8/18 9:23
 *
 * 自定义UserRealm 类继承 AuthorizingRealm 类
 */
public class UserRealm extends AuthorizingRealm  {

    @Autowired
    private LoginService loginService;
    @Autowired
    private AuthService authService;
    @Autowired
    private RoleService roleService;

    private static final Logger log = LoggerFactory.getLogger(UserRealm.class);

    /**
     * 执行认证逻辑
     * //在这里进行登录验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        //获取前端 传来的信息
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;

        //前端传来的 用户名
        String username = upToken.getUsername();
        //前端传来的 用户密码
        String password = String.valueOf(upToken.getPassword());

        //取从数据库查询的用户信息对象
        User user = null;

        try { //Ctrl + alt +t 生成 try catch 的快捷键
            user = loginService.login(username, password);
        } catch (UserNotExistsException e) {
            //未知账户异常
            throw new UnknownAccountException();
        } catch (Exception e)
        {
            log.info("对用户[" + username + "]进行登录验证..验证未通过{}", e.getMessage());
            throw new AuthenticationException();
        }

        // 获取盐值，即用户名
        ByteSource salt = ByteSource.Util.bytes(username);

        //进行登录认证

        /*这里第一个参数就是你刚才传入的用户名，第二个参数就是你传入的密码，但是 方法定义中这两个参数都是Object类型，尤其是第一个principal参数，它的意义远远不止用户名那么简单，它是用户的所有认证信息集合，登陆成 功后，<shiro:principal/>标签一旦有property属性，PrincipalTag类也就是标签的支持类，会从 Subject的principalcollection里将principal取出，取出的就是你传入的第一个参数，如果你传了个string类型的用 户名，那么你只能获取用户名。
       仔细看那个this.principals=new SimplePrincipalCollection，这一行，这一行构造了一个新的对象并将引用给了principals，而principals就是principalcollection。*/

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(),salt,getName());
        /*ByteSource bytes = ByteSource.Util.bytes(user.getCredentialsSalt());
        System.out.println("密码"+bytes);*/
        return info;
    }

    /**
     * 执行授权逻辑
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        System.out.println("执行授权逻辑");
        //给资源进行授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //添加资源授权的字符串
        //info.addStringPermission("user:add");
        //获取当前登录用户
        /*User user = (User)SecurityUtils.getSubject().getPrincipal();*/
        User user = (User)principal.getPrimaryPrincipal();

        // 角色列表
        Set<String> roles = new HashSet<String>();
        // 功能列表
        Set<String> menus = new HashSet<String>();
        //根据id 查询角色
        roles = roleService.selectRoleKeys(user.getId());
        //根据id 查询权限
        menus = authService.selectPermsByUserId(user.getId());
        // 角色加入AuthorizationInfo认证对象
        info.setRoles(roles);
        // 权限加入AuthorizationInfo认证对象
        info.setStringPermissions(menus);

        return info;
    }

}
