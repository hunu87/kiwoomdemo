package com.kiwoom.demo.account.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountDeniedController {
	
	@GetMapping("access-denied")
	public String accessDinied(Principal principal, Model model) {
		model.addAttribute("name", principal.getName());
		return "/account/access-denied";
	}
}
