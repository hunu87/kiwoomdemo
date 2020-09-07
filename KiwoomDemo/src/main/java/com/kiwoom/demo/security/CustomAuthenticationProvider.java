package com.kiwoom.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.kiwoom.demo.account.service.AccountService;
import com.kiwoom.demo.account.vo.SecurityUser;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        
        SecurityUser user = (SecurityUser)accountService.loadUserByUsername(username);
        
        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("비밀번호가 맞지 않습니다.");
        }
 
        if(!user.isEnabled()) {
            throw new BadCredentialsException("계정이 비활성화되었습니다.");
        }
        
        return new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}
}
