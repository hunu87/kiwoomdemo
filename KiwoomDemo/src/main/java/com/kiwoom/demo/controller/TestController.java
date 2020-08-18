package com.kiwoom.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.kiwoom.demo.service.TestService;
import com.kiwoom.demo.vo.TestVO;


public class TestController {
	
	@Autowired 
	TestService testService;
	
	@RequestMapping(value = "/test") 
	public String test(Model model) throws Exception{ 
		List<TestVO> testList = testService.selectTest(); 
		model.addAttribute("list", testList); 
		return "test"; 
	}
}
