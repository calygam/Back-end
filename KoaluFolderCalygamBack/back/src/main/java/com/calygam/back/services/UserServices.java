package com.calygam.back.services;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.calygam.back.repositories.UserRepository;

@Service
public class UserServices {
	
	@Autowired
	private UserRepository repo;
	public Boolean userExistentInMyBaseService(String email,String Cpf)throws Exception {
		return repo.userExistentInMyBase(email, Cpf);
	}
	
	public ResponseEntity<?> CreateOneNewUserInMyBaseService(String userName,String userEmail,String userHashPassword,String userTelefone,String userCpf,String userRank,String userRole)throws Exception{
		return repo.CreateOneNewUserInMyBase(userName, userEmail, userHashPassword, userTelefone, userCpf, userRank, userRole);
	}
}
