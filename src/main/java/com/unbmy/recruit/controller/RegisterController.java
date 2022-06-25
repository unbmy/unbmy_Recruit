package com.unbmy.recruit.controller;

import com.unbmy.recruit.pojo.Account;
import com.unbmy.recruit.pojo.Enterprise;
import com.unbmy.recruit.pojo.User;
import com.unbmy.recruit.service.IEnterpriseService;
import com.unbmy.recruit.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
                                     @RequestParam("verifyCode") String verifyCode,
                                     HttpServletResponse response) throws IOException {
        Account account = userService.getUser(username, null);
        if (account == null && password.equals(repeatPassword) && String.valueOf(randomCode).equals(verifyCode)){
            userService.addUser(username, password, email);
        }
        response.sendRedirect("/index");
        return new ModelAndView("/index");
    }

    @RequestMapping(value = "/enterpriseRegister", method = RequestMethod.POST)
    public ModelAndView userRegister(@RequestParam("username") String username,
                                     @RequestParam("password") String password,
                                     @RequestParam("repeatPassword") String repeatPassword,
                                     @RequestParam("enterpriseName") String enterpriseName,
                                     @RequestParam("address") String address,
                                     @RequestParam("phone") String phone,
                                     @RequestParam("verifyCode") String verifyCode,
                                     HttpServletResponse response) throws IOException {
        Account account = enterpriseService.getEnterprise(username, null);
        if (account == null && password.equals(repeatPassword) && String.valueOf(randomCode).equals(verifyCode)){
            enterpriseService.addEnterprise(username, password, enterpriseName, address, phone);
        }
        response.sendRedirect("/index");
        return new ModelAndView("/index");
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

    @RequestMapping(value = "/usernameCheck2", method = RequestMethod.GET)
    @ResponseBody
    public String usernameCheck2(@RequestParam("username") String username){
        Account account = enterpriseService.getEnterprise(username, null);
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
    public String verifyCodeCheck(@RequestParam("verifyCode") String verifyCode){
        if (verifyCode.length() < 6){
            return "fail";
        }
        if (String.valueOf(randomCode).equals(verifyCode)){
            return "success";
        } else {
            return "fail";
        }
    }

    @RequestMapping("/enterprise/modify-phone/modify")
    public ModelAndView modifyPhoneAction(HttpSession session,
                                          @RequestParam String phone,
                                          @RequestParam String verifyCode) {
        Enterprise enterprise = (Enterprise) session.getAttribute("account");
        if (String.valueOf(randomCode).equals(verifyCode)){
            enterprise.setPhone(phone);
            enterpriseService.updateEnterprise(enterprise);
        }
        return new ModelAndView("redirect:/enterprise/modify-phone");
    }

    @RequestMapping("/enterprise/modify-password/modify")
    public ModelAndView modifyPasswordActionEn(HttpSession session,
                                             @RequestParam String password,
                                             @RequestParam String repeatPassword,
                                             @RequestParam String verifyCode) {
        Enterprise enterprise = (Enterprise) session.getAttribute("account");
        if (enterprise!= null && password.equals(repeatPassword) && String.valueOf(randomCode).equals(verifyCode)){
            enterprise.setPassword(password);
            enterpriseService.updateEnterprise(enterprise);
        }
        return new ModelAndView("redirect:/index");
    }

    @RequestMapping("/user/modify-email/modify")
    public ModelAndView modifyEmailAction(HttpSession session,
                                          HttpServletResponse response,
                                          @RequestParam String email,
                                          @RequestParam String verifyCode) throws IOException {
        User user = (User) session.getAttribute("account");
        if (String.valueOf(randomCode).equals(verifyCode)){
            user.setEmail(email);
            userService.updateUser(user);
        }
        response.sendRedirect("/user/modify-email");
        return new ModelAndView("/user/modify-email");
    }

    @RequestMapping("/user/modify-password/modify")
    public ModelAndView modifyPasswordAction(HttpSession session,
                                             HttpServletResponse response,
                                             @RequestParam String password,
                                             @RequestParam String repeatPassword,
                                             @RequestParam String verifyCode) throws IOException {
        User user = (User) session.getAttribute("account");
        if (user!= null && password.equals(repeatPassword) && String.valueOf(randomCode).equals(verifyCode)){
            user.setPassword(password);
            userService.updateUser(user);
        }
        response.sendRedirect("/index");
        return new ModelAndView("/index");
    }

}
