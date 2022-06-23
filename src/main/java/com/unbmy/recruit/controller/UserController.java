package com.unbmy.recruit.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Unbmy
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("/user/index");
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
