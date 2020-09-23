package com.mydemo.demo.login.controller;

import com.mydemo.demo.common.entity.Message;
import com.mydemo.demo.common.enums.Errcode;
import com.mydemo.demo.login.entity.PO.Login;
import com.mydemo.demo.login.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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

    @PostMapping("/login")
    public Message doLogin(Login login){
        log.info("进入登录处理");
        log.info("login:"+login);
        return  loginService.login(login);
    }
    @GetMapping("/aaa")
    public Message aaa(){
        log.info("这是个页面");
        return new Message().rest(Errcode.E_9999);
    }

}
