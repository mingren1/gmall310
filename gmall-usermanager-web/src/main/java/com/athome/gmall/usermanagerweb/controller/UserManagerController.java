package com.athome.gmall.usermanagerweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserManagerController {

    @RequestMapping("index")
    public String index(HttpServletRequest request) {

        String originURL = request.getParameter("originURL");
        request.setAttribute("originURL",originURL);

        return "index";
    }

    @RequestMapping("verify")
    @ResponseBody
    public String verify(HttpServletRequest request) {

        String token = request.getParameter("token");
        String salt = request.getHeader("X-forwarded-for");

        return "";
    }

    @RequestMapping("login")
    @ResponseBody
    public String login(HttpServletRequest request) {

        return "";
    }
}
