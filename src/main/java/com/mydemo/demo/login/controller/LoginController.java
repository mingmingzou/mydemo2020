package com.mydemo.demo.login.controller;

import com.mydemo.demo.common.entity.Message;
import com.mydemo.demo.common.enums.Errcode;
import com.mydemo.demo.login.entity.PO.Login;
import com.mydemo.demo.login.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping(value = "/login")
@Slf4j
public class LoginController {

    @Autowired
    LoginService loginService;


    @RequestMapping(value="/login",method=RequestMethod.GET)
    public Message getLogin(){
        log.info("进入login页面");
        return new Message().rest(Errcode.E_1001);
    }
    @RequestMapping(value="/login",method= RequestMethod.POST)
    public String doLogin(HttpServletRequest req, Map<String, Object> model){
        log.info("进入登录处理");
        String exceptionClassName = (String) req.getAttribute("shiroLoginFailure");
        log.info("exceptionClassName:"+exceptionClassName);
        String error = null;
        if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        }else if(LockedAccountException.class.getName().equals(exceptionClassName)){
            error = "用户已锁定或已删除";
        }else if (exceptionClassName != null) {
            error = "其他错误：" + exceptionClassName;
        }
        if(SecurityUtils.getSubject().isAuthenticated()){//没有错误，但是已经登录了，就直接跳转到welcom页面
            model.put("name", req.getParameter("userName"));
            return "index";
        }else{//有错误的
            model.put("error", error);
            return "login";
        }
    }
    @RequestMapping("/index")
    public String index(){
        return "index";
    }



}
