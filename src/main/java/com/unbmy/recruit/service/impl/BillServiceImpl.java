package com.unbmy.recruit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.unbmy.recruit.mapper.BillMapper;
import com.unbmy.recruit.mapper.UserMapper;
import com.unbmy.recruit.pojo.Bill;
import com.unbmy.recruit.pojo.User;
import com.unbmy.recruit.service.IBillService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Unbmy
 */
@Service("billService")
public class BillServiceImpl implements IBillService {
    @Resource
    private BillMapper billMapper;
    @Resource
    private UserMapper userMapper;

    @Override
    public List<Bill> getAllCompletedBill() {
        QueryWrapper<Bill> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 1);
        return billMapper.selectList(queryWrapper);
    }

    @Override
    public List<Bill> getAllUnfinishedBill() {
        QueryWrapper<Bill> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 0);
        return billMapper.selectList(queryWrapper);
    }

    @Override
    public List<Bill> getCompletedBill(Long id) {
        return billMapper.getAllCompletedBill(id);
    }

    @Override
    public List<Bill> getUnfinishedBill(Long id) {
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

    @Override
    public int updateBill(Bill bill) {
        return billMapper.updateById(bill);
    }

    @Override
    public int deleteBill(Long id) {
        return billMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addBillToAll(String topic, String description, Double cost, Date date) {
        List<User> users = userMapper.selectList(null);
        Bill bill = new Bill(topic, description, cost, date);
        try {
            billMapper.insert(bill);
            Long billId = bill.getId();
            billMapper.addBillToAll(billId, users);
        } catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addBillToUser(String topic, String description, Double cost, Date date, Long userId) {
        Bill bill = new Bill(topic, description, cost, date);
        try {
            billMapper.insert(bill);
            Long billId = bill.getId();
            billMapper.addBillToUser(billId, userId);
        } catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

    }
}
