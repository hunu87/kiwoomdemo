package com.kiwoom.demo.redis.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiwoom.demo.redis.service.GetSetService;

@RestController
public class RedisController {
	
	@Autowired
	private GetSetService getSetService;
	
	@GetMapping("/test/redis")
	public String test() {
		return this.getSetService.test();
	}
	
	@GetMapping("/test/redisSession")
	public String test2(HttpSession httpSession) {
		httpSession.setAttribute("redis", "session");
		return "redis session";
	}
}
