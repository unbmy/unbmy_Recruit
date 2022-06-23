package com.unbmy.recruit.controller;


import com.unbmy.recruit.pojo.Account;
import com.unbmy.recruit.pojo.Maintenance;
import com.unbmy.recruit.pojo.Notice;
import com.unbmy.recruit.service.IMaintenanceService;
import com.unbmy.recruit.service.INoticeService;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Unbmy
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private INoticeService noticeService;
    @Resource
    private IMaintenanceService maintenanceService;

    public String getUploadPath(){
        ApplicationHome applicationHome = new ApplicationHome(this.getClass());
        String path = "\\src\\main\\resources\\static\\upload";
        System.out.println(applicationHome.getDir()+path);
        return applicationHome.getDir()+path;
    }

    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        List<Notice> latestNoticeList = noticeService.getLatestNotice();
        modelAndView.addObject("latestNoticeList", latestNoticeList);
        modelAndView.setViewName("/user/index");
        return modelAndView;
    }

    @RequestMapping("/all-notice")
    public ModelAndView noticeLatest(){
        ModelAndView modelAndView = new ModelAndView();
        List<Notice> allNoticeList = noticeService.getAllNotice();
        modelAndView.addObject("allNoticeList", allNoticeList);
        modelAndView.setViewName("/user/all-notice");
        return modelAndView;
    }

    @RequestMapping("/maintenance-upload")
    public ModelAndView maintenanceUploadHtml(){
        return new ModelAndView("/user/maintenance-upload");
    }

    @RequestMapping("/maintenance-upload/upload")
    public ModelAndView maintenanceUploadAction(@RequestParam String place,
                                                @RequestParam String description,
                                                @RequestParam MultipartFile file){
        if (file.isEmpty()){

        } else {

        }
        return new ModelAndView("/user/maintenance-upload");
    }

    @RequestMapping("/maintenance-finish")
    public ModelAndView maintenanceFinish(){
        return new ModelAndView("/user/maintenance-finish");
    }

    @RequestMapping("/maintenance-not-finish")
    public ModelAndView maintenanceNotFinish(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        Account account = (Account) session.getAttribute("account");
        List<Maintenance> unhandledMaintenance = maintenanceService.getUnhandledMaintenance(account.getId());
        modelAndView.addObject("unhandledMaintenance", unhandledMaintenance);
        modelAndView.setViewName("/user/maintenance-not-finish");
        return modelAndView;
    }

}
