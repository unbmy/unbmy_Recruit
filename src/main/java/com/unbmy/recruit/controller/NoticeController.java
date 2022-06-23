package com.unbmy.recruit.controller;

import com.unbmy.recruit.pojo.Notice;
import com.unbmy.recruit.service.INoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @author Unbmy
 */
@Controller
@RequestMapping("/notice")
public class NoticeController {
    @Resource
    private INoticeService noticeService;

    @RequestMapping("/noticeDetail/{id}")
    public ModelAndView noticeDetail(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView();
        Notice notice = noticeService.getNoticeById(id);
        modelAndView.addObject("notice", notice);
        modelAndView.setViewName("/notice/noticeDetail");
        return modelAndView;
    }

}
