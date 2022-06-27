package com.unbmy.recruit.controller;

import com.unbmy.recruit.pojo.Account;
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

    @RequestMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("/index");
    }

    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    public ModelAndView userLogin(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpSession session,
            HttpServletResponse response) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        Account account = userService.getUser(username, password);
        if (account == null){
            modelAndView.setViewName("/index");
            modelAndView.addObject("login_err_msg", "用户名或密码错误");
            return modelAndView;
        }
        session.setAttribute("account", account);
        response.sendRedirect("/user/index");
        return null;
    }

    @RequestMapping(value = "/enterpriseLogin", method = RequestMethod.POST)
    public ModelAndView enterpriseLogin(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpSession session,
            HttpServletResponse response) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        Account account = enterpriseService.getEnterprise(username, password);
        if (account == null){
            modelAndView.setViewName("/index");
            modelAndView.addObject("login_err_msg", "用户名或密码错误");
            return modelAndView;
        }
        session.setAttribute("account", account);
        response.sendRedirect("/enterprise/index");
        return null;
    }

}
