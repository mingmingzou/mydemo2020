package com.mydemo.demo.login.mapper;

import com.mydemo.demo.login.entity.PO.Login;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LoginMapper {
    List<Login> login();
}
