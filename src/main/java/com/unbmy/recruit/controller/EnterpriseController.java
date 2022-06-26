package com.unbmy.recruit.controller;

import com.unbmy.recruit.pojo.*;
import com.unbmy.recruit.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
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
    @Resource
    private IBillService billService;
    @Resource
    private IHousingService housingService;
    @Resource
    private IRequestService requestService;

    @RequestMapping("/index")
    public ModelAndView index(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        Account account = (Account) session.getAttribute("account");
        modelAndView.setViewName("/enterprise/index");
        return modelAndView;
    }

    @RequestMapping("/all-housing")
    public ModelAndView allHousing(){
        ModelAndView modelAndView = new ModelAndView();
        List<Housing> allHousing = housingService.getAllHousing();
        modelAndView.addObject("allHousing", allHousing);
        modelAndView.setViewName("/enterprise/all-housing");
        return modelAndView;
    }

    @RequestMapping("/add-housing")
    public ModelAndView addHousing(){
        ModelAndView modelAndView = new ModelAndView();
        List<Request> allRequest = requestService.getAllRequest();
        modelAndView.addObject("allRequest", allRequest);
        modelAndView.setViewName("/enterprise/add-housing");
        return modelAndView;
    }

    @RequestMapping("/eval-housing")
    public ModelAndView evalHousing(){
        return new ModelAndView("/enterprise/eval-housing");
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

    @RequestMapping("/bill-paid")
    public ModelAndView billPaid(){
        ModelAndView modelAndView = new ModelAndView();
        List<Bill> allCompletedBill = billService.getAllCompletedBill();
        modelAndView.addObject("allCompletedBill", allCompletedBill);
        modelAndView.setViewName("/enterprise/bill-paid");
        return modelAndView;
    }

    @RequestMapping("/bill-unpaid")
    public ModelAndView billUnpaid(){
        ModelAndView modelAndView = new ModelAndView();
        List<Bill> allUnfinishedBill = billService.getAllUnfinishedBill();
        modelAndView.addObject("allUnfinishedBill", allUnfinishedBill);
        modelAndView.setViewName("/enterprise/bill-unpaid");
        return modelAndView;
    }

    @RequestMapping("/bill-add")
    public ModelAndView billAdd(){
        return new ModelAndView("/enterprise/add-bill");
    }

    @RequestMapping("/modify-phone")
    public ModelAndView modifyPhoneHtml(){
        return new ModelAndView("/enterprise/modify-phone");
    }

    @RequestMapping("/modify-password")
    public ModelAndView modifyPasswordHtml(){
        return new ModelAndView("/enterprise/modify-password");
    }

}
