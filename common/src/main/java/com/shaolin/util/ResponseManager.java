package com.shaolin.util;


import com.alibaba.fastjson.JSONObject;
import com.shaolin.common.Constant;


/**
 * @description: jwt响应类
 **/
public class ResponseManager {

    /**
     * 成功请求不带数据
     *
     * @return
     */
    public static String success() {
        JwtResult jwtResult = new JwtResult(Constant.RESCODE_SUCCESS, "success");
        return objToString(jwtResult);
    }

    /**
     * 成功请求带返回数据
     *
     * @param data
     * @return
     */
    public static String successWithData(Object data) {
        JwtResult jwtResult = new JwtResult(Constant.RESCODE_SUCCESS, data, "success");
        return objToString(jwtResult);
    }

    /**
     * 服务器异常不带数据
     *
     * @return
     */
    public static String err() {
        JwtResult jwtResult = new JwtResult(Constant.RESCODE_EXCEPTION, "请稍后再试");
        return objToString(jwtResult);
    }

    /**
     * 服务器异常带数据
     *
     * @param data
     * @return
     */
    public static String errWhitData(Object data) {
        JwtResult jwtResult = new JwtResult(Constant.RESCODE_EXCEPTION, data, "请稍后再试");
        return objToString(jwtResult);
    }

    /**
     * 服务器异常带数据和消息
     *
     * @param data
     * @return
     */
    public static String errWhitData(String msg, Object data) {
        JwtResult jwtResult = new JwtResult(Constant.RESCODE_EXCEPTION, data, msg);
        return objToString(jwtResult);
    }

    /**
     * 用户未登录
     *
     * @return
     */
    public static String noLogin() {
        JwtResult jwtResult = new JwtResult(Constant.RESCODE_NOLOGIN, "用户未登录");
        return objToString(jwtResult);
    }


    /**
     * 无操作权限
     *
     * @return
     */
    public static String noAuth() {
        JwtResult jwtResult = new JwtResult(Constant.RESCODE_NOAUTH, "拒绝授权");
        return objToString(jwtResult);
    }

    /**
     * 登录过期
     *
     * @return
     */
    public static String loginExpire() {
        JwtResult jwtResult = new JwtResult(Constant.RESCODE_LOGINEXPIRE, "登录过期");
        return objToString(jwtResult);
    }


    /**
     * 对象统一转换
     *
     * @param object
     * @return
     */
    public static String objToString(Object object) {
        return JSONObject.toJSONString(object);
    }
}