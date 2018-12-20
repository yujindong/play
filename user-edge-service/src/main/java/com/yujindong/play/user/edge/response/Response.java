package com.yujindong.play.user.edge.response;

import java.io.Serializable;

/**
 * @Author yujindong
 */
public class Response implements Serializable {
    // 用户系统出错
    public static Response USER_SYSTEM_ERROR = new Response("1004", "user system error");
    // 用户名或密码错误
    public static Response USERNAME_PASSWORD_INVALID = new Response("1001", "username password invalid");
    // 用户已存在
    public static Response USERNAME_IS_EXISTS = new Response("1002", "username is exists");
    // 用户不存在
    public static Response USERNAME_IS_NOT_EXISTS = new Response("1003", "username is not exists");
    // 手机号不能为空
    public static Response MOBILE_CANNOT_EMPTY = new Response("1004", "mobile cannot empty");

    private String code;
    private String message;

    public Response() {
        this.code = "0";
    }

    public Response(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
