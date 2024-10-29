package com.starter.app.ui;

import com.starter.app.presentation.AppController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Tag(name = AppController.SIGN_VIEW_TAG)
@Controller
@RequestMapping(value = AppController.SIGN_VIEW_PATH)
@RequiredArgsConstructor
public class SignViewController {

	@Operation(summary = "로그인 화면")
	@GetMapping(name = "로그인 화면", value = "in", produces = MediaType.TEXT_HTML_VALUE)
	public ModelAndView signIn() {
		return new ModelAndView("sign/in/index");
	}

	@Operation(summary = "사용자 회원 로그인 화면")
	@GetMapping(name = "사용자 회원 로그인 화면", value = "in/client-user", produces = MediaType.TEXT_HTML_VALUE)
	public ModelAndView clientUserSignIn() {
		ModelAndView modelAndView = new ModelAndView("sign/in/client-user");
		modelAndView.addObject("appRedirectUri", "/sign/in/issue-auth-token");
		return modelAndView;
	}

	@Operation(summary = "사업자 회원 로그인 화면")
	@GetMapping(name = "사업자 회원 로그인 화면", value = "in/business-user", produces = MediaType.TEXT_HTML_VALUE)
	public ModelAndView businessUserSignIn() {
		return new ModelAndView("sign/in/business-user");
	}

	@Operation(summary = "토큰 발급")
	@GetMapping(name = "토큰 발급", value = "in/issue-auth-token", produces = MediaType.TEXT_HTML_VALUE)
	public ModelAndView issueAuthToken(@RequestParam UUID authTokenId) {
		ModelAndView modelAndView = new ModelAndView("sign/in/issue-auth-token");
		modelAndView.addObject("authTokenId", authTokenId.toString());
		return modelAndView;
	}

}
