package com.mydemo.demo.modules.sys.login.mapper;

import com.mydemo.demo.modules.sys.login.entity.DTO.LoginDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper{
    LoginDTO login(String username);
}
