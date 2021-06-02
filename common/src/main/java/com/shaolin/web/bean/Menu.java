package com.shaolin.web.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.shaolin.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 菜单表
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_menu")
@ApiModel(value="Menu对象", description="菜单表")
public class Menu extends BaseModel {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ID_WORKER)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "@PID|父节点id")
    private Long parentId;

    @ApiModelProperty(value = " 菜单名称")
    private String name;

    @ApiModelProperty(value = "产品Id")
    private String projectId;

    @ApiModelProperty(value = " 图标地址")
    private String icon;

    @ApiModelProperty(value = " 请求地址")
    private String url;

    private Long orders;

    private Integer disable;

    private String cuser;

    private String uuser;

    @ApiModelProperty(value = " 创建时间")
    private LocalDateTime ctime;

    @ApiModelProperty(value = " 更新时间")
    private LocalDateTime utime;


}
