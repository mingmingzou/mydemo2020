package com.mydemo.demo.login.service.Impl;

import com.mydemo.demo.common.entity.Message;
import com.mydemo.demo.common.enums.Errcode;
import com.mydemo.demo.config.BaseConfig;
import com.mydemo.demo.login.entity.DTO.LoginDTO;
import com.mydemo.demo.login.entity.PO.Login;
import com.mydemo.demo.login.mapper.LoginMapper;
import com.mydemo.demo.login.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
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
    public LoginDTO getUser(String username) {
        LoginDTO user = loginMapper.login(username);
        return user;
    }

    @Override
    public Message login(Login login){
        UsernamePasswordToken token = new UsernamePasswordToken(login.getUsername(),
                new SimpleHash(BaseConfig.NAMEMD5, login.getPassword(), BaseConfig.SALTMD5, BaseConfig.TIMESMD5).toString());
        try {
            SecurityUtils.getSubject().login(token);
        }catch (UnknownAccountException e) {
            return new Message().rest(Errcode.U_3006);
        } catch (CredentialsException e) {
            return new Message().rest(Errcode.U_3001);
        } catch (LockedAccountException e) {
            return new Message().error(e.getMessage());
        } catch (DisabledAccountException e) {
            return new Message().error(e.getMessage());
        }  catch (ExcessiveAttemptsException e) {
            return new Message().error(e.getMessage());
        } catch (Exception e) {
            log.info("用户认证失败：{}", e.getMessage());
            return new Message().error("用户认证失败");
        }

        return new Message().ok();
    }
}
