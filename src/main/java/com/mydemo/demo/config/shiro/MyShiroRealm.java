package com.mydemo.demo.config.shiro;

import com.mydemo.demo.common.enums.Errcode;
import com.mydemo.demo.config.RedisKeys;
import com.mydemo.demo.config.RedisUtil;
import com.mydemo.demo.modules.sys.login.entity.DTO.LoginDTO;
import com.mydemo.demo.modules.sys.login.service.LoginService;
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
import org.springframework.context.annotation.Lazy;

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

    /*
    * Lazy 第一次使用时才创建对象
    * */
    @Lazy
    @Autowired
    RedisUtil redisUtil;

    public static void main(String[] args) {
//        String name = "MD5";
//        Object password = "admin";
//        Object salt = "zmm";
//        int times = 2;
//
//        Object ret = new SimpleHash(name, password, salt, times);
//        System.out.println(ret);


//        if (!JwtUtil.verify(token) || !redisUtil.hasKey(RedisKeys.PREFIX_SHIRO_REFRESH_TOKEN + userName)
//                || !JwtUtil.isRefreshExpired(token, redisUtil.get(RedisKeys.PREFIX_SHIRO_REFRESH_TOKEN + userName))

//        redisUtil.set(RedisKeys.PREFIX_SHIRO_REFRESH_TOKEN + "admin","1");
//        System.out.println(redisUtil.get(RedisKeys.PREFIX_SHIRO_REFRESH_TOKEN + "admin"));
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        //这个token就是从过滤器中传入的jwtToken
        return token instanceof JwtToken;
    }

    /*授权*/
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

    /*验证*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("开始认证(doGetAuthenticationInfo)");

        String token = (String) authenticationToken.getPrincipal();
        String userName = JwtUtil.getUsername(token);   //通过token获取用户名

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

        if (!JwtUtil.verify(token) || !redisUtil.hasKey(RedisKeys.PREFIX_SHIRO_REFRESH_TOKEN + userName)
                || !JwtUtil.isRefreshExpired(token, redisUtil.get(RedisKeys.PREFIX_SHIRO_REFRESH_TOKEN + userName))) {
            throw new AuthenticationException(Errcode.E_1006.getErrmsg());
        }
        //登录认证
        SimpleAuthenticationInfo simpleAuthenticationInfo =
                new SimpleAuthenticationInfo(token, token, "jwtRealm");
        //放入shiro供credentialsMatcher检验密码
        log.debug("检验密码2222:"+simpleAuthenticationInfo);
        System.out.println("检验密码:"+simpleAuthenticationInfo);
        return simpleAuthenticationInfo;
    }



}
