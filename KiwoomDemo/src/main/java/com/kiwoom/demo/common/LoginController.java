package com.kiwoom.demo.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping("loginPage")
	public String login() {
		return "login";
	}
}
