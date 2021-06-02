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
 * 菜单、权限表
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_menu_role")
@ApiModel(value="MenuRole对象", description="菜单、权限表")
public class MenuRole extends BaseModel {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ID_WORKER)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "用户id")
    private String projectId;

    @ApiModelProperty(value = "权限id")
    private Long roleId;

    @ApiModelProperty(value = "菜单id")
    private Long menuId;

    private Integer disable;

    private String cuser;

    private String uuser;

    @ApiModelProperty(value = " 创建时间")
    private LocalDateTime ctime;

    @ApiModelProperty(value = " 更新时间")
    private LocalDateTime utime;


}
