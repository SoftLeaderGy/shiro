package com.yang.shirospringboot.config;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @Description:
 * @Author: Guo.Yang
 * @Date: 2022/03/09/20:59
 */
public class UserRealm extends AuthorizingRealm {

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了-------授权");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermission("user:add");
        return info;
    }


    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了-------认证");

        // TODO: 2022/3/9  只要是调用登录就会执行

        // 查数据库 拿用户名密码

        String name = "root";
        String pass = "123456";

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        // 验证用户名密码
        if (!token.getUsername().equals(name)) {
            return null; // 返回null 就是抛出异常 UnknownAccountException
        }

        // 密码 shiro做了，安全、高效
        // 获取当前的认证，密码，认证名
        return new SimpleAuthenticationInfo("", pass, "");
    }
}
