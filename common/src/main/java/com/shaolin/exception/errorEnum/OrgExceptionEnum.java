package com.shaolin.exception.errorEnum;

public enum OrgExceptionEnum {
    /**
     * 父节点ID为空
     */
    PARENT_ID_NULL(4001, "父节点ID不能为空"),

    /**
     * 父节点ID不允许删除
     */
    PARENT_NOT_ALLOWED_NULL(4002, "父节点ID不允许删除"),

    /**
     * 起始IP不能大于结束IP
     */
    IP_BEGINEND_ERROR(4003, "起始IP不能大于结束IP"),


    /**
     * 该IP段与同级的其他机构或本级管理的IP段冲突
     */
    IP_CONTRADICT(4004, "该IP段与同级的其他机构或本级管理的IP段冲突"),

    /**
     * IP冲突
     */
    IP_ISNOTEXITPARENT(4005, "IP冲突");


    private Integer code;
    private String msg;

    OrgExceptionEnum(Integer code, String msg) {
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
