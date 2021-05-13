package com.mydemo.demo.modules.sys.user.service;

import com.mydemo.demo.modules.sys.user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zmm
 * @since 2021-04-22
 */
public interface UserService extends IService<User> {

    User login(String username,String password);
}
