package com.shaolin.web.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.shaolin.base.BaseModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_permission")
@ApiModel(value="Permission对象", description="")
public class Permission extends BaseModel {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ID_WORKER)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String name;

    private String descritpion;

    private String url;

    private Long pid;

    private String cuser;

    private String uuser;

    private LocalDateTime ctime;

    private LocalDateTime utime;


}
