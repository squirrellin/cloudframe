package com.yaoshun.util;


import com.yaoshun.exception.errorEnum.BaseExceptionEnum;

import java.io.Serializable;


/**
 * @description: 异常通用返回类
 * @author: duanwei
 * @create: 2020-05-28 13:57
 **/
public class ExceptionResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer code;
    private String msg;
    private T result;

    public static boolean isSuccess(ExceptionResult<?> response) {
        return response != null && BaseExceptionEnum.SUCCESS.getCode().equals(response.getCode());
    }

    public ExceptionResult() {
        this.code = BaseExceptionEnum.SUCCESS.getCode();
        this.msg = BaseExceptionEnum.SUCCESS.getMsg();
    }

    public ExceptionResult(Integer code, String message) {
        this.code = code;
        this.msg = message;
    }

    public ExceptionResult(T result) {
        this.code = BaseExceptionEnum.SUCCESS.getCode();
        this.msg = BaseExceptionEnum.SUCCESS.getMsg();
        this.result = result;
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

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}

