package com.mydemo.demo.login.controller;


import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
@RequestMapping("/static/user")
public class UserController {

    @RequiresPermissions("user:list")//这个是配置是否有该权限的，如果是按上面的写法，这个是有权限的
    @RequestMapping(value="/list",method= RequestMethod.GET)
    public String getList(){
        log.info("进入用户列表");
        return "user/list";
    }
    @RequiresPermissions(value={"user:add"})//这个是没有权限的
    @RequestMapping(value="/add",method=RequestMethod.GET)
    public String getAdd(){
        log.info("进入新增用户界面");
        return "user/add";
    }
}
