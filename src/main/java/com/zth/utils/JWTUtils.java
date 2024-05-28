package com.zth.utils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.zth.entity.People;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
public class JWTUtils {
    //密钥
    private static final String SING = "zhength";


    public static String getToken(People people) {
        Map map = new HashMap();
        map.put("identity", people.getIdentity().toString());
        map.put("id", people.getId().toString());
        map.put("username", people.getUsername());
        map.put("password", people.getPassword());
        String token = generateToken(map);
        return token;
    }

    /**
     * 生成token
     */
    public static String generateToken(Map<String,String> map){
        Calendar instance = Calendar.getInstance();
        //默认7天过期
        instance.add(Calendar.DATE,7);
        //创建jwt builder
        JWTCreator.Builder builder = JWT.create();
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        String token = builder.withExpiresAt(instance.getTime())//有效期
                .sign(Algorithm.HMAC256(SING));//密钥
        return token;
    }
    /**
     * 验证token合法性
     */
    public static DecodedJWT verify(String token){
        //返回验证结果（结果是内置的）
        return JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }

    public static String getUserId(String token) {
        Claim id = JWTUtils.verify(token).getClaim("id");
        return id.asString();
    }

}
