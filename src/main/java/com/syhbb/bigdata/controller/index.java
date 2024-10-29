package com.syhbb.bigdata.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class index {
    @GetMapping("/index")
    public String index(){
        return "console";
    }
}
