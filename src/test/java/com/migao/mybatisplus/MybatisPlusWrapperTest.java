package com.migao.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.migao.mybatisplus.mapper.UserMapper;
import com.migao.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 * @author Qi
 * @create 2022-11-30 16:07
 */
@SpringBootTest
public class MybatisPlusWrapperTest {
    @Autowired
    private UserMapper userMapper;
    
    @Test
    public void tes01(){
        //查询用户名包含a，年龄在20到30之间，邮箱信息不为null的用户信息
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("user_name","a")
                    .between("age",20,30)
                    .isNotNull("email");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void test02(){
        //查询用户信息，按照年龄的降序排序，若年龄相同，则安装id的升序排序
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("age")
                    .orderByAsc("uid");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void test03(){
        //删除邮箱为null的用户胡信息
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("email");
        int result = userMapper.delete(queryWrapper);
        System.out.println(result);
    }

    @Test
    public void test04(){
        //将（年龄大于20并且用户名中包含有a）或邮箱为null的用户信息修改
        //UPDATE t_user SET user_name=?, email=? WHERE is_deleted=0 AND (age > ? AND user_name LIKE ? OR email IS NULL)
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age",20)
                    .like("user_name","a")
                    .or()
                    .isNull("email");
        User user = new User();
        user.setUserName("小明");
        user.setEmail("120@qq.com");
        int result = userMapper.update(user, queryWrapper);
        System.out.println(result);
    }
    
    @Test
    public void test05(){
        //将用户名中包含有a并且（年龄大于20或邮箱为null）的用户信息修改
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("user_name","a")
                    .and(i->i.gt("age",20).or().isNull("email"));
        User user = new User();
        user.setUserName("小hong");
        user.setEmail("120@qq.com");
        int result = userMapper.update(user, queryWrapper);
        System.out.println(result);
    }

    @Test
    public void test06(){
        //查询用户的用户名、年龄、邮箱信息
        //SELECT user_name,age,email FROM t_user WHERE is_deleted=0
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("user_name","age","email");
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        for (Map<String, Object> map : maps) {
            System.out.println(map);
        }
    }


    @Test
    public void test07(){
        //查询id小于等于100的用户信息
        // SELECT uid AS id,user_name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (uid IN (select uid from t_user where uid<=100))
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //子查询
        queryWrapper.inSql("uid","select uid from t_user where uid<=100");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void test08(){
        //将用户名中包含有a并且（年龄大于20或邮箱为null）的用户信息修改
        //UPDATE t_user SET user_name=?,email=? WHERE is_deleted=0 AND (user_name LIKE ? AND (age > ? AND email IS NULL))
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.like("user_name","a")
                     .and(i-> i.gt("age",20).isNull("email"))
                     .set("user_name","小黑").set("email","abc@qq.com");
        int result = userMapper.update(null, updateWrapper);
        System.out.println(result);
    }

    @Test
    public void test09(){
        String username = "";
        Integer ageBegin = 20;
        Integer ageEnd = 30;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(username)){
            //isNotBlank判断某个字符串不为null、不为空字符串、不为空白符
            queryWrapper.like("user_name",username);
        }
        if(ageBegin!=null){
            queryWrapper.ge("age",ageBegin);
        }
        if (ageEnd!=null){
            queryWrapper.le("age",ageEnd);
        }
        //Preparing: SELECT uid AS id,user_name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (age >= ? AND age <= ?)
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void test10(){
        String username = "";
        Integer ageBegin = 20;
        Integer ageEnd = 30;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(username),"user_name",username)
                    .ge(ageBegin!=null,"age",ageBegin)
                    .le(ageEnd!=null,"age",ageEnd);
        List<User> users = userMapper.selectList(queryWrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }


    @Test
    public void test11(){
        String username = "";
        Integer ageBegin = 20;
        Integer ageEnd = 30;
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(username),User::getUserName,username)
                    .ge(ageBegin!=null,User::getAge,ageBegin)
                    .le(ageEnd!=null,User::getAge,ageEnd);
        List<User> users = userMapper.selectList(queryWrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }
    
    @Test
    public void test12(){
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.like(User::getUserName,"a")
                .and(i-> i.gt(User::getAge,20).isNull(User::getEmail))
                .set(User::getUserName,"小黑").set(User::getEmail,"abc@qq.com");
        int result = userMapper.update(null, updateWrapper);
        System.out.println(result);
    }
}
