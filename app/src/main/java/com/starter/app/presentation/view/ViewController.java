package com.starter.app.presentation.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @RequestMapping(name = "홈")
    public String index() {
        return "skin1/index";
    }

    @RequestMapping(name = "path1", value = "/{path1:^(?!api).+$}")
    public String path1(@PathVariable String path1) {
        return "skin1/" + path1;
    }

    @RequestMapping(name = "path2", value = "/{path1:^(?!api).+$}/{path2}")
    public String path2(@PathVariable String path1, @PathVariable String path2) {
        return "skin1/" + path1 + "/" + path2;
    }

}