package com.shaolin.exception;


import com.shaolin.exception.errorEnum.BaseExceptionEnum;

/**
 * @description: 基础异常类
 **/
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 194906846739586857L;

    /**
     * 错误码
     */
    private int code;
    /**
     * 错误内容
     */
    private String msg;


    public BaseException(String msg) {
        super(msg);
    }

    public BaseException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BaseException(BaseExceptionEnum baseExceptionEnum) {
        super(baseExceptionEnum.getMsg());
        this.msg = baseExceptionEnum.getMsg();
        this.code = baseExceptionEnum.getCode();
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
}
