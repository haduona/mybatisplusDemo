package com.zth.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.zth.entity.Admin;
import com.zth.service.IAdminService;
import com.zth.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    /**
     * 查询所有用户
     *
     * @return
     */
    @GetMapping("/getAdmins")
    public JSONObject getUser() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 1);
        jsonObject.put("msg", "success");
        List<Admin> admins = adminService.list(null);
        jsonObject.put("data", admins);
        return jsonObject;
    }

    /**
     * 登录
     *
     * @param admin
     */
    @PostMapping("/login")
    public JSONObject login(@RequestBody Admin admin) {
        JSONObject jsonObject = new JSONObject();
        //查询用户账号
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", admin.getUsername());
        queryWrapper.eq("password", admin.getPassword());
        Admin admin1 = adminService.getOne(queryWrapper);
        //查询失败
        if (admin1 == null) {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "用户名或密码错误");
            jsonObject.put("token", "");
            return jsonObject;
        }
        //查询成功
        String token = JWTUtils.getToken(admin1);
        jsonObject.put("code", 1);
        jsonObject.put("msg", "查询成功");
        jsonObject.put("token", token);
        return jsonObject;
    }

    /**
     * 添加管理员
     *
     * @param admin
     * @return
     */
    @PostMapping("/addAdmin")
    public JSONObject addAdmin(@RequestBody Admin admin) {
        JSONObject jsonObject = new JSONObject();
        if (adminService.save(admin)) {
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
     * 删除管理员
     *
     * @param admin
     * @return
     */
    @PostMapping("/deleteAdmin")
    public JSONObject deleteAdmin(@RequestBody Admin admin) {
        JSONObject jsonObject = new JSONObject();
        if (adminService.removeById(admin.getId())) {
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
     * 修改管理员
     *
     * @param admin
     * @return
     */
    @PostMapping("/updateAdmin")
    public JSONObject updateAdmin(@RequestBody Admin admin) {
        JSONObject jsonObject = new JSONObject();
        if (adminService.updateById(admin)) {
            jsonObject.put("code", 1);
            jsonObject.put("msg", "修改成功");
            return jsonObject;
        } else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "修改失败");
            return jsonObject;
        }
    }


}
