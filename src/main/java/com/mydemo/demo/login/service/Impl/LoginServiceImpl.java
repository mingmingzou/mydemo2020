package com.mydemo.demo.login.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mydemo.demo.common.entity.Message;
import com.mydemo.demo.common.enums.Errcode;
import com.mydemo.demo.config.BaseConfig;
import com.mydemo.demo.config.DemoConfig;
import com.mydemo.demo.config.RedisKeys;
import com.mydemo.demo.config.RedisUtil;
import com.mydemo.demo.config.shiro.JwtUtil;
import com.mydemo.demo.login.entity.DTO.LoginDTO;
import com.mydemo.demo.login.entity.PO.Login;
import com.mydemo.demo.login.mapper.LoginMapper;
import com.mydemo.demo.login.service.LoginService;
import com.mydemo.demo.modules.sys.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Slf4j
public class LoginServiceImpl implements LoginService {


    @Autowired
    RedisUtil redisUtil;


    @Autowired
    LoginMapper loginMapper;
//    private Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Override
    public LoginDTO getUser(String username) {
        LoginDTO user = loginMapper.login(username);
        return user;
    }

    @Override
    public Message login(Login login ,HttpServletResponse response){
//        UsernamePasswordToken token = new UsernamePasswordToken(login.getUsername(),
//                new SimpleHash(BaseConfig.NAMEMD5, login.getPassword(), BaseConfig.SALTMD5, BaseConfig.TIMESMD5).toString());
        Object ret = new SimpleHash(DemoConfig.ENCRYPT_TYPE, login.getPassword(), DemoConfig.SALT, DemoConfig.ENCRYPT_TIMES);
            LoginDTO user = loginMapper.login(login.getUsername());
        QueryWrapper<User> wrapper = new QueryWrapper<User>()
                .eq("username",login.getPassword())
                .eq("password",ret);
            if(user == null){
                new Message().error("登录失败");
            }


        //生成token，将refresh_token放入redis
        String currentTimeStamp = String.valueOf(System.currentTimeMillis());
        String token = JwtUtil.sign(user.getUsername(), currentTimeStamp);
        redisUtil.set(RedisKeys.PREFIX_SHIRO_REFRESH_TOKEN + user.getUsername(), currentTimeStamp, DemoConfig.JWT_REFRESH_TOKEN_EXPIRE_TIME);

        //将token输出到header
        response.setHeader("Authorization", token);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        Map<String, String> map = new HashMap<>();
        map.put("access_token", token);

        return new Message().ok();
    }
}
