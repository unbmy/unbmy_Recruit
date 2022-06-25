package com.unbmy.recruit.controller;

import com.unbmy.recruit.pojo.Notice;
import com.unbmy.recruit.service.INoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Date;

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

    @RequestMapping("/noticeDetail-en/{id}")
    public ModelAndView noticeDetailEn(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView();
        Notice notice = noticeService.getNoticeById(id);
        modelAndView.addObject("notice", notice);
        modelAndView.setViewName("/notice/noticeDetail-en");
        return modelAndView;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public ModelAndView update(@PathVariable Long id,
                               @RequestParam String topic,
                               @RequestParam String content){
        Date date = new Date();
        Notice notice = new Notice(id, topic, content, date);
        noticeService.updateNotice(notice);
        return new ModelAndView("redirect:/notice/noticeDetail-en/{id}");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable Long id){
        noticeService.deleteNotice(id);
        return new ModelAndView("redirect:/enterprise/all-notice");
    }

}
