package com.kiwoom.demo.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping(value = "/home") 
	public String home(Model model) throws Exception{ 
		return "/home/home";
	}
}
