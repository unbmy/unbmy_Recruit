package com.unbmy.recruit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.unbmy.recruit.pojo.Housing;
import org.apache.ibatis.annotations.Param;

/**
 * @author Unbmy
 */
public interface HousingMapper extends BaseMapper<Housing> {

    void addHousingUser(@Param("userId")Long userId, @Param("housingId")Long housingId);
    Housing getHousingById(@Param("userId")Long userId);

}
