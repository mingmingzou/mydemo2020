package com.mydemo.demo.modules.sys.login.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mydemo.demo.common.entity.Message;
import com.mydemo.demo.config.DemoConfig;
import com.mydemo.demo.config.RedisKeys;
import com.mydemo.demo.config.RedisUtil;
import com.mydemo.demo.config.shiro.JwtUtil;
import com.mydemo.demo.modules.sys.login.entity.DTO.LoginDTO;
import com.mydemo.demo.modules.sys.login.entity.PO.Login;
import com.mydemo.demo.modules.sys.login.mapper.LoginMapper;
import com.mydemo.demo.modules.sys.login.service.LoginService;
import com.mydemo.demo.modules.sys.user.entity.User;
import com.mydemo.demo.modules.sys.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Service
@Slf4j
public class LoginServiceImpl implements LoginService {


    @Autowired
    RedisUtil redisUtil;

    @Autowired
    UserService userService;

    @Autowired
    LoginMapper loginMapper;

    @Override
    public LoginDTO getUser(String username) {
        LoginDTO user = loginMapper.login(username);
        return user;
    }

    @Override
    public Message login(Login login ,HttpServletResponse response){
//        UsernamePasswordToken token = new UsernamePasswordToken(controller.getUsername(),
//                new SimpleHash(BaseConfig.NAMEMD5, controller.getPassword(), BaseConfig.SALTMD5, BaseConfig.TIMESMD5).toString());
        Object ret = new SimpleHash(DemoConfig.ENCRYPT_TYPE, login.getPassword(), DemoConfig.SALT, DemoConfig.ENCRYPT_TIMES);

        User user = userService.login(login.getUsername(),ret.toString());
        if(user == null){
               return new Message().error("账号或密码错误 登录失败");
         }

        //生成token，将refresh_token放入redis
        String currentTimeStamp = String.valueOf(System.currentTimeMillis());
        String token = JwtUtil.sign(user.getUsername(), currentTimeStamp);
        redisUtil.set(RedisKeys.PREFIX_SHIRO_REFRESH_TOKEN + user.getUsername(), currentTimeStamp, DemoConfig.JWT_REFRESH_TOKEN_EXPIRE_TIME
        , TimeUnit.SECONDS);

        //将token输出到header
        response.setHeader("Authorization", token);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        Map<String, String> map = new HashMap<>();
        map.put("access_token", token);

        return new Message().ok(map);
    }
}
