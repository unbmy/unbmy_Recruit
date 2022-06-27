package com.unbmy.recruit.service;

import com.unbmy.recruit.pojo.Housing;

import java.util.List;

/**
 * @author Unbmy
 */
public interface IHousingService {

    List<Housing> getAllHousing();
    Housing getHousingById(Long id);
    Housing getHousingByIdEn(Long id);
    int addHousing(Housing housing);
    int updateHousing(Housing housing);
    int deleteHousing(Long id);
    void  addHousingUser(Long userId, Long housingId);

}
