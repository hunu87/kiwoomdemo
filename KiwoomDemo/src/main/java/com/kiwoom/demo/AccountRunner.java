package com.kiwoom.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.kiwoom.demo.account.service.AccountService;
import com.kiwoom.demo.account.vo.Account;

@Component
public class AccountRunner implements ApplicationRunner{
	
	@Autowired
	AccountService accountService; 
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		Account account = accountService.createAccount("hunu87", "kw5000");
		System.out.println(account.getUsername() + "passoword: " + account.getPassword());
	}
}
