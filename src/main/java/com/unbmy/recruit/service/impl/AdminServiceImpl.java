package com.unbmy.recruit.service.impl;

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
        return adminMapper.selectOne(new QueryWrapper<>(new Admin(username, password)));
    }
}
