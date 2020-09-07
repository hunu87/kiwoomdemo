package com.kiwoom.demo.config;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@Configuration
@EnableEncryptableProperties
public class JasyptStringEncryptor {
	@Bean("jasyptStringEncrypt")
    public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        
        config.setProvider(new BouncyCastleProvider());
        config.setPassword("KiwoomKey"); //암호화에 사용할 키 -> 중요
        config.setAlgorithm("PBEWITHSHA256AND128BITAES-CBC-BC"); //사용할 알고리즘
        config.setPoolSize("1");
        
        encryptor.setConfig(config);
        
        return encryptor;
    }
}
