package com.unbmy.recruit.controller;

import com.unbmy.recruit.pojo.Bill;
import com.unbmy.recruit.service.IBillService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @author Unbmy
 */
@Controller
@RequestMapping("/bill")
public class BillController {
    @Resource
    private IBillService billService;

    @RequestMapping("/billDetail/{id}")
    public ModelAndView maintenanceDetail(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView();
        Bill bill = billService.getBillById(id);
        modelAndView.addObject("bill", bill);
        modelAndView.setViewName("/bill/billDetail");
        return modelAndView;
    }

}
