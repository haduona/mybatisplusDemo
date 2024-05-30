package com.zth.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zth.entity.People;
import com.zth.mapper.PeopleMapper;
import com.zth.service.IPeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private IPeopleService peopleService;

    @RequestMapping("/getPeoples")
    public JSONObject getPeople(int i) {

        JSONObject jsonObject = new JSONObject();
        Page<People> page = new Page<>(i, 10);
        IPage<People> peopleIPage = peopleService.page(page);
        List<People> records = peopleIPage.getRecords();
        jsonObject.put("data", records);
        return jsonObject;

    }

}
