package com.zth;

import com.alibaba.fastjson.JSONObject;
import com.zth.entity.User;
import com.zth.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class controller {

    @Autowired
    public UserMapper userMapper;

    @GetMapping("/getUser")
    public JSONObject getUser() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",1);
        jsonObject.put("msg","success");
        List<User> users = userMapper.selectList(null);
        jsonObject.put("data",users);
        return jsonObject;
    }


}
