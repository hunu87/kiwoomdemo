package com.kiwoom.demo.security;

import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
		RequestCache requestCache = new HttpSessionRequestCache();
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		String redirectUrl = "";
		
		if(savedRequest == null) {
			log.info("savedRequest is null");
			redirectUrl = getRedirectUrl(authentication);
		}else {
			if(savedRequest.getRedirectUrl().contains("error")) {
				redirectUrl = getRedirectUrl(authentication);
			}else	{
				redirectUrl = savedRequest.getRedirectUrl();
			}
		}
		
		redirectStrategy.sendRedirect(request, response, redirectUrl);
	}
	
	private String getRedirectUrl(Authentication authentication) {
		AtomicReference<String> redirectUrl = new AtomicReference<>();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		authorities.forEach(role -> {
			switch (role.toString()) {
			case "USER":
				redirectUrl.set("/");
				break;

			default:
				break;
			}
		});
		
		return redirectUrl.get();
	}
}
