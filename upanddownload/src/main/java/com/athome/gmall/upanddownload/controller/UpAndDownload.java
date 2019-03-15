package com.athome.gmall.upanddownload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UpAndDownload {

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

}
