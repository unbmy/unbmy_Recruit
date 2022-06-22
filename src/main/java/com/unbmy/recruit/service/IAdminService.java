package com.unbmy.recruit.service;

import com.unbmy.recruit.pojo.Admin;

import java.util.List;

/**
 * @author Unbmy
 */
public interface IAdminService {

    List<Admin> getAllAdmin();
    Admin getAdmin(String username, String password);

}
