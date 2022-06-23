package com.unbmy.recruit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.unbmy.recruit.pojo.Maintenance;

import java.util.List;

/**
 * @author Unbmy
 */
public interface MaintenanceMapper extends BaseMapper<Maintenance> {

    List<Maintenance> getUnhandledMaintenance(Long id);

}
