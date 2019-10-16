package com.nixsolutions.sql_jdbc.entity;

import java.io.Serializable;

public class UserLogin implements Serializable {
    private Long id;
    private String loginName;
    private String password;
    private String email;

    public UserLogin() {
    }

    public UserLogin(String loginName, String password, String email) {
        this.loginName = loginName;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
