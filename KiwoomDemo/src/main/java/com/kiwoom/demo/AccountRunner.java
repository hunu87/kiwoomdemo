package com.kiwoom.demo;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import com.kiwoom.demo.account.service.AccountService;

@Component
public class AccountRunner implements ApplicationRunner{
	
	@Autowired
	AccountService accountService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// accountService.createAccount("hunu87", "kw5000", true);
		
		/*
		StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
		pbeEnc.setProvider(new BouncyCastleProvider());
		pbeEnc.setAlgorithm("PBEWITHSHA256AND128BITAES-CBC-BC");
		pbeEnc.setPassword("KiwoomKey"); //2번 설정의 암호화 키를 입력
		
		String enc = pbeEnc.encrypt("pass"); //암호화 할 내용
		System.out.println("enc = " + enc); //암호화 한 내용을 출력
		
		//테스트용 복호화
		String des = pbeEnc.decrypt(enc);
		System.out.println("des = " + des);
		*/
	}
}
