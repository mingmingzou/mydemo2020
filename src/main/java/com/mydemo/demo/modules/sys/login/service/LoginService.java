package com.mydemo.demo.modules.sys.login.service;


import com.mydemo.demo.common.entity.Message;
import com.mydemo.demo.modules.sys.login.entity.DTO.LoginDTO;
import com.mydemo.demo.modules.sys.login.entity.PO.Login;

import javax.servlet.http.HttpServletResponse;


public interface LoginService {

   LoginDTO getUser(String  login) ;
   Message login(Login  login, HttpServletResponse response);
}
