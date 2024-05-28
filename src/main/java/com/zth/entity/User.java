package com.zth.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.zth.common.IdentityEnum;

@TableName("tb_user")
public class User extends People {

    public User()
    {
        this.setIdentity(IdentityEnum.USER);
    }

}
