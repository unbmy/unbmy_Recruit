package com.unbmy.recruit.controller;

import com.unbmy.recruit.pojo.Request;
import com.unbmy.recruit.service.IRequestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @author Unbmy
 */
@Controller
@RequestMapping("/request")
public class RequestController {
    @Resource
    private IRequestService requestService;

    @RequestMapping("/requestDetail-en/{id}")
    public ModelAndView requestDetailEn(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView();
        Request request = requestService.getRequestById(id);
        modelAndView.addObject("request", request);
        modelAndView.setViewName("/request/requestDetail-en");
        return modelAndView;
    }

    @RequestMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id){
        requestService.deleteRequest(id);
        return new ModelAndView("redirect:/enterprise/add-housing");
    }

}
