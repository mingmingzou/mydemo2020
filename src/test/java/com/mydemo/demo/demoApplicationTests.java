package com.mydemo.demo;

import com.mydemo.demo.login.entity.PO.Login;
import com.mydemo.demo.login.service.LoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class demoApplicationTests {

    @Autowired
    LoginService loginService;
    @Test
    void contextLoads() {
        Login login = new Login();
        try {
            int login1 = loginService.login(login);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
