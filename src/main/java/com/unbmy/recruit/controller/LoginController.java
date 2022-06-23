package com.unbmy.recruit.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.unbmy.recruit.pojo.Account;
import com.unbmy.recruit.service.IAdminService;
import com.unbmy.recruit.service.IEnterpriseService;
import com.unbmy.recruit.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @author Unbmy
 */
@Controller
public class LoginController {

    @Resource
    private IUserService userService;
    @Resource
    private IEnterpriseService enterpriseService;
    @Resource
    private IAdminService adminService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("identity") String identity){
        ModelAndView modelAndView = new ModelAndView();
        Account account = null;
        System.out.println("收到的数据：" +
                username + password + identity);
        if (StringUtils.isNotEmpty(identity)){
            switch (identity){
                case "user" :{
                    account = userService.getUser(username, password);
                    if (account != null){
                        modelAndView.setViewName("/user/index");
                    }
                    break;
                }
                case "enterprise" :{
                    account = enterpriseService.getEnterprise(username, password);
                    if (account != null){
                        modelAndView.setViewName("/enterprise/index");
                    }
                    break;
                }
                case "admin" :{
                    account = adminService.getAdmin(username, password);
                    if (account != null){
                        modelAndView.setViewName("/admin/index");
                    }
                    break;
                }
                default:{
                    break;
                }
            }
            if (account == null){
                modelAndView.setViewName("/index");
                modelAndView.addObject("login_err_msg", "用户名或密码错误");
            }
        }
        return modelAndView;
    }

}
