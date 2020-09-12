package com.kiwoom.demo.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.kiwoom.demo.test.service.TestService;
import com.kiwoom.demo.test.vo.TestVO;

@Controller
public class TestController {
	
	@Autowired 
	TestService testService;
	
	@RequestMapping(value = "/test") 
	public String test(Model model) throws Exception{ 
		
		List<TestVO> testList = testService.selectTest(); 
		model.addAttribute("list", testList);
		
		return "/test/test";
	}
}
