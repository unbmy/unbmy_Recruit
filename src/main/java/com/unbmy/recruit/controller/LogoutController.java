package com.unbmy.recruit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Unbmy
 */
@Controller
public class LogoutController {

    @RequestMapping("/logout")
    public ModelAndView logout(HttpSession session,
                               HttpServletResponse response) throws IOException {
        session.invalidate();
        response.sendRedirect("/index");
        return new ModelAndView("/index");
    }

}
