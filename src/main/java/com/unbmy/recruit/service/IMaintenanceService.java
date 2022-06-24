package com.unbmy.recruit.service;

import com.unbmy.recruit.pojo.Maintenance;

import java.util.List;

/**
 * @author Unbmy
 */
public interface IMaintenanceService {

    List<Maintenance> getUnhandledMaintenance(Long id);
    List<Maintenance> getCompleteMaintenance(Long id);
    Maintenance getMaintenanceById(Long id);
    int addMaintenance(String topic, String place, String description, String photo);

}
