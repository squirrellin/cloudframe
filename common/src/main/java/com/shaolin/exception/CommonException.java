package com.shaolin.exception;


import com.shaolin.exception.errorEnum.CommonErrorEnum;

/**
 * @description: 共同异常类
 **/
public class CommonException extends BaseException {

    private static final long serialVersionUID = 194906846739586857L;

    /**
     * 错误码
     */
    private int code;
    /**
     * 错误内容
     */
    private String msg;

    public CommonException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public CommonException(CommonErrorEnum menuExceptionEnum) {
        super(menuExceptionEnum.getMsg());
        this.msg = menuExceptionEnum.getMsg();
        this.code = menuExceptionEnum.getCode();
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public void setMsg(String msg) {
        this.msg = msg;
    }

}
