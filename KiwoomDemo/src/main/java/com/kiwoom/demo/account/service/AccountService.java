package com.kiwoom.demo.account.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kiwoom.demo.account.repository.AccountRepository;
import com.kiwoom.demo.account.repository.AuthoritiesRepository;
import com.kiwoom.demo.account.vo.Account;
import com.kiwoom.demo.account.vo.Authority;
import com.kiwoom.demo.account.vo.SecurityUser;

@Service
public class AccountService implements UserDetailsService {
	
	@Autowired
	private AccountRepository accoutRepository;
	
	@Autowired
    private AuthoritiesRepository authoritiesRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public Account createAccount(String username, String password, boolean enabled) {
		Account account = new Account();
		
		account.setUsername(username);
		String encodePassword = passwordEncoder.encode(password);
		account.setPassword(encodePassword);
		account.setEnabled(enabled);
		
		return accoutRepository.save(account);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Account> byUsername = accoutRepository.findByUsername(username);
		Account account = byUsername.orElseThrow(() -> new UsernameNotFoundException(username));
		
		SecurityUser securityUser = new SecurityUser();
		
		securityUser.setUsername(account.getUsername());
		securityUser.setPassword(account.getPassword());
		securityUser.setAuthorities(getAuthorities(username));
		securityUser.setEnabled(true);
		securityUser.setAccountNonExpired(true);
		securityUser.setAccountNonLocked(true);
		securityUser.setCredentialsNonExpired(true);
		
        return securityUser;
	}

	private Collection<? extends GrantedAuthority> getAuthorities(String username) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		List<Authority> authList = authoritiesRepository.findByUsername(username);
        for (Authority authority : authList) {
            authorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
        }
        
        return authorities;
	}
}
