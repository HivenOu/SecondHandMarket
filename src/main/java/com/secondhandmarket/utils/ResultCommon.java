package com.secondhandmarket.utils;

/**
 * 通用的数据封装工具类
 */
public class ResultCommon<T> {

    private int code;
    private String msg;
    private T data;

    public static ResultCommon success(ResultCode resultCode){
        ResultCommon resultCommon = new ResultCommon(resultCode.getCode(), resultCode.getMsg());
        return resultCommon;
    }

    public static<T> ResultCommon success(ResultCode resultCode, T data){
        ResultCommon success = success(resultCode);
        success.setData(data);
        return success;
    }

    public static ResultCommon fail(ResultCode resultCode){
        return success(resultCode);
    }



    public ResultCommon() {
    }

    public ResultCommon(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
