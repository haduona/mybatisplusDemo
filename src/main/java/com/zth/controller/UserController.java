package com.zth.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zth.common.IdentityEnum;
import com.zth.entity.User;
import com.zth.service.IUserService;
import com.zth.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    public IUserService userService;

    /**
     * 查询所有用户
     * @return
     */
    @GetMapping("/getUsers")
    public JSONObject getUser() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 1);
        jsonObject.put("msg", "success");
        List<User> users = userService.list(null);
        jsonObject.put("data", users);
        return jsonObject;
    }


    /**
     * 登录
     * @param user
     * @return
     */
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
        String token = JWTUtils.getToken(user1);
        jsonObject.put("code",1);
        jsonObject.put("msg","查询成功");
        jsonObject.put("token",token);
        return jsonObject;
    }


    //对user的增删改查

    /**
     * 添加用户
     * @param user
     * @return
     */
    @PostMapping("/addUser")
    public JSONObject addUser(@RequestBody User user) {
        JSONObject jsonObject = new JSONObject();
        user.setIdentity(IdentityEnum.USER);
        if (userService.save(user)) {
            jsonObject.put("code", 1);
            jsonObject.put("msg", "添加成功");
            return jsonObject;
        } else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "添加失败");
            return jsonObject;
        }
    }


    /**
     * 删除用户
     * @param user
     * @return
     */
    @PostMapping("/deleteUser")
    public JSONObject deleteUser(@RequestBody User user) {
        JSONObject jsonObject = new JSONObject();

        if (userService.removeById(user.getId())) {
            jsonObject.put("code", 1);
            jsonObject.put("msg", "删除成功");
            return jsonObject;
        } else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "删除失败");
            return jsonObject;
        }
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @PostMapping("/updateUser")
    public JSONObject updateUser(@RequestBody User user) {
        JSONObject jsonObject = new JSONObject();
        if (userService.updateById(user)) {
            jsonObject.put("code", 1);
            jsonObject.put("msg", "修改成功");
            return jsonObject;
        } else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "修改失败");
            return jsonObject;
        }
    }



    @PostMapping("/tokentest")
    public JSONObject tokentest(HttpServletRequest request) {

        JSONObject jsonObject = new JSONObject();
        System.out.println("需要验证token的函数执行了...");
        String token = request.getHeader("token");
        String userId = JWTUtils.getUserId(token);
        jsonObject.put("userId", userId);
        jsonObject.put("state", true);
        jsonObject.put("token", token);
        jsonObject.put("msg", "请求成功");
        return jsonObject;

    }

}
