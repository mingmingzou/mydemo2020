package com.mydemo.demo.login.mapper;

import com.mydemo.demo.login.entity.DTO.LoginDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
    LoginDTO login(String username);
}
