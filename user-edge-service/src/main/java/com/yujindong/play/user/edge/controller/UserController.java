package com.yujindong.play.user.edge.controller;

import com.yujindong.play.user.api.UserInfo;
import com.yujindong.play.user.dto.UserDto;
import com.yujindong.play.user.edge.redis.RedisClient;
import com.yujindong.play.user.edge.response.LoginResponse;
import com.yujindong.play.user.edge.response.Response;
import com.yujindong.play.user.edge.thrift.ServiceProvider;
import org.apache.commons.lang.StringUtils;
import org.apache.thrift.TException;
import org.apache.tomcat.util.buf.HexUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import static com.yujindong.play.user.edge.response.Response.*;

/**
 * @Author yujindong
 */
@Controller
@RequestMapping(name = "user-edge", value = "/user")
public class UserController {
    @Value("${redis_prefix}")
    private String redisPrefix;
    @Value("${verify_expires_time}")
    private int verifyExpiresTime;
    @Autowired
    private ServiceProvider serviceProvider;

    @Autowired
    private RedisClient redisClient;

    /**
     * 完善信息
     * @param username
     * @param mobile
     * @param email
     * @return
     */
    public Response perfectUserInfo(
            @RequestParam("username") String username,
            @RequestParam(value = "mobile", required = false) String mobile,
            @RequestParam(value = "email", required = false) String email) {
        return null;
    }

    /**
     * 注册
     * @param mobile
     * @param verifyCode
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Response register(@RequestParam("mobile")String mobile, @RequestParam("verifyCode")String verifyCode) {
        if(StringUtils.isBlank(mobile) || StringUtils.isBlank(verifyCode)) {
            return MOBILE_VERIFY_CANNOT_EMPTY;
        }
        UserInfo user = null;
        try {
            user = serviceProvider.getUserService().getUserByMobile(mobile);
        } catch (TException e) {
            e.printStackTrace();
            return USER_SYSTEM_ERROR;
        }
        if(mobile.equals(user.mobile)) {
            return USER_EXISTS;
        }

        String redisVerifyCode = redisClient.get(redisPrefix + mobile);
        if(!verifyCode.equals(redisVerifyCode)) {
            return VERIFY_CODE_INVALID;
        }
        user = new UserInfo();
        user.setMobile(mobile);
        user.setUsername(mobile);
        user.setPassword(md5("12345"));

        try {
            serviceProvider.getUserService().registerUser(user);
            redisClient.expire(redisPrefix + mobile, 1);
        } catch (TException e) {
            e.printStackTrace();
            return USER_SYSTEM_ERROR;
        }
        return REGISTER_SUCCESS;
    }


    @RequestMapping(value = "/sendVerifyCode", method = RequestMethod.POST)
    @ResponseBody
    public Response sendVerifyCode(@RequestParam("mobile") String mobile) {
        boolean result = false;
        String verifyCode = "1234";
        try {
            result = serviceProvider.getMessageService().sendMobileMessage(mobile, verifyCode);
        } catch (TException e) {
            e.printStackTrace();
            return MESSAGE_SYSTEM_ERROR;
        }
        if(!result) {
            return SEND_VERIFY_CODE_FAILD;
        }
        redisClient.set(redisPrefix + mobile, verifyCode, verifyExpiresTime);
        return SEND_VERIFY_CODE_SUCCESS;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Response login(@RequestParam("mobile")String mobile, @RequestParam("verifyCode")String verifyCode) {
        String redisVerifyCode = redisClient.get(redisPrefix + mobile);
        if(!verifyCode.equals(redisVerifyCode)) {
            return VERIFY_CODE_INVALID;
        }
        UserInfo userInfo = null;
        try {
            userInfo = serviceProvider.getUserService().getUserByMobile(mobile);
        } catch (TException e) {
            e.printStackTrace();
            return USERNAME_PASSWORD_INVALID;
        }
        if(userInfo == null || !mobile.equals(userInfo.getMobile())) {
            return USERNAME_IS_NOT_EXISTS;
        }


        String token = getToken();
        UserDto userDto = new UserDto(userInfo);
        System.out.println(token);
        System.out.println(userDto);
        redisClient.set(token, userDto, 3600);

        return new LoginResponse(token);
    }

    @RequestMapping(value = "/authentication", method = RequestMethod.POST)
    @ResponseBody
    public UserDto authentication(@RequestHeader("token")String token) {
        System.out.println(token);
        UserDto u = redisClient.get(token);
        System.out.println(u);
        return u;
//        return redisClient.get("token");
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
