package com.black.exception;


import com.black.exception.errorEnum.OrgExceptionEnum;
/**
 * @description: 组织机构异常类
 * @author: duanwei
 * @create: 2019-08-28 20:07
 **/
public class OrgException extends BaseException {
    private static final long serialVersionUID = 194906846739586857L;

    /**
     * 错误码
     */
    private int code;
    /**
     * 错误内容
     */
    private String msg;

    public OrgException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public OrgException(OrgExceptionEnum orgExceptionEnum) {
        super(orgExceptionEnum.getMsg());
        this.msg = orgExceptionEnum.getMsg();
        this.code = orgExceptionEnum.getCode();
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
