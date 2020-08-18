package com.kiwoom.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiwoom.demo.mapper.TestMapper;
import com.kiwoom.demo.vo.TestVO;

@Service 
public class TestService {
	
	@Autowired 
	public TestMapper mapper;
	
	public List<TestVO> selectTest() { 
		return mapper.selectTest(); 
	} 
}

