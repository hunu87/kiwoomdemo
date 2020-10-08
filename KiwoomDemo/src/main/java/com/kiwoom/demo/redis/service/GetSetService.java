package com.kiwoom.demo.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.kiwoom.demo.redis.dto.DataData;

@Service
public class GetSetService {
	
	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	
	public String test() {
		//get,set을 위한 객체
		ValueOperations<String, Object> vop = redisTemplate.opsForValue();
		
		DataData setData = new DataData();
		setData.setItemId("item");
		setData.setSourceId("hunu87");
		
		vop.set("key", setData);
		
		DataData getData = (DataData)vop.get("key");
		String result = getData.getItemId() + " : " + getData.getSourceId();
		
		return result;
	}
}
