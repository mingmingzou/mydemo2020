package com.mydemo.demo.login.service.Impl;

import com.mydemo.demo.login.entity.PO.Login;
import com.mydemo.demo.login.mapper.LoginMapper;
import com.mydemo.demo.login.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginMapper loginMapper;
//    private Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Override
    public int login(Login login) throws Exception {
        log.debug("+++++++++++++++++++++:"+login);
        List<Login> login1 = loginMapper.login();
        log.info("login:"+login1.toString());
        if(login1 == null || "".equals(login1))
            return 0;
        return 1;
    }
}
