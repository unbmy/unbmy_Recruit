package com.unbmy.recruit.service;

import com.unbmy.recruit.pojo.Bill;

import java.util.Date;
import java.util.List;

/**
 * @author Unbmy
 */
public interface IBillService {

    List<Bill> getAllCompletedBill();
    List<Bill> getAllUnfinishedBill();
    List<Bill> getCompletedBill(Long id);
    List<Bill> getUnfinishedBill(Long id);
    List<Bill> getLatestBill(Long id);
    Bill getBillById(Long id);
    void billCost(Long id);
    int updateBill(Bill bill);
    int deleteBill(Long id);
    void addBillToAll(String topic, String description, Double cost, Date date);
    void addBillToUser(String topic, String description, Double cost, Date date, Long userId);

}
