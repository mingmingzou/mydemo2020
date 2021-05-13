package com.mydemo.demo.modules.sys.user.mapper;

import com.mydemo.demo.modules.sys.user.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zmm
 * @since 2021-04-22
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
