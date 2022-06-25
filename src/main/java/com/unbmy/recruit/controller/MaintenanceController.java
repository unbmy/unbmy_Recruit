package com.unbmy.recruit.controller;

import com.unbmy.recruit.pojo.Maintenance;
import com.unbmy.recruit.service.IMaintenanceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @author Unbmy
 */
@Controller
@RequestMapping("/maintenance")
public class MaintenanceController {
    @Resource
    private IMaintenanceService maintenanceService;

    @RequestMapping("/maintenanceDetail/{id}")
    public ModelAndView maintenanceDetail(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView();
        Maintenance maintenance = maintenanceService.getMaintenanceById(id);
        modelAndView.addObject("maintenance", maintenance);
        modelAndView.setViewName("/maintenance/maintenanceDetail");
        return modelAndView;
    }

    @RequestMapping("/maintenanceDetail-en/{id}")
    public ModelAndView maintenanceDetailEn(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView();
        Maintenance maintenance = maintenanceService.getMaintenanceById(id);
        modelAndView.addObject("maintenance", maintenance);
        modelAndView.setViewName("/maintenance/maintenanceDetail-en");
        return modelAndView;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public ModelAndView update(@PathVariable Long id,
                               @RequestParam String topic,
                               @RequestParam String place,
                               @RequestParam String description){
        Maintenance maintenance = new Maintenance(id, topic, place, description);
        maintenanceService.updateMaintenance(maintenance);
        return new ModelAndView("redirect:/maintenance/maintenanceDetail-en/{id}");
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView updateStatus(@PathVariable Long id){
        Maintenance maintenance = maintenanceService.getMaintenanceById(id);
        maintenance.setStatus(1);
        maintenanceService.updateMaintenance(maintenance);
        return new ModelAndView("redirect:/enterprise/handledMaintenance");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable Long id){
        maintenanceService.deleteMaintenance(id);
        return new ModelAndView("redirect:/enterprise/handledMaintenance");
    }

}
