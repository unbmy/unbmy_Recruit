package com.unbmy.recruit.service;

import com.unbmy.recruit.pojo.Maintenance;
import org.apache.ibatis.annotations.Param;

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
    void addUserMaintenance(@Param("userId") Long userId, @Param("mtId") Long mtId);
    int addMaintenance(String topic, String place, String description, String photo, Long userId);
    int updateMaintenance(Maintenance maintenance);
    int deleteMaintenance(Long id);

}
