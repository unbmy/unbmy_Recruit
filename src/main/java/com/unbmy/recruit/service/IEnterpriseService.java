package com.unbmy.recruit.service;

import com.unbmy.recruit.pojo.Enterprise;

import java.util.List;

/**
 * @author Unbmy
 */
public interface IEnterpriseService {

    List<Enterprise> getAllEnterprise();
    Enterprise getEnterprise(String username, String password);
    int addEnterprise(String username, String password, String enterpriseName,
                      String address, String phone);
    int updateEnterprise(Enterprise enterprise);

}
