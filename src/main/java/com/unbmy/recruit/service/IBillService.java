package com.unbmy.recruit.service;

import com.unbmy.recruit.pojo.Bill;

import java.util.List;

/**
 * @author Unbmy
 */
public interface IBillService {

    List<Bill> getAllBill();
    List<Bill> getAllCompletedBill(Long id);
    List<Bill> getAllUnfinishedBill(Long id);
    Bill getBillById(Long id);
    void billCost(Long id);

}
