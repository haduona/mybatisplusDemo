package com.zth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zth.entity.People;
import com.zth.mapper.PeopleMapper;
import com.zth.service.IPeopleService;
import org.springframework.stereotype.Service;

@Service
public class PeopleServiceImpl extends ServiceImpl<PeopleMapper, People> implements IPeopleService {
}
