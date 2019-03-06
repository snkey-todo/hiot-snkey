package com.snkey.core.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private String id;
    private String password;
    private Date lastlogin;
    private String username;
    private String email;
    private Integer is_active;
    private Date date_joined;
    private Integer is_superuser;
    private Integer is_staff;
    private Integer is_developer;
    private String img;
    private String phone;

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(Date lastlogin) {
        this.lastlogin = lastlogin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIs_active() {
        return is_active;
    }

    public void setIs_active(Integer is_active) {
        this.is_active = is_active;
    }

    public Date getDate_joined() {
        return date_joined;
    }

    public void setDate_joined(Date date_joined) {
        this.date_joined = date_joined;
    }

    public Integer getIs_superuser() {
        return is_superuser;
    }

    public void setIs_superuser(Integer is_superuser) {
        this.is_superuser = is_superuser;
    }

    public Integer getIs_staff() {
        return is_staff;
    }

    public void setIs_staff(Integer is_staff) {
        this.is_staff = is_staff;
    }

    public Integer getIs_developer() {
        return is_developer;
    }

    public void setIs_developer(Integer is_developer) {
        this.is_developer = is_developer;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", lastlogin=" + lastlogin +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", is_active=" + is_active +
                ", date_joined=" + date_joined +
                ", is_superuser=" + is_superuser +
                ", is_staff=" + is_staff +
                ", is_developer=" + is_developer +
                ", img='" + img + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
