package com.black.exception;


import com.black.exception.errorEnum.UserExceptionEnum;
/**
 * @description: 用户异常类
 * @author: duanwei
 * @create: 2019-08-28 20:07
 **/
public class UserException extends BaseException {
    private static final long serialVersionUID = 194906846739586857L;

    /**
     * 错误码
     */
    private int code;
    /**
     * 错误内容
     */
    private String msg;

    public UserException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public UserException(UserExceptionEnum userExceptionEnum) {
        super(userExceptionEnum.getMsg());
        this.msg = userExceptionEnum.getMsg();
        this.code = userExceptionEnum.getCode();
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
