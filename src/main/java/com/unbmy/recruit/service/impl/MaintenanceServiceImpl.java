package com.unbmy.recruit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.unbmy.recruit.mapper.MaintenanceMapper;
import com.unbmy.recruit.pojo.Maintenance;
import com.unbmy.recruit.service.IMaintenanceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Unbmy
 */
@Service("maintenanceService")
public class MaintenanceServiceImpl implements IMaintenanceService {
    @Resource
    private MaintenanceMapper maintenanceMapper;

    @Override
    public List<Maintenance> getUnhandledMaintenance(Long id) {
        return maintenanceMapper.getUnhandledMaintenance(id);
    }

    @Override
    public List<Maintenance> getCompleteMaintenance() {
        return null;
    }

    @Override
    public Maintenance getMaintenanceById(Long id) {
        return maintenanceMapper.selectById(id);
    }

    @Override
    public int addMaintenance(String place, String description, String photo) {
        return 0;
    }
}
