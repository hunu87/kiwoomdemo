package com.kiwoom.demo.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiwoom.demo.test.mapper.TestMapper;
import com.kiwoom.demo.test.vo.TestVO;

@Service 
public class TestService {
	
	@Autowired 
	public TestMapper mapper;
	
	public List<TestVO> selectTest() { 
		return mapper.selectTest(); 
	} 
}

