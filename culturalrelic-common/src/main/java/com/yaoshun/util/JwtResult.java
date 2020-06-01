package com.yaoshun.util;


import org.apache.commons.lang3.StringUtils;
/**
 * @description: jwt响应结果类
 * @author: duanwei
 * @create: 2020-05-28 13:57
 **/
public class JwtResult {

    private Integer code;//返回码

    private Object data;//业务数据

    private String msg;//返回描述

    private String token;//身份标识


    public JwtResult(Integer code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public JwtResult(Integer code, Object data, String msg) {
        super();
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public JwtResult(Integer code, Object data, String msg, String token) {
        super();
        this.code = code;
        this.data = data;
        this.msg = msg;
        if (StringUtils.isNotEmpty(token)) {
            this.token = "Bearer " + token;
        }
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
