package com.unbmy.recruit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.unbmy.recruit.pojo.Bill;
import com.unbmy.recruit.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Unbmy
 */
public interface BillMapper extends BaseMapper<Bill> {

    List<Bill> getAllCompletedBill(Long id);
    List<Bill> getAllUnfinishedBill(Long id);
    List<Bill> getLatestBill(Long id);
    List<Bill> queryBill(@Param("keyword") String keyword, @Param("userId") Long id);
    void billCost(Long id);
    void addBillToAll(@Param("billId") Long billId, @Param("userList")List<User> userList);
    void addBillToUser(@Param("billId") Long billId, @Param("userId") Long userId);

}
