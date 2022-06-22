package com.unbmy.recruit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.unbmy.recruit.mapper.AdminMapper;
import com.unbmy.recruit.pojo.Admin;
import com.unbmy.recruit.service.IAdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Unbmy
 */
@Service("adminService")
public class AdminServiceImpl implements IAdminService {
    @Resource
    private AdminMapper adminMapper;

    @Override
    public List<Admin> getAllAdmin() {
        return adminMapper.selectList(null);
    }

    @Override
    public Admin getAdmin(String username, String password) {
        LambdaQueryWrapper<Admin> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Admin::getUsername, username)
                          .eq(Admin::getPassword, password);
        return adminMapper.selectOne(lambdaQueryWrapper);
    }
}
