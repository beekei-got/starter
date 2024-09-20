package com.starter.user.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class LoginController {

    @GetMapping(name = "사용자 회원 로그인", value = "client/login")
    public ModelAndView clientUserLogin(@RequestParam String appRedirectUri) {
        ModelAndView modelAndView = new ModelAndView("client-login");
        modelAndView.addObject("appRedirectUri", appRedirectUri);
        return modelAndView;
    }

}
