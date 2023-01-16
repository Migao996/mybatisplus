package com.migao.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.migao.mybatisplus.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author Qi
 * @create 2022-11-30 14:06
 */
//@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据id查询用户信息为map集合
     * @param id
     * @return
     */
    Map<String,Object> selectMapById(@Param("id")long id);


    /**
     * 通过年龄查询用户信息并且分页
     * @param page Mybatis-Plus所提供的分页对象，必须位于第一个参数位置
     * @param age
     * @return
     */
    Page<User> selectPageVO(@Param("page") Page<User> page,@Param("age") Integer age);
}
