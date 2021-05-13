package com.mydemo.demo.modules.sys.role.service.impl;

import com.mydemo.demo.modules.sys.role.entity.Role;
import com.mydemo.demo.modules.sys.role.mapper.RoleMapper;
import com.mydemo.demo.modules.sys.role.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.hutool.core.util.StrUtil;
import java.util.*;
import com.baomidou.mybatisplus.core.metadata.IPage;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zmm
 * @since 2021-05-06
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {


}
