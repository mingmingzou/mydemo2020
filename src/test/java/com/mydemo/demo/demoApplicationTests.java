package com.mydemo.demo;

import com.mydemo.demo.config.DemoConfig;
import com.mydemo.demo.config.RedisKeys;
import com.mydemo.demo.config.RedisUtil;
import com.mydemo.demo.modules.sys.login.entity.DTO.LoginDTO;
import com.mydemo.demo.modules.sys.login.entity.PO.Login;
import com.mydemo.demo.modules.sys.login.service.LoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class demoApplicationTests {

    @Autowired
    LoginService loginService;

    @Autowired
    RedisUtil redisUtil;
    @Test
    void contextLoads() {
        Login login =new Login();
        try {
                    redisUtil.set("yayayay","1111111111",100, TimeUnit.SECONDS);
//            System.out.println(redisUtil.get(RedisKeys.PREFIX_SHIRO_REFRESH_TOKEN + "admin"));
            System.out.println("1111111111111111");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
