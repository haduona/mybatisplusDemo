package com.zth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zth.entity.Admin;
import com.zth.entity.User;
import com.zth.mapper.AdminMapper;
import com.zth.mapper.UserMapper;
import com.zth.service.IAdminService;
import com.zth.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {
}
