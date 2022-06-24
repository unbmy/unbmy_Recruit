package com.unbmy.recruit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.unbmy.recruit.pojo.Bill;

import java.util.List;

/**
 * @author Unbmy
 */
public interface BillMapper extends BaseMapper<Bill> {

    List<Bill> getAllCompletedBill(Long id);
    List<Bill> getAllUnfinishedBill(Long id);
    List<Bill> getLatestBill(Long id);
    void billCost(Long id);

}
