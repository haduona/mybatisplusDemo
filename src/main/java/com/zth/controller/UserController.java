package com.zth.controller;

import com.alibaba.fastjson.JSONObject;
import com.zth.entity.User;
import com.zth.mapper.UserMapper;
import com.zth.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

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

    @PostMapping("/login")
    public JSONObject login(@RequestBody User user) {
        System.out.println(user);
        JSONObject jsonObject = new JSONObject();
        //查询用户账号
        Map map = new HashMap();
        map.put("username",user.getUsername());
        map.put("password",user.getPassword());
        List<User> list = userMapper.selectByMap(map);
//        查询失败
        if (list.size() == 0) {
            jsonObject.put("code",0);
            jsonObject.put("msg","用户名或密码错误");
            jsonObject.put("token","");
            return jsonObject;
        }

        map.put("id",list.get(0).getId().toString());
        String token = JWTUtils.getToken(map);
        jsonObject.put("code",1);
        jsonObject.put("msg","查询成功");
        jsonObject.put("token",token);
        return jsonObject;
    }

    @PostMapping("/tokentest")
    public JSONObject tokentest(HttpServletRequest request, HttpServletResponse response) {

        JSONObject jsonObject = new JSONObject();
        System.out.println("需要验证token的函数执行了...");
        String token = request.getHeader("token");
        String userId = JWTUtils.getUserId(token);
        jsonObject.put("userId",userId);
        jsonObject.put("state",true);
        jsonObject.put("token",token);
        jsonObject.put("msg","请求成功");
        return jsonObject;

    }


}
