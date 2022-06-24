package com.unbmy.recruit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.unbmy.recruit.mapper.UserMapper;
import com.unbmy.recruit.pojo.User;
import com.unbmy.recruit.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Unbmy
 */
@Service("userService")
public class UserServiceImpl implements IUserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> getAllUser() {
        return userMapper.selectList(null);
    }

    @Override
    public User getUser(String username, String password) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(User::getUsername, username)
                          .eq(password!=null, User::getPassword, password);
        return userMapper.selectOne(lambdaQueryWrapper);
    }

    @Override
    public int addUser(String username, String password, String email) {
        return userMapper.insert(new User(username, password, email));
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateById(user);
    }
}
