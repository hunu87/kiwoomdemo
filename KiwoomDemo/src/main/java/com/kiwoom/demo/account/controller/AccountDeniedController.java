package com.kiwoom.demo.account.controller;

import java.security.Principal;

import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountDeniedController {
	
	@Description("접근 거부 시 페이지")
	@GetMapping("access-denied")
	public String accessDinied(Principal principal, Model model) {
		model.addAttribute("name", principal.getName());
		return "/account/access-denied"; 
	}
}
