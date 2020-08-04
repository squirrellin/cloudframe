package com.black.exception.errorEnum;


/**
 * 通用错误码，code编码：10000000-10000999
 *
 * @author King
 * @since 2018/12/14 10:30
 */
public enum CommonErrorEnum {

    /**
     * 服务器核心数据丢失
     */
    SERVER_KEY_DATA_MISSING(10000000, "服务器核心数据丢失"),

    /**
     * 服务器数据查询错误
     */
    SERVER_DATA_QUERY_ERROR(10000001, "服务器数据查询错误"),

    /**
     * 主键查询主键无效
     */
    QUERY_PRIMARY_INVALID(10000002, "主键查询主键无效"),

    /**
     * 主键查询主键无效
     */
    QUERY_ONE_PARAM_INVALID(10000003, "唯一性查询参数无效"),

    /**
     * 序列化异常
     */
    SERIALIZATION_EXCEPTION(10000004, "序列化异常"),

    /**
     * 反序列化异常
     */
    DESERIALIZATION_EXCEPTION(10000005, "反序列化异常"),

    /**
     * 入参无效
     */
    PARAM_INVALID(10000006, "入参无效"),

    /**
     * 核心字段无效
     */
    CRUCIAL_FIELD_INVALID(10000007, "核心字段无效"),

    /**
     * 解压缩异常
     */
    DECOMPRESS_EXCEPTION(10000008, "解压缩异常"),

    /**
     * 缓存查询key值无效
     */
    CACHE_QUERY_KEY_INVALID(10000009, "缓存查询key值无效"),

    /**
     * 缓存失效
     */
    CACHE_LOSE_EFFICACY(10000010, "缓存失效"),

    /**
     * 未知错误
     */
    UNKNOWN(10000999, "未知错误");

    private Integer code;
    private String msg;

    CommonErrorEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
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

}
