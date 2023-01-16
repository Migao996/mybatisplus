package com.migao.mybatisplus;

import com.migao.mybatisplus.pojo.User;
import com.migao.mybatisplus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Qi
 * @create 2022-11-30 15:20
 */
@SpringBootTest
public class MybatisPlusServiceTest {
    @Autowired
    private UserService userService;
    @Test
    public void testGetCount(){
        //查询总记录数
        //SELECT COUNT( * ) FROM user
        long count = userService.count();
        System.out.println("总记录数:"+count);

    }
    @Test
    public void testInsertBatch(){
        //批量添加
        List<User> list = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            User user = new User();
            user.setUserName("zsq"+i);
            user.setAge(20+i);
            list.add(user);
        }
        boolean b = userService.saveBatch(list);
        System.out.println(b);
    }
}
