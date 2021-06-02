package com.shaolin.util;


import com.shaolin.exception.errorEnum.BaseExceptionEnum;

import java.io.Serializable;

/**
 * @description: 普通响应结果类
 **/
public class Response implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer code;
    private String msg;
    private Object data;

    public static boolean isSuccess(Response response) {
        return response != null && BaseExceptionEnum.SUCCESS.getCode().equals(response.getCode());
    }

    public Response() {
        this.code = BaseExceptionEnum.SUCCESS.getCode();
        this.msg = BaseExceptionEnum.SUCCESS.getMsg();
    }

    public Response(Integer code, String message) {
        this.code = code;
        this.msg = message;
    }

    public Response(Object data) {
        this.code = BaseExceptionEnum.SUCCESS.getCode();
        this.msg = BaseExceptionEnum.SUCCESS.getMsg();
        this.data = data;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

