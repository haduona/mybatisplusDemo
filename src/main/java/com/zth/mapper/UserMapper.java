package com.zth.mapper;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zth.entity.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper
@TableName("user")
public interface UserMapper extends BaseMapper<User> {
}
