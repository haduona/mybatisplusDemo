package com.zth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zth.entity.User;
import com.zth.mapper.UserMapper;
import com.zth.service.IUserService;
import com.zth.utils.JWTUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


}
