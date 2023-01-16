package com.migao.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.migao.mybatisplus.enums.SexEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Qi
 * @create 2022-11-30 14:00
 */
@Data
//设置实体类所对应的表名
//@TableName("t_user")
public class User {
    //@TableId的value属性用于将属性所以对应的字段指定为主键
    //@TableId注解的type属性设置主键生成策略
//    @TableId(value = "uid",type = IdType.AUTO)
    @TableId("uid")
    private Long id;

    //指定属性所对应的字段名
    @TableField("user_name") //默认开启驼峰命名法，这里可以不加
    private String userName;

    private Integer age;

    private String email;

    private SexEnum sex;

    @TableLogic
    private Integer isDeleted;
}
