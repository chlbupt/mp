package com.atguigu.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName(value = "t_user")
public class User {
//    @TableId(type = IdType.ASSIGN_ID)
//    @TableId(value = "uid", type=IdType.AUTO)
    @TableId(value = "uid")
    private Long id;
    @TableField(value = "username")
    private String name;
    @TableField(fill = FieldFill.INSERT)
    private Integer age;
    private String email;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

    @TableField(value = "is_deleted")
    @TableLogic
    private Boolean deleted;
}
