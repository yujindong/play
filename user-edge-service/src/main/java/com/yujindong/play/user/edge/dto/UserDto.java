package com.yujindong.play.user.edge.dto;

import com.yujindong.play.user.api.UserInfo;
//import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * @Author yujindong
 */
public class UserDto implements Serializable {
    private int id;
    private String username;
    private String realName;
    private String password;
    private String mobile;
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
}
