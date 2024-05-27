package com.zth.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zth.entity.User;
import com.zth.service.IUserService;
import com.zth.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    public IUserService userService;

    @GetMapping("/getUser")
    public JSONObject getUser() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",1);
        jsonObject.put("msg","success");
        List<User> users = userService.list(null);
        jsonObject.put("data",users);
        return jsonObject;
    }

    @PostMapping("/login")
    public JSONObject login(@RequestBody User user) {
        JSONObject jsonObject = new JSONObject();
        //查询用户账号
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",user.getUsername());
        queryWrapper.eq("password",user.getPassword());
        User user1 = userService.getOne(queryWrapper);
        //查询失败
        if(user1 == null) {
            jsonObject.put("code",0);
            jsonObject.put("msg","用户名或密码错误");
            jsonObject.put("token","");
            return jsonObject;
        }
        //查询成功
        String token = getToken(user1);
        jsonObject.put("code",1);
        jsonObject.put("msg","查询成功");
        jsonObject.put("token",token);
        return jsonObject;
    }

    @PostMapping("/tokentest")
    public JSONObject tokentest(HttpServletRequest request) {

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



    public String getToken(User user) {
        Map map = new HashMap();
        map.put("id",user.getId().toString());
        map.put("username",user.getUsername());
        map.put("password",user.getPassword());
        String token = JWTUtils.getToken(map);
        return token;
    }

}
