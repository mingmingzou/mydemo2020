package com.mydemo.demo.login.controller;

import com.mydemo.demo.login.entity.PO.Login;
import com.mydemo.demo.login.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/login")
@Slf4j
public class LoginController {

    @Autowired
    LoginService loginService;


    @RequestMapping("/tologin")
    @ResponseBody
    public int giveDuanxin(Login login){
        int i = 0;
        try {

            i = loginService.login(login);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }



}
