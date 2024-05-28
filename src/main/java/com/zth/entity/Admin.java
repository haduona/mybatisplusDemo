package com.zth.entity;


import com.zth.common.IdentityEnum;

public class Admin extends People{

    public Admin() {
        this.setIdentity(IdentityEnum.ADMIN);
    }

}
