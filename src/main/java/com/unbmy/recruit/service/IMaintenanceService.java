package com.unbmy.recruit.service;

import com.unbmy.recruit.pojo.Maintenance;

import java.util.List;

/**
 * @author Unbmy
 */
public interface IMaintenanceService {

    List<Maintenance> getAllCompleteMaintenance();
    List<Maintenance> getAllUnhandledMaintenance();
    List<Maintenance> getUnhandledMaintenance(Long id);
    List<Maintenance> getCompleteMaintenance(Long id);
    List<Maintenance> getLatestMaintenance();
    Maintenance getMaintenanceById(Long id);
    int addMaintenance(String topic, String place, String description, String photo);
    int updateMaintenance(Maintenance maintenance);
    int deleteMaintenance(Long id);

}
