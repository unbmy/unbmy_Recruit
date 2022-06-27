package com.unbmy.recruit.service.impl;

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
    public List<Maintenance> getAllCompleteMaintenance() {
        QueryWrapper<Maintenance> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 1);
        return maintenanceMapper.selectList(queryWrapper);
    }

    @Override
    public List<Maintenance> getAllUnhandledMaintenance() {
        QueryWrapper<Maintenance> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 0);
        return maintenanceMapper.selectList(queryWrapper);
    }

    @Override
    public List<Maintenance> getUnhandledMaintenance(Long id) {
        return maintenanceMapper.getUnhandledMaintenance(id);
    }

    @Override
    public List<Maintenance> getCompleteMaintenance(Long id) {
        return maintenanceMapper.getCompleteMaintenance(id);
    }

    @Override
    public List<Maintenance> getLatestMaintenance() {
        QueryWrapper<Maintenance> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("time");
        queryWrapper.last("limit 3");
        queryWrapper.eq("status", 0);
        return maintenanceMapper.selectList(queryWrapper);
    }

    @Override
    public Maintenance getMaintenanceById(Long id) {
        return maintenanceMapper.selectById(id);
    }

    @Override
    public int addMaintenance(String topic, String place, String description, String photo) {
        return maintenanceMapper.insert(new Maintenance(topic, place, description, photo));
    }

    @Override
    public int updateMaintenance(Maintenance maintenance) {
        return maintenanceMapper.updateById(maintenance);
    }

    @Override
    public int deleteMaintenance(Long id) {
        return maintenanceMapper.deleteById(id);
    }
}
