package com.unbmy.recruit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.unbmy.recruit.mapper.EnterpriseMapper;
import com.unbmy.recruit.pojo.Enterprise;
import com.unbmy.recruit.service.IEnterpriseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Unbmy
 */
@Service("enterpriseService")
public class EnterpriseServiceImpl implements IEnterpriseService {
    @Resource
    private EnterpriseMapper enterpriseMapper;

    @Override
    public List<Enterprise> getAllEnterprise() {
        return enterpriseMapper.selectList(null);
    }

    @Override
    public Enterprise getEnterprise(String username, String password) {
        LambdaQueryWrapper<Enterprise> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Enterprise::getUsername, username)
                          .eq(Enterprise::getPassword, password);
        return enterpriseMapper.selectOne(lambdaQueryWrapper);
    }
}
