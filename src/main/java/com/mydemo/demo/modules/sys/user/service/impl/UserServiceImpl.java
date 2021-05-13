package com.mydemo.demo.modules.sys.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mydemo.demo.modules.sys.user.entity.User;
import com.mydemo.demo.modules.sys.user.mapper.UserMapper;
import com.mydemo.demo.modules.sys.user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zmm
 * @since 2021-04-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User login(String username,String password) {

        QueryWrapper<User> wrapper = new QueryWrapper<User>()
                .eq("username",username)
                .eq("password",password);
        return this.getOne(wrapper);
    }
}
