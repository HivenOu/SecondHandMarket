package com.secondhandmarket.utils;

/**
 * 常见的状态码
 */
public enum ResultCode {

    SUCCESS(200,"请求成功"),
    FAIL(500,"请求失败"),
    ERROR_PHONE_PASSWORD(501,"账号密码错误"),
    DONGJIE_PHONE_PASSWORD(502,"账户被冻结"),
    BAD_NEWPASSWORD(505,"新密码不能为空"),
    BAD_PASSWORD(504,"原密码输入错误"),
    REPPET(503,"重复关注");

    private int code; //状态码
    private String msg;  //信息

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    //有参数的构造方法
    ResultCode(int code, String msg){
        this.code=code;
        this.msg=msg;
    }
}
