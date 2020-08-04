package com.black.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @description: 通用模型对象
 * @author: duanwei
 * @create: 2019-09-01 11:23
 **/
@Data
@Accessors(chain = true)
public class BaseModel extends Model {
    private static final long serialVersionUID = 1L;

    @TableField(value = "cuser", fill = FieldFill.INSERT) // 新增执行
    private String cuser;

    @TableField(value = "ctime", fill = FieldFill.INSERT)
    private LocalDateTime ctime;

    @TableField(value = "uuser", fill = FieldFill.INSERT_UPDATE) // 新增和更新执行
    private String uuser;

    @TableField(value = "utime", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime utime;
}
