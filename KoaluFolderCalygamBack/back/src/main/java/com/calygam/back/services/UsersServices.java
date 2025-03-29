package com.calygam.back.services;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.calygam.back.dtos.DataUtilUserDTO;
import com.calygam.back.dtos.RegisterDTO;
import com.calygam.back.dtos.RegisterResponseDTO;
import com.calygam.back.enums.UserRankEnum;
import com.calygam.back.enums.UserRoleEnum;
import com.calygam.back.exceptions.UserAlreadExistsException;
import com.calygam.back.models.UserEntity;
import com.calygam.back.repositories.UserAuthRepository;
import com.calygam.back.repositories.UsersRepository;


@Service
public class UsersServices {
	
	@Autowired
	private UserAuthRepository userAuthRepository;
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private JwtUtilsId jwtUtilsId;
	
	
	
	public RegisterResponseDTO CreateANewUser(RegisterDTO registerDTO) {
		
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
		Integer xp = 0;
		userEntity.setXp(xp);
		userEntity.setUserRole(UserRoleEnum.ALUNO);
		String encrypitedPassword = new BCryptPasswordEncoder().encode(registerDTO.getUserPassword());
		userEntity.setUserPassword(encrypitedPassword);
		
		
		userEntity = userAuthRepository.save(userEntity);
		
		RegisterResponseDTO userResponseDTO = new RegisterResponseDTO();
		
		userResponseDTO.setId(userEntity.getUserId());
		userResponseDTO.setUserName(userEntity.getUsername());
		userResponseDTO.setUserCpf(userEntity.getUserCpf());
		userResponseDTO.setUserEmail(userEntity.getUserEmail());
	    String userRank = UserRankEnum.getRankForXpToString(userEntity.getXp());
	    userResponseDTO.setUserRank(userRank);
		
		return userResponseDTO;
		
		
		
	}
	
	public Optional<DataUtilUserDTO> ReadInfoUserByIdService(String token){
		Long userId = jwtUtilsId.getUserIdFromToken(token);
		
		return usersRepository.findByUserId(userId);
		
	}
	
	
}
