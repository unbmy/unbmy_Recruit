package com.unbmy.recruit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.unbmy.recruit.mapper.BillMapper;
import com.unbmy.recruit.pojo.Bill;
import com.unbmy.recruit.service.IBillService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Unbmy
 */
@Service("billService")
public class BillServiceImpl implements IBillService {
    @Resource
    private BillMapper billMapper;

    @Override
    public List<Bill> getAllBill() {
        return billMapper.selectList(null);
    }

    @Override
    public List<Bill> getAllCompletedBill(Long id) {
        return billMapper.getAllCompletedBill(id);
    }

    @Override
    public List<Bill> getAllUnfinishedBill(Long id) {
        return billMapper.getAllUnfinishedBill(id);
    }

    @Override
    public List<Bill> getLatestBill(Long id) {
        return billMapper.getLatestBill(id);
    }

    @Override
    public Bill getBillById(Long id) {
        return billMapper.selectById(id);
    }

    @Override
    public void billCost(Long id) {
        billMapper.billCost(id);
    }
}
