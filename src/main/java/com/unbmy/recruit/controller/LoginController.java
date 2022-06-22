package com.unbmy.recruit.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.unbmy.recruit.pojo.Account;
import com.unbmy.recruit.service.IUserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@RestController
public class LoginController {

    @Resource
    private IUserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("identity") String identity){
        Account account = null;
        System.out.println(username + password + identity);
        if (StringUtils.isNotEmpty(identity)){
            switch (identity){
                case "user" :{
                    account = userService.getUser(username, password);
                    return new ModelAndView("/user/index");
                }
                case "enterprise" :{
                    userService.getUser(username, password);
                    break;
                }
                case "admin" :{
                    userService.getUser(username, password);
                    break;
                }
                default:{
                    break;
                }
            }
        }
        return null;
    }

}
