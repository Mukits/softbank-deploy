package edu.dss.softbank.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    // handler method to handle home page request
    @GetMapping("/index")
    public String home(){
        return "index";
    }

}
