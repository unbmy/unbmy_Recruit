package com.unbmy.recruit.controller;

import com.unbmy.recruit.pojo.Account;
import com.unbmy.recruit.pojo.Request;
import com.unbmy.recruit.service.IRequestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author Unbmy
 */
@Controller
@RequestMapping("/request")
public class RequestController {
    @Resource
    private IRequestService requestService;

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ModelAndView insert(@RequestParam String topic,
                               @RequestParam String host,
                               @RequestParam Integer people,
                               HttpSession session){
        Account account = (Account) session.getAttribute("account");
        Request request = new Request();
        request.setTopic(topic);
        request.setUserId(account.getId());
        request.setHost(host);
        request.setPeople(people);
        requestService.addRequest(request);
        return new ModelAndView("/user/my-housing");
    }

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
