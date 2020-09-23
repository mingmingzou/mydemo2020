package com.mydemo.demo.login.service;


import com.mydemo.demo.common.entity.Message;
import com.mydemo.demo.login.entity.DTO.LoginDTO;
import com.mydemo.demo.login.entity.PO.Login;


public interface LoginService {

   LoginDTO getUser(String  login) ;
   Message login(Login  login);
}
