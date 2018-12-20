package com.yujindong.play.user.edge.controller;

import com.yujindong.play.user.api.UserInfo;
import com.yujindong.play.user.edge.dto.UserDto;
import com.yujindong.play.user.edge.redis.RedisClient;
import com.yujindong.play.user.edge.response.LoginResponse;
import com.yujindong.play.user.edge.response.Response;
import com.yujindong.play.user.edge.thrift.ServiceProvider;
import org.apache.commons.lang.StringUtils;
import org.apache.thrift.TException;
import org.apache.tomcat.util.buf.HexUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * @Author yujindong
 */
@Controller
public class UserController {
    @Autowired
    private ServiceProvider serviceProvider;

    @Autowired
    private RedisClient redisClient;

    public Response registry(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             @RequestParam(value = "mobile", required = false) String mobile,
                             @RequestParam(value = "email", required = false) String email,
                             @RequestParam("verifyCode") String verifyCode) {
        if(StringUtils.isBlank(mobile)) {
            return Response.MOBILE_CANNOT_EMPTY;
        }

        return null;
    }


    public Response sendVerifyCode(@RequestParam("mobile") String mobile, @RequestParam("verifyCode") String verifyCode) {
        return null;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Response login(@RequestParam("username")String username, @RequestParam("password")String password) {
        UserInfo userInfo = null;
        try {
            userInfo = serviceProvider.getUserService().getUserByName(username);
            System.out.println(userInfo);
        } catch (TException e) {
            e.printStackTrace();
            return Response.USERNAME_PASSWORD_INVALID;
        }
        if(userInfo == null) {
            return Response.USERNAME_IS_NOT_EXISTS;
        }
        if(!userInfo.getPassword().equals(md5(password))) {
            return Response.USERNAME_PASSWORD_INVALID;
        }

        String token = getToken();
        redisClient.set(token, new UserDto(userInfo), 3600);

        return new LoginResponse(token);
    }

    private String getToken() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


    private String md5(String str) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] md5bytes = md5.digest(str.getBytes("utf-8"));
            return HexUtils.toHexString(md5bytes);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;

    }

}
