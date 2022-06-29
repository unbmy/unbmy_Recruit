package com.unbmy.recruit.controller;

import com.unbmy.recruit.pojo.Bill;
import com.unbmy.recruit.service.IBillService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Unbmy
 */
@Controller
@RequestMapping("/bill")
public class BillController {
    @Resource
    private IBillService billService;

    @RequestMapping("/billDetail/{id}")
    public ModelAndView billDetail(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView();
        Bill bill = billService.getBillById(id);
        modelAndView.addObject("bill", bill);
        modelAndView.setViewName("/bill/billDetail");
        return modelAndView;
    }

    @RequestMapping("/billDetail-en/{id}")
    public ModelAndView billDetailEn(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView();
        Bill bill = billService.getBillById(id);
        modelAndView.addObject("bill", bill);
        modelAndView.setViewName("/bill/billDetail-en");
        return modelAndView;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ModelAndView addBill(@RequestParam String topic,
                                @RequestParam String description,
                                @RequestParam Double cost,
                                @RequestParam Integer type,
                                @RequestParam(required = false) Long userId){
        Date date = new Date();
        if (type == 0){
            billService.addBillToAll(topic, description, cost ,date);
        } else if (type == 1){
            billService.addBillToUser(topic, description, cost, date, userId);
        }
        return new ModelAndView("redirect:/enterprise/bill-unpaid");
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public ModelAndView update(@PathVariable Long id,
                               @RequestParam String topic,
                               @RequestParam String description,
                               @RequestParam Double cost){
        Bill bill = new Bill(id, topic, description, cost);
        billService.updateBill(bill);
        return new ModelAndView("redirect:/bill/billDetail-en/{id}");
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView updateStatus(@PathVariable Long id){
        Bill bill = billService.getBillById(id);
        Date date = new Date();
        bill.setCostTime(date);
        bill.setStatus(1);
        billService.updateBill(bill);
        return new ModelAndView("redirect:/enterprise/bill-paid");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable Long id){
        billService.deleteBill(id);
        return new ModelAndView("redirect:/enterprise/bill-paid");
    }

    @RequestMapping(value = "/retrieve", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView retrieve(
            @RequestParam("userId") Long userId,
            @RequestParam("keyword") String keyword){
        ModelAndView modelAndView = new ModelAndView();
        List<Bill> unfinishedBill = billService.queryBill(keyword, userId);
        modelAndView.setViewName("/user/bill-unfinished");
        modelAndView.addObject("unfinishedBill", unfinishedBill);
        return modelAndView;
    }

}
