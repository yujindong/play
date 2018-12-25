package com.yujindong.play.user.dto;

import com.yujindong.play.user.api.UserInfo;

import java.io.Serializable;

//import org.springframework.beans.BeanUtils;

/**
 * @Author yujindong
 */
public class UserDto implements Serializable {
    private int id;
    private String username;
    private String realName;
    private String password;
    private String mobile;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;

    public UserDto() {
    }

    public UserDto(UserInfo userInfo) {
//        BeanUtils.copyProperties(userInfo,this);
        this.id = userInfo.id;
        this.username = userInfo.username;
        this.realName = userInfo.realName;
        this.password = userInfo.password;
        this.mobile = userInfo.mobile;
        this.email = userInfo.email;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", realName='" + realName + '\'' +
                ", password='" + password + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
