package com.migao.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.migao.mybatisplus.mapper.UserMapper;
import com.migao.mybatisplus.pojo.User;
import com.migao.mybatisplus.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author Qi
 * @create 2022-11-30 15:19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
