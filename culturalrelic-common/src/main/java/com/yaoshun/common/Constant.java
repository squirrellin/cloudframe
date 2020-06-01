package com.yaoshun.common;

/**
 * @Description: 常量类
 * @Author: duanwei
 * @Date: 2019/7/29
 */
public interface Constant {

    String PAGE_NUM = "0";

    String SIZE_NUM = "20";

    String PAGE = "page";

    String SIZE = "size";


    String ZERO = "0";

    String ONE = "1";

    String TWO = "2";

    String THREE = "3";

    String NULL = "";

    String JSON_NULL = "[]";


    /**
     * 请求成功
     */
    String SUCCESS = "200";
    /**
     * 请求错误
     */
    String ERROR = "300";

    /**
     * 无权限
     */
    String PERMISSION = "401";
    /**
     * 请求成功，其他错误
     */
    String DATA_NULL = "402";
    /**
     * 请求失败
     */
    String FAILED = "999";

    /**
     * 最大值
     */
    Integer MAX = 32767;




    /**
     * 数据请求返回码
     */
    public static final int RESCODE_SUCCESS = 1000;				//成功
    public static final int RESCODE_SUCCESS_MSG = 1001;			//成功(有返回信息)
    public static final int RESCODE_EXCEPTION = 1002;			//请求抛出异常
    public static final int RESCODE_NOLOGIN = 1003;				//未登陆状态
    public static final int RESCODE_NOEXIST = 1004;				//查询结果为空
    public static final int RESCODE_NOAUTH = 1005;				//无操作权限
    public static final int RESCODE_LOGINEXPIRE = 1006;				//登录过期
    /**
     * token
     */
    public static final int JWT_ERRCODE_EXPIRE = 1007;//Token过期
    public static final int JWT_ERRCODE_FAIL = 1008;//验证不通过

    /**
     * jwt
     */
    public static final String JWT_ID = "jwt-black";//jwt签发者
    public static final String JWT_SECRET = "Isi5Ob9OfvJt+4IHoMJlHkS1ttg=";//密匙
    public static final int JWT_TTL = 60*60*1000; // 60*60*1000;  //millisecond
    public static final int JWT_REFRESH_INTERVAL = 18*1000; //55*60*1000;  //millisecond
    public static final int JWT_REFRESH_TTL = 60*1000; // 12*60*60*1000;  //millisecond

}
