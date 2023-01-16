package com.migao.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

/**
 * @author Qi
 * @create 2022-11-30 17:59
 */
@Data
public class Product {
    private Long id;
    private String name;
    private Integer price;
    @Version //标识乐观锁版本号字段
    private Integer version;
}
