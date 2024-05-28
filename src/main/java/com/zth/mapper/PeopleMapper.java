package com.zth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zth.entity.People;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PeopleMapper extends BaseMapper<People> {
}
