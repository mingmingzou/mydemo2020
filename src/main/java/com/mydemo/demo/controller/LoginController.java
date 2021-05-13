package com.mydemo.demo.controller;

import com.mydemo.demo.common.entity.Message;
import com.mydemo.demo.common.enums.Errcode;
import com.mydemo.demo.modules.sys.login.entity.PO.Login;
import com.mydemo.demo.modules.sys.login.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Api(tags = "登录")
@RestController
@RequestMapping(value = "/login")
@Slf4j
public class LoginController {

    @Autowired
    LoginService loginService;


    @GetMapping("/login2")
    public Message getLogin(){
        log.info("进入login拦截页面");
        return new Message().rest(Errcode.E_1001);
    }

    @GetMapping("/loginsuss")
    public Message loginsuss(){
        log.info("登录成功");
        return new Message().rest(Errcode.E_1001);
    }

    @ApiOperation("登录：密码登录type=pwd，手机验证码登录type=phone")
    @PostMapping("/login")
    public Message doLogin(Login login, HttpServletResponse response){
        log.info("进入登录处理");
        log.info("controller:"+login);
        return  loginService.login(login,response);
    }

    @GetMapping("/aaa")
    public Message aaa(){
        log.info("这是个页面");
        return new Message().rest(Errcode.ok);
    }

}
