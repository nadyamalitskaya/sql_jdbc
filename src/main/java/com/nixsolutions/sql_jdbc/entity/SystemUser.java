package com.nixsolutions.sql_jdbc.entity;

import java.io.Serializable;
import java.util.Date;

public class SystemUser implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private Long loginId;
    private String passportNumber;
    private String phoneNumber;
    private String address;
    private Date birthday;
    private Long roleId;

    public SystemUser() {
    }

    public SystemUser(String firstName, String lastName, Long loginId, String passportNumber, String phoneNumber,
                      String address, Date birthday, Long roleId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.loginId = loginId;
        this.passportNumber = passportNumber;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.birthday = birthday;
        this.roleId = roleId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getLoginId() {
        return loginId;
    }

    public void setLoginId(Long loginId) {
        this.loginId = loginId;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
