package com.zth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zth.common.IdentityEnum;
import com.zth.entity.Admin;
import com.zth.entity.People;
import com.zth.entity.User;
import com.zth.mapper.AdminMapper;
import com.zth.mapper.PeopleMapper;
import com.zth.mapper.UserMapper;
import com.zth.utils.JWTUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MybatisplusDemoApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PeopleMapper peopleMapper;

    @Autowired
    private AdminMapper adminMapper;





    /**
     * 分页插件测试
     */
    @Test
    void fenyeTest() {

        System.out.println("--------------");
        System.out.println(peopleMapper.selectList(null));
        System.out.println("--------------");

        System.out.println("第一批数据");
        IPage<People> peopleIPage = peopleMapper.selectPageVo(new Page<>(1, 5));
        List<People> records = peopleIPage.getRecords();
        for (People people : records) {
            System.out.println(people);
        }
        System.out.println("第二批数据");
        IPage<People> peopleIPage1 = peopleMapper.selectPageVo(new Page<>(2, 5));
        List<People> records1 = peopleIPage1.getRecords();
        for (People people : records1) {
            System.out.println(people);
        }
        System.out.println("第三批数据");
        IPage<People> peopleIPage2 = peopleMapper.selectPageVo(new Page<>(3, 5));
        List<People> records2 = peopleIPage2.getRecords();
        for (People people : records2) {
            System.out.println(people);
        }

    }


    @Test
    void adminTest() {
        List<Admin> list = adminMapper.selectList(null);
        System.out.println(list);
    }




    @Test
    void peopleTest() {

        List<People> people = peopleMapper.selectList(null);
        System.out.println(people);
        IdentityEnum identity = people.get(0).getIdentity();
        System.out.println(identity);
        if(identity == IdentityEnum.ADMIN) {
            System.out.println("管理员");
        }
        else {
            System.out.println("其他");
        }

    }



    @Test
    void getUser() {
        System.out.println(userMapper.selectList(null));
        Map map = new HashMap();
        map.put("username", "zhangsan");
        map.put("password", "123");
        List list = userMapper.selectByMap(map);
        User user = (User) list.get(0);
        String token = JWTUtils.generateToken(map);
        System.out.println(token);
        System.out.println("verify是：");
//        DecodedJWT verify = JWTUtils.verify(token);
//        System.out.println(verify.getHeader());
//        System.out.println(verify.getPayload());
//        System.out.println(verify.getSignature());
//        System.out.println(verify.getToken());

        System.out.println("试试一个错误的token");
        String token1 = token + "pppp";
        try {
            DecodedJWT verify1 = JWTUtils.verify(token1);
        } catch (SignatureVerificationException e) {
            //e.printStackTrace();
            map.put("msg", "无效签名");
        } catch (TokenExpiredException e) {
            //e.printStackTrace();
            map.put("msg", "token过期");
        } catch (AlgorithmMismatchException e) {
            //e.printStackTrace();
            map.put("msg", "token算法不一致");
        } catch (Exception e) {
            //e.printStackTrace();
            map.put("msg", "token无效");
        }
        map.put("state", "flase");
        System.out.println(map);

    }

    @Test
    void loginTest() {
        Map map = new HashMap();
        map.put("username", "zhangsan");
        map.put("password", "123");
        List list = userMapper.selectByMap(map);
        User user = (User) list.get(0);
        System.out.println(user);
    }

    @Test
    void getParamByTokenTest() {
        Map map = new HashMap();
        map.put("id","10001");
        map.put("username", "zhangsan");
        map.put("password", "123");
        String token = JWTUtils.generateToken(map);
        System.out.println(JWTUtils.verify(token));
        Claim id = JWTUtils.verify(token).getClaim("id");
        System.out.println(id.asString());
    }
}
