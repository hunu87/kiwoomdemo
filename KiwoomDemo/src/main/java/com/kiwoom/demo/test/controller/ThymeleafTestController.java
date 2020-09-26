package com.kiwoom.demo.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kiwoom.demo.test.service.TestService;
import com.kiwoom.demo.test.vo.TestVO;

@Controller
public class ThymeleafTestController {
	
	@Autowired 
	TestService testService;
	
	@GetMapping("/thymeleaf")
	public String thymeleafTest(Model model) {
		
		String username = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		List<TestVO> userList = testService.selectTest(); 
		
		System.out.println("userList : " + userList);
		
		model.addAttribute("username", username);
		model.addAttribute("userList", userList);
		model.addAttribute("message", "Hello, Hyunwoo!!");
		
		return "/thymeleaf/main";
	}
}
