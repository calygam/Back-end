package com.calygam.back.services;


import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calygam.back.dtos.DataUtilUserDTO;
import com.calygam.back.dtos.RegisterDTO;
import com.calygam.back.dtos.RegisterResponseDTO;
import com.calygam.back.enums.UserRankEnum;
import com.calygam.back.enums.UserRoleEnum;
import com.calygam.back.enums.UserStatus;
import com.calygam.back.exceptions.UserAlreadExistsException;
import com.calygam.back.exceptions.UserServiceException;
import com.calygam.back.mappers.UserMappers;
import com.calygam.back.models.UserEntity;
import com.calygam.back.projections.AdminAnalisisProjection;
import com.calygam.back.projections.TeacherDashProjection;
import com.calygam.back.repositories.UserAuthRepository;
import com.calygam.back.repositories.UsersRepository;



@Service
public class UsersServices {
	
	@Autowired
	private UserAuthRepository userAuthRepository;
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private UserMappers userMappers;
	
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
		userEntity.setUserCpf(null);
		userEntity.setUserMoney(0L);
		userEntity.setUserStatus(UserStatus.ACTIVE);
		Long xp = 0L;
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
		Optional<DataUtilUserDTO> userResponseDTO = usersRepository.findByUserId(userId);
	    return userResponseDTO;
	}
	
	public Page<DataUtilUserDTO> ReadInfoUsersByRole(Pageable pageable){

		Page<TeacherDashProjection> teacherPages = usersRepository.findTeachersByRole(pageable);
				
	    return teacherPages.map(tea-> userMappers.toDTO(tea));
	}
	

	
	
	//caio<- COM O GOOGLE 
	
	@Transactional
	public UserEntity createGoogleUserService(String email, String name, String id, String picture) throws UserServiceException {
		try {
			Long xp = 0L;
			UserEntity user = new UserEntity();
			user.setUserName(name);
			
			user.setXp(xp);
			user.setUserRole(UserRoleEnum.ALUNO);
			user.setUserMoney(0L);
			user.setUserEmail(email);
			user.setUserStatus(UserStatus.ACTIVE);
			user.setuserProviderId(id);
			user.setuserImagePerfil(picture);
			return usersRepository.save(user);
		} catch (Exception e) {
			throw new UserServiceException("Erro ao criar usuário Google", e);
		}
	}
	
	public UserEntity readUserGoogleService(String email) throws UserServiceException {
		try {
			UserEntity userIdentified = userAuthRepository.findByUserEmail(email);
			if(userIdentified==null) {
				throw new UserAlreadExistsException("Usuário não enontrado");
			}
			return userIdentified;
		} catch (Exception e) {
			throw new UserServiceException("Erro ao ler usuário Google", e);
		}
	}
	
	public AdminAnalisisProjection getTotalAnalisisAdminService() {
		return usersRepository.getTotalAnalisisAdmin();
	}
	
	public ResponseEntity<String> assignTeacherToProject(String email) {
		
			UserEntity userIdentified = userAuthRepository.findByUserEmail(email);
			if(userIdentified==null) {
				throw new UserAlreadExistsException("Usuário não enontrado");
			}
			if(userIdentified.getUserRole().ordinal()==2) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Este usuário já é um professor");
			}else {
				userIdentified.setUserRole(UserRoleEnum.INSTRUTOR);
				usersRepository.save(userIdentified);
				return ResponseEntity.status(HttpStatus.OK).body("Designado o titulo de professor = novo membro ao time");
			}
			
			
	}
	
	
	
	
	
	
}
