package com.calygam.back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.calygam.back.repositories.UserAuthRepository;

@Service
public class AuthorizationService implements UserDetailsService {
	@Autowired
	private UserAuthRepository userAuthRepo;

	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		try {
        return userAuthRepo.findByUserEmail(userEmail);
		}catch(Exception e) {
			e.printStackTrace();
			
		}
	      return null;
	}

}
