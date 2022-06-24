package com.unbmy.recruit.controller;

import com.unbmy.recruit.pojo.Maintenance;
import com.unbmy.recruit.service.IMaintenanceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
