package com.migao.mybatisplus;

import com.migao.mybatisplus.enums.SexEnum;
import com.migao.mybatisplus.mapper.UserMapper;
import com.migao.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Qi
 * @create 2022-11-30 18:34
 */
@SpringBootTest
public class MybatisPlusEnum {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test(){
        User user = new User();
        user.setAge(23);
        user.setUserName("小曾");
        user.setSex(SexEnum.MALE);
        int result = userMapper.insert(user);
        System.out.println(result);
    }
}
