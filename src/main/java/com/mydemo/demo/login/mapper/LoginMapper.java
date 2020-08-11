package com.mydemo.demo.login.mapper;

import com.mydemo.demo.login.entity.PO.Login;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
    Login login();
}
