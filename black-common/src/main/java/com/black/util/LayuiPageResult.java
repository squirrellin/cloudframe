package com.black.util;

/**
 * @description: layui响应结果类
 * @author: duanwei
 * @create: 2020-05-28 13:57
 **/
public class LayuiPageResult {

    private int code;

    private String msg;

    private int count;

    private Object data;


    public LayuiPageResult(int count, Object data) {
        this.code = 200;
        this.msg = "成功";
        this.count = count;
        this.data = data;
    }

    public LayuiPageResult(int code, String msg, int count, Object data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
