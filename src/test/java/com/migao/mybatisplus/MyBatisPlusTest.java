package com.migao.mybatisplus;

import com.migao.mybatisplus.mapper.UserMapper;
import com.migao.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Qi
 * @create 2022-11-30 14:08
 */
@SpringBootTest
public class MyBatisPlusTest {
    @Autowired
    private UserMapper userMapper;


    @Test
    public void testSelectList(){
        //通过调节构造器查询一个list集合，若没有条件，则可以设置null为参数
        List<User> userList = userMapper.selectList(null);
        for (User user : userList) {
            System.out.println(user);
        }
    }
    
    @Test
    public void testInsert(){
        User user = new User();
        user.setUserName("张三");
        user.setAge(23);
        user.setEmail("qi1030422412@163.com");
        int result = userMapper.insert(user);
        System.out.println("result:"+result);
        System.out.println("userId:"+user.getId());
    }


    @Test
    public void testDeleteById(){
       /* int result = userMapper.deleteById(0);
        System.out.println(result);*/

        //根据map集合中的条件进行删除
        //DELETE FROM user WHERE name = ? AND age = ?
       /* Map<String,Object> map = new HashMap<>();
        map.put("name","张三");
        map.put("age",23);
        int result = userMapper.deleteByMap(map);
        System.out.println(result);*/

        //通过多个id进行批量删除
        //DELETE FROM user WHERE id IN ( ? , ? , ? )
        List<Long> list = Arrays.asList(1L, 2L, 3L);
        int result = userMapper.deleteBatchIds(list);
        System.out.println(result);
    }


    @Test
    public void testUpdate(){
        //修改用户信息
        //UPDATE user SET name=?, age=?, email=? WHERE id=?
        User user = new User();
        user.setId(4L);
        user.setUserName("李四");
        user.setAge(33);
        user.setEmail("110@163.com");
        int result = userMapper.updateById(user);
        System.out.println(result);
    }


    @Test
    public void testSelect(){
        //通过id查询用户信息
        //SELECT id,name,age,email FROM user WHERE id=?
        /*User user = userMapper.selectById(1L);
        System.out.println(user);*/

        //根据ids批量查找用户信息
        //SELECT id,name,age,email FROM user WHERE id IN ( ? , ? , ? )
        /*List<Long> list = Arrays.asList(1L, 2L, 3L);
        List<User> userList = userMapper.selectBatchIds(list);
        userList.forEach(System.out::println);*/


        //根据map集合中条件进行查询用户信息
        //SELECT id,name,age,email FROM user WHERE name = ? AND age = ?
        /*Map<String,Object> map = new HashMap<>();
        map.put("name","Jack");
        map.put("age",20);
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);*/

        //查询所有数据
        //SELECT id,name,age,email FROM user
       /* List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);*/

        Map<String, Object> map = userMapper.selectMapById(1L);
        System.out.println(map);
    }
}
