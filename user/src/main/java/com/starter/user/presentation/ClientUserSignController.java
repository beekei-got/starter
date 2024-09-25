package com.starter.user.presentation;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "user/client/sign", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ClientUserSignController {

    @Hidden
    @GetMapping(name = "사용자 회원 로그인", value = "in")
    public ModelAndView clientUserSignIn(@RequestParam String appRedirectUri) {
        ModelAndView modelAndView = new ModelAndView("client-signin");
        modelAndView.addObject("appRedirectUri", appRedirectUri);
        return modelAndView;
    }

}
