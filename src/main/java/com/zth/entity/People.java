package com.zth.entity;

import com.zth.common.IdentityEnum;

public class People {

    private Long id;
    private String username;
    private String password;
    private IdentityEnum identity;
    private String phoneNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public IdentityEnum getIdentity() {
        return identity;
    }

    public void setIdentity(IdentityEnum identity) {
        this.identity = identity;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", identity=" + identity +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
