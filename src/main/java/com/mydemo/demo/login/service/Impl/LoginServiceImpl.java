package com.mydemo.demo.login.service.Impl;

import com.mydemo.demo.login.entity.PO.Login;
import com.mydemo.demo.login.mapper.LoginMapper;
import com.mydemo.demo.login.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginMapper loginMapper;

    @Override
    public int login(Login login) throws Exception {
        log.debug("+++++++++++++++++++++:"+login);
        Login login1 = loginMapper.login();
        if(login1 == null || "".equals(login1))
            return 0;
        return 1;
    }
}
