package com.unbmy.recruit.service;

import com.unbmy.recruit.pojo.User;

import java.util.List;

/**
 * @author Unbmy
 */
public interface IUserService {

    List<User> getAllUser();
    User getUser(String username, String password);
    int addUser(String username, String password, String email);
    int updateUser(User user);

}
