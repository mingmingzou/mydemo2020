package com.mydemo.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.web.subject.WebSubject;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/*
* Realm的配置
* author:zmm
* */
@Slf4j
public class MyShiroRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("开始授权(doGetAuthorizationInfo)");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        HttpServletRequest request = (HttpServletRequest) ((WebSubject) SecurityUtils
                .getSubject()).getServletRequest();//这个可以用来获取在登录的时候提交的其他额外的参数信息
        String username = (String) principalCollection.getPrimaryPrincipal();//这里是写的demo，后面在实际项目中要通过这个登录的账号去获取用户的角色和权限，这里直接是写死的
        //受理权限
        //角色
        Set<String> roles = new HashSet<String>();
        roles.add("role1");
        authorizationInfo.setRoles(roles);
        //权限
        Set<String> permissions = new HashSet<String>();
        permissions.add("user:list");
        //permissions.add("user:add");
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;

    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("开始认证(doGetAuthenticationInfo)");
        //UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        HttpServletRequest request = (HttpServletRequest) ((WebSubject) SecurityUtils
                .getSubject()).getServletRequest();
        UsernamePasswordToken token = new UsernamePasswordToken(request.getParameter("userName"), request.getParameter("password"));
        //获取用户输入的账号
        String userName = (String) token.getPrincipal();
        //通过userName去数据库中匹配用户信息，通过查询用户的情况做下面的处理
        //这里暂时就直接写死,根据登录用户账号的情况做处理
        log.info("账号：" + userName);
        if ("passwordError".equals(userName)) {//密码错误
            throw new IncorrectCredentialsException();
        } else if ("lockAccount".equals(userName)) {// 用户锁定
            throw new LockedAccountException();
        } else {
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                    userName, //用户名
                    "123456", //密码，写死
                    ByteSource.Util.bytes(userName + "salt"),//salt=username+salt
                    getName()  //realm name
            );
            return authenticationInfo;
        }
    }
}
