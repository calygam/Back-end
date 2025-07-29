package com.calygam.back.configs;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.calygam.back.repositories.UserAuthRepository;
import com.calygam.back.services.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class MySecurityFilter extends OncePerRequestFilter {
	@Autowired
	private UserAuthRepository userAuthRepo;
	
	@Autowired 
	private TokenService tokenService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String path = request.getRequestURI();
		
		if(path.equals("/auth/register")|| path.equals("/auth/login") ||path.startsWith("/file/read/user/")||path.startsWith("/file/read")||path.startsWith("/file")|| path.equals("/auth/google") || path.equals("/auth/google/callback")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		var token = this.recoverToken(request);
		
		if(token !=null) {
			var email = tokenService.validateToken(token);
			UserDetails user = userAuthRepo.findByUserEmail(email);
			
			var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		filterChain.doFilter(request, response);
		
	}
	private String recoverToken(HttpServletRequest request) {
		var authHeader = request.getHeader("Authorization");
		if(authHeader == null) return null;
		return authHeader.replace("Bearer ","");
	}

}
