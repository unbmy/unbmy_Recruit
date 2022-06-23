package com.unbmy.recruit.controller;

import com.unbmy.recruit.pojo.Account;
import com.unbmy.recruit.service.IAdminService;
import com.unbmy.recruit.service.IEnterpriseService;
import com.unbmy.recruit.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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

    @RequestMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("/index");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("identity") String identity,
            HttpSession session, HttpServletResponse response) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        Account account;
        String type;
        System.out.println("收到的数据：" + username + password + identity);
        switch (identity){
            case "user" :{
                account = userService.getUser(username, password);
                type = "user";
                break;
            }
            case "enterprise" :{
                account = enterpriseService.getEnterprise(username, password);
                type = "enterprise";
                break;
            }
            case "admin" :{
                account = adminService.getAdmin(username, password);
                type = "admin";
                break;
            }
            default:{
                return new ModelAndView("/index");
            }
        }
        if (account == null){
            modelAndView.setViewName("/index");
            modelAndView.addObject("login_err_msg", "用户名或密码错误");
            return modelAndView;
        }
        session.setAttribute("account", account);
        response.sendRedirect("/" + type + "/index");
        return new ModelAndView("/index");
    }

}
