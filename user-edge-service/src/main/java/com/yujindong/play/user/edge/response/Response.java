package com.yujindong.play.user.edge.response;

import com.yujindong.play.constants.StatusCode;

import java.io.Serializable;


/**
 * @Author yujindong
 */
public class Response implements Serializable {
    /** 用户系统出错 **/
    public static Response USER_SYSTEM_ERROR = new Response(StatusCode.USER.SYSTEM_ERROR, StatusCode.USER.SYSTEM_ERROR_DESC_CN);
    /** 用户名或密码错误 **/
    public static Response USERNAME_PASSWORD_INVALID = new Response(StatusCode.USER.USERNAME_PASSWORD_INVALID, StatusCode.USER.USERNAME_PASSWORD_INVALID_DESC_CN);
    /** 用户不存在 **/
    public static Response USERNAME_IS_NOT_EXISTS = new Response(StatusCode.USER.USER_IS_NOT_EXISTS, StatusCode.USER.USER_IS_NOT_EXISTS_DESC_CN);
    /** 用户已存在 **/
    public static Response USER_EXISTS = new Response(StatusCode.USER.USER_EXISTS, StatusCode.USER.USER_EXISTS_DESC_CN);
    /** 注册成功 **/
    public static Response REGISTER_SUCCESS = new Response(StatusCode.USER.REGISTER_SUCCESS, StatusCode.USER.REGISTER_SUCCESS_DESC_CN);
    /** 手机号不能为空 **/
    public static Response MOBILE_CANNOT_EMPTY = new Response(StatusCode.MESSAGE.MOBILE_CAN_NOT_EMPTY, StatusCode.MESSAGE.MOBILE_CAN_NOT_EMPTY_DESC_CN);
    /** 手机号和验证码不能为空 **/
    public static Response MOBILE_VERIFY_CANNOT_EMPTY = new Response(StatusCode.USER.MOBILE_VERIFY_CANNOT_EMPTY, StatusCode.USER.MOBILE_VERIFY_CANNOT_EMPTY_DESC_CN);
    /** 发送验证码失败 **/
    public static Response SEND_VERIFY_CODE_FAILD = new Response(StatusCode.MESSAGE.SEND_VERIFY_FAILD, StatusCode.MESSAGE.SEND_VERIFY_FAILD_DESC_CN);
    /** 验证码错误 **/
    public static Response VERIFY_CODE_INVALID = new Response(StatusCode.MESSAGE.VERIFY_CODE_INVALID, StatusCode.MESSAGE.VERIFY_CODE_INVALID_DESC_CN);
    /** 发送验证码成功 **/
    public static Response SEND_VERIFY_CODE_SUCCESS = new Response(StatusCode.MESSAGE.SEND_VERIFY_SUCCESS, StatusCode.MESSAGE.SEND_VERIFY_SUCCESS_DESC_CN);
    /** 消息服务系统出错 **/
    public static Response MESSAGE_SYSTEM_ERROR = new Response(StatusCode.MESSAGE.SYSTEM_ERROR, StatusCode.MESSAGE.SYSTEM_ERROR_DESC_CN);

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
