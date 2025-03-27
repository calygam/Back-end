package com.calygam.back.services;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.calygam.back.dtos.RegisterDTO;
import com.calygam.back.enums.UserRankEnum;
import com.calygam.back.enums.UserRoleEnum;
import com.calygam.back.exceptions.UserAlreadExistsException;
import com.calygam.back.models.UserEntity;
import com.calygam.back.repositories.UserAuthRepository;

@Service
public class UsersServices {
	
	@Autowired
	private UserAuthRepository userAuthRepository;
	
	
	
	public RegisterDTO CreateANewUser(RegisterDTO registerDTO) {
		
		if(userAuthRepository.findByUserEmail(registerDTO.getUserEmail())!=null) {
			throw new UserAlreadExistsException("Usuário já existe na base -> E-mail!");
		}
		if(userAuthRepository.findByUserCpf(registerDTO.getUserCpf())!=null) {
			throw new UserAlreadExistsException("Usuário já existe na base -> CPF!");
		}
		UserEntity userEntity = new UserEntity();
		
		userEntity.setUserName(registerDTO.getUserName());
		userEntity.setUserEmail(registerDTO.getUserEmail());
		userEntity.setUserCpf(registerDTO.getUserCpf());
		userEntity.setUserMoney(new BigInteger("0"));
		userEntity.setUserRank(UserRankEnum.BRONZEI);
		userEntity.setUserRole(UserRoleEnum.ALUNO);
		String encrypitedPassword = new BCryptPasswordEncoder().encode(registerDTO.getUserPassword());
		userEntity.setUserPassword(encrypitedPassword);
		
		
		userEntity = userAuthRepository.save(userEntity);
		
		RegisterDTO userResponseDTO = new RegisterDTO();
		
		userResponseDTO.setId(userEntity.getUserId());
		userResponseDTO.setUserCpf(userEntity.getUserCpf());
		userResponseDTO.setUserEmail(userEntity.getUserEmail());
		
		return userResponseDTO;
		
		
		
	}
}
