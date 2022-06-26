package com.unbmy.recruit.service.impl;

import com.unbmy.recruit.mapper.HousingMapper;
import com.unbmy.recruit.pojo.Housing;
import com.unbmy.recruit.service.IHousingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Unbmy
 */
@Service("housingService")
public class HousingServiceImpl implements IHousingService {
    @Resource
    private HousingMapper housingMapper;

    @Override
    public List<Housing> getAllHousing() {
        return housingMapper.selectList(null);
    }

    @Override
    public Housing getHousingById(Long id) {
        return housingMapper.selectById(id);
    }

    @Override
    public int addHousing(Housing housing) {
        return housingMapper.insert(housing);
    }

    @Override
    public void addHousingUser(Long userId, Long housingId) {
        housingMapper.addHousingUser(userId, housingId);
    }

    @Override
    public int updateHousing(Housing housing) {
        return housingMapper.updateById(housing);
    }

    @Override
    public int deleteHousing(Long id) {
        return housingMapper.deleteById(id);
    }
}
