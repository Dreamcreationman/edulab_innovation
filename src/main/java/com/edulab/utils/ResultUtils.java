package com.edulab.utils;

/**
 * CREATED BY Dream
 * DATE : 2018/8/29
 * MAIL : YANK.TENYOND@GMAIL.COM
 * FUNCTION : 返回值设计
 */
public class ResultUtils<T> {

    private boolean success;

    private String msg;

    private T data;


    public ResultUtils() {
    }

    public ResultUtils(boolean success, String msg, T data) {
        this.success = success;
        this.msg = msg;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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
