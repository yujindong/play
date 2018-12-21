package com.yujindong.play.constants;

/**
 * @Author yujindong
 */
public interface StatusCode {
    public static interface USER {
        /** 用户服务系统错误 code **/
        public static String SYSTEM_ERROR = "40500";
        /** user service system error desc **/
        public static String SYSTEM_ERROR_DESC = "user service system error";
        /** 用户服务系统错误 描述 **/
        public static String SYSTEM_ERROR_DESC_CN = "用户系统错误";

        /** 用户名或者密码错误 code **/
        public static String USERNAME_PASSWORD_INVALID = "40011";
        /** username or password invalid desc **/
        public static String USERNAME_PASSWORD_INVALID_DESC = "username or password invalid";
        /** 用户名或者密码错误 描述 **/
        public static String USERNAME_PASSWORD_INVALID_DESC_CN = "用户名或密码错误";

        /** 该用户不存在 code **/
        public static String USER_IS_NOT_EXISTS = "40404";
        /** user is not exists desc **/
        public static String USER_IS_NOT_EXISTS_DESC = "user is not exists";
        /** 该用户不存在 描述 **/
        public static String USER_IS_NOT_EXISTS_DESC_CN = "该用户不存在";

        /** 该用户已存在 code **/
        public static String USER_EXISTS = "40404";
        /** user exists desc **/
        public static String USER_EXISTS_DESC = "user exists";
        /** 该用户已存在 描述 **/
        public static String USER_EXISTS_DESC_CN = "该用户已存在";

        /** 注册成功 code **/
        public static String REGISTER_SUCCESS = "400200";
        /** register success desc **/
        public static String REGISTER_SUCCESS_DESC = "register success";
        /** 注册成功 描述 **/
        public static String REGISTER_SUCCESS_DESC_CN = "注册成功";


        /** 手机号，验证码 不能为空 code **/
        public static String MOBILE_VERIFY_CANNOT_EMPTY = "400410";
        /** mobile or verify code can not empty desc **/
        public static String MOBILE_VERIFY_CANNOT_EMPTY_DESC = "mobile or verify code can not empty";
        /** 手机号，验证码 不能为空 描述 **/
        public static String MOBILE_VERIFY_CANNOT_EMPTY_DESC_CN = "手机号或验证码不能为空";



    }

    public static interface MESSAGE {
        /** 消息服务系统错误 code **/
        public static String SYSTEM_ERROR = "60500";
        /** message service system error desc **/
        public static String SYSTEM_ERROR_DESC = "message service system error";
        /** 消息服务系统错误 描述 **/
        public static String SYSTEM_ERROR_DESC_CN = "消息服务系统错误";

        /** 手机号不能为空 code**/
        public static String MOBILE_CAN_NOT_EMPTY = "60400";
        /** mobile can not empty desc **/
        public static String MOBILE_CAN_NOT_EMPTY_DESC = "mobile can not empty";
        /** 手机号不能为空 描述**/
        public static String MOBILE_CAN_NOT_EMPTY_DESC_CN = "手机号不能为空";


        /** 发送验证码失败 code**/
        public static String SEND_VERIFY_FAILD = "60501";
        /** send verify code faild desc **/
        public static String SEND_VERIFY_FAILD_DESC = "send verify code faild";
        /** 发送验证码失败 描述**/
        public static String SEND_VERIFY_FAILD_DESC_CN = "发送验证码失败";

        /** 验证码错误 code**/
        public static String VERIFY_CODE_INVALID = "60502";
        /** verify code invalid desc **/
        public static String VERIFY_CODE_INVALID_DESC = "verify code invalid";
        /** 验证码错误 描述**/
        public static String VERIFY_CODE_INVALID_DESC_CN = "验证码错误";




        /** 发送验证码成功 code**/
        public static String SEND_VERIFY_SUCCESS = "60200";
        /** send verify code success desc **/
        public static String SEND_VERIFY_SUCCESS_DESC = "send verify code success";
        /** 发送验证码成功 描述**/
        public static String SEND_VERIFY_SUCCESS_DESC_CN = "发送验证码成功";




    }

}
