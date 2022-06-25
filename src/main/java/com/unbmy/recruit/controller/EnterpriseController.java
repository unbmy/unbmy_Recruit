package com.unbmy.recruit.controller;

import com.unbmy.recruit.pojo.Account;
import com.unbmy.recruit.pojo.Bill;
import com.unbmy.recruit.pojo.Maintenance;
import com.unbmy.recruit.pojo.Notice;
import com.unbmy.recruit.service.IMaintenanceService;
import com.unbmy.recruit.service.INoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @author Unbmy
 */
@Controller
@RequestMapping("/enterprise")
public class EnterpriseController {
    @Resource
    private INoticeService noticeService;
    @Resource
    private IMaintenanceService maintenanceService;

    @RequestMapping("/index")
    public ModelAndView index(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        Account account = (Account) session.getAttribute("account");
        modelAndView.setViewName("/enterprise/index");
        return modelAndView;
    }

    @RequestMapping("/all-notice")
    public ModelAndView allNotice(){
        ModelAndView modelAndView = new ModelAndView();
        List<Notice> allNoticeList = noticeService.getAllNotice();
        modelAndView.addObject("allNoticeList", allNoticeList);
        modelAndView.setViewName("/enterprise/all-notice");
        return modelAndView;
    }

    @RequestMapping("/add-notice")
    public ModelAndView addNoticeHtml(){
        return new ModelAndView("/enterprise/add-notice");
    }

    @RequestMapping("/add-notice/insert")
    public ModelAndView addNoticeAction(@RequestParam String topic,
                                        @RequestParam String content){
        Date date = new Date();
        noticeService.addNotice(topic, content, date);
        return new ModelAndView("redirect:/enterprise/add-notice");
    }

    @RequestMapping("/handledMaintenance")
    public ModelAndView handledMaintenance(){
        ModelAndView modelAndView = new ModelAndView();
        List<Maintenance> allCompleteMaintenance = maintenanceService.getAllCompleteMaintenance();
        modelAndView.addObject("allCompleteMaintenance", allCompleteMaintenance);
        modelAndView.setViewName("/enterprise/handledMaintenance");
        return modelAndView;
    }

    @RequestMapping("/unhandledMaintenance")
    public ModelAndView unhandledMaintenance(){
        ModelAndView modelAndView = new ModelAndView();
        List<Maintenance> allUnhandledMaintenance = maintenanceService.getAllUnhandledMaintenance();
        modelAndView.addObject("allUnhandledMaintenance", allUnhandledMaintenance);
        modelAndView.setViewName("/enterprise/unhandledMaintenance");
        return modelAndView;
    }


}
