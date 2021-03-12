package com.kiwoom.demo.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	
	@RequestMapping(value = "/home") 
	public String home(@RequestParam String param, Model model) throws Exception{
		log.info("param : " + param);
		return "/home/home";
	}
	
	@RequestMapping("/home/{no}")
	public String pathVariableTest(@PathVariable String no) {
		return "redirect:/home?param=123";
	}
}
