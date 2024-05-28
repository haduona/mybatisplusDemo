package com.zth.common;

public enum IdentityEnum {

    ADMIN(1,"管理员"),
    USER(2,"用户"),
    DOCTOR(3,"医生"),
    NURSE(4,"护士"),
    PATIENT(5,"患者");


    private int code;
    private String desc;

    IdentityEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }


}
