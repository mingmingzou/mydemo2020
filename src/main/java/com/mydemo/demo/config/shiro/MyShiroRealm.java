package com.mydemo.demo.config.shiro;

import com.mydemo.demo.config.BaseConfig;
import com.mydemo.demo.login.entity.DTO.LoginDTO;
import com.mydemo.demo.login.service.LoginService;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.subject.WebSubject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/*
* Realm的配置
* author:zmm
* */
@Slf4j
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    LoginService loginService;

    public static void main(String[] args) {
        String name = "MD5";
        Object password = "admin";
        Object salt = "zmm";
        int times = 2;

        Object ret = new SimpleHash(name, password, salt, times);
        System.out.println(ret);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("开始授权(doGetAuthorizationInfo)");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        HttpServletRequest request = (HttpServletRequest) ((WebSubject) SecurityUtils
                .getSubject()).getServletRequest();//这个可以用来获取在登录的时候提交的其他额外的参数信息
        String username = (String) principalCollection.getPrimaryPrincipal();//这里是写的demo，后面在实际项目中要通过这个登录的账号去获取用户的角色和权限，这里直接是写死的
//        受理权限
//        角色
        Set<String> roles = new HashSet<String>();
        roles.add("role1");
        authorizationInfo.setRoles(roles);
        //权限
        Set<String> permissions = new HashSet<String>();
        permissions.add("user:add");
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;

    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("开始认证(doGetAuthenticationInfo)");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //获取用户输入的账号
        String userName = token.getUsername();
        //通过userName去数据库中匹配用户信息，通过查询用户的情况做下面的处理
        //这里暂时就直接写死,根据登录用户账号的情况做处理
        log.info("账号：" + userName);
        LoginDTO loginDTO = null;
        try {
            loginDTO = loginService.getUser(userName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(loginDTO == null || "".equals(loginDTO)){
         throw  new UnknownAccountException("账号不存在");
        }
        AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(loginDTO, loginDTO.getPassword(),
                this.getName());
        //放入shiro供credentialsMatcher检验密码
        log.info("检验密码:"+authenticationInfo);
        System.out.println("检验密码:"+authenticationInfo);
        return authenticationInfo;
    }
}
