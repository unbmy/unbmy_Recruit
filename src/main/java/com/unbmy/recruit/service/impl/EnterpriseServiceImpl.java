package com.unbmy.recruit.service.impl;

import com.unbmy.recruit.mapper.EnterpriseMapper;
import com.unbmy.recruit.pojo.Enterprise;
import com.unbmy.recruit.service.IEnterpriseService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Unbmy
 */
public class EnterpriseServiceImpl implements IEnterpriseService {
    @Resource
    private EnterpriseMapper enterpriseMapper;

    @Override
    public List<Enterprise> getAllEnterprise() {
        return enterpriseMapper.selectList(null);
    }
}
