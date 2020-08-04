package com.black.exception.errorEnum;

public enum MenuExceptionEnum {
    PARAM_INVALID(3001, "菜单不存在"),
    MENU_ROLE_ERROR(3002, "菜单、权限对应数据有误"),
    ISEXIT_CHILDREN(3003, "当前节点下存在子菜单,禁止删除"),
    PARENT_NODE_QUERY_NULL(3004, "父节点查询不到数据、权限对应数据有误"),
    PARENT_IS_NOTEXIT(3005, "父菜单不存在"),
    ROLE_ONEMENU_ISNULL(3006, "当前用户角色无一级菜单,不允许添加子菜单");


    private Integer code;
    private String msg;

    MenuExceptionEnum(Integer code, String msg) {
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
