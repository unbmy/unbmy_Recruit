package com.unbmy.recruit.controller;


import com.unbmy.recruit.pojo.Notice;
import com.unbmy.recruit.service.INoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Unbmy
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private INoticeService noticeService;

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


    @RequestMapping("/maintenance-finish")
    public ModelAndView maintenanceFinish(){
        return new ModelAndView("/user/maintenance-finish");
    }

    @RequestMapping("/maintenance-not-finish")
    public ModelAndView maintenanceNotFinish(){
        return new ModelAndView("/user/maintenance-not-finish");
    }

}
