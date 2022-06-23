package com.unbmy.recruit.controller;

import com.unbmy.recruit.pojo.Account;
import com.unbmy.recruit.service.IAdminService;
import com.unbmy.recruit.service.IEnterpriseService;
import com.unbmy.recruit.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @author Unbmy
 */
@Controller
public class RegisterController {

    @Resource
    private IUserService userService;
    @Resource
    private IEnterpriseService enterpriseService;

    private int randomCode = (int) ((Math.random()*9+1)*100000);

    @RequestMapping(value = "/userRegister", method = RequestMethod.POST)
    public ModelAndView userRegister(@RequestParam("username") String username,
                                     @RequestParam("password") String password,
                                     @RequestParam("repeatPassword") String repeatPassword,
                                     @RequestParam("email") String email,
                                     @RequestParam("verifyCode") String verifyCode){
        ModelAndView modelAndView = new ModelAndView();
        Account account = null;
        account = userService.getUser(username, null);
        if (account != null || !password.equals(repeatPassword) || !String.valueOf(randomCode).equals(verifyCode)){
            modelAndView.setViewName("/index");
            return modelAndView;
        } else {
            if (userService.addUser(username, password, email) > 0){
                modelAndView.setViewName("/index");
                return modelAndView;
            }
        }
        return modelAndView;
    }

    @RequestMapping(value = "/usernameCheck", method = RequestMethod.GET)
    @ResponseBody
    public String usernameCheck(@RequestParam("username") String username){
        Account account = userService.getUser(username, null);
        if (account != null){
            return "fail";
        } else {
            return "success";
        }
    }

    @RequestMapping(value = "/getVerifyCode", method = RequestMethod.GET)
    @ResponseBody
    public String getVerifyCode(){
        int code = (int) ((Math.random()*9+1)*100000);
        randomCode = code;
        return String.valueOf(code);
    }

    @RequestMapping(value = "/verifyCodeCheck", method = RequestMethod.GET)
    @ResponseBody
    public String VerifyCodeCheck(@RequestParam("verifyCode") String verifyCode){
        if (verifyCode.length() < 6){
            return "fail";
        }
        if (randomCode == Integer.parseInt(verifyCode)){
            return "success";
        } else {
            return "fail";
        }
    }
}
