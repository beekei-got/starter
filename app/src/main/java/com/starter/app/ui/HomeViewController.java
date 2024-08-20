package com.starter.app.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeViewController {

    @RequestMapping(name = "홈")
    public String home() {
        return "index";
    }

}
