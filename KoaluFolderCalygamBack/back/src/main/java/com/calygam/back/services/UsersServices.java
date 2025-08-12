package com.calygam.back.services;


import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calygam.back.dtos.DataUtilUserDTO;
import com.calygam.back.dtos.EditCredentialsDTO;
import com.calygam.back.dtos.RegisterDTO;
import com.calygam.back.dtos.RegisterResponseDTO;
import com.calygam.back.enums.UserRankEnum;
import com.calygam.back.enums.UserRoleEnum;
import com.calygam.back.enums.UserStatus;
import com.calygam.back.exceptions.MissingMinRolesException;
import com.calygam.back.exceptions.SoftNotFoundException;
import com.calygam.back.exceptions.UserAlreadExistsException;
import com.calygam.back.exceptions.UserNotIdentifiedException;
import com.calygam.back.exceptions.UserServiceException;
import com.calygam.back.mappers.UserMappers;
import com.calygam.back.models.UserEntity;
import com.calygam.back.projections.AdminAnalisisProjection;
import com.calygam.back.projections.TeacherDashProjection;
import com.calygam.back.repositories.UserAuthRepository;
import com.calygam.back.repositories.UsersRepository;
import com.calygam.back.sucesshandlers.ApiSucessHandler;
import com.calygam.back.utils.MakeUploadAndDownloadArchive;



@Service
public class UsersServices {
	
	@Autowired
	private UserAuthRepository userAuthRepository;
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private UserMappers userMappers;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private JwtUtilsId jwtUtilsId;
	
	@Autowired
	private MakeUploadAndDownloadArchive makeUploadAndDownloadArchive;
	
	
public ApiSucessHandler<String> EditCredentialsUser(Long userId,EditCredentialsDTO editCredentialsDTO) throws IOException {
		if(!editCredentialsDTO.getUserEmail().isEmpty() && editCredentialsDTO.getUserEmail() !=null) {
		if(userAuthRepository.findByUserEmail(editCredentialsDTO.getUserEmail())!=null) {
			throw new UserAlreadExistsException("Usuário já existe na base -> E-mail!");
		}
		}
		UserEntity userEntity = usersRepository.findById(userId).orElseThrow(()-> new SoftNotFoundException("Usuário não encontrado!"));
		if(editCredentialsDTO.getUserName()!=null) {
			userEntity.setUserName(editCredentialsDTO.getUserName());
		}
		
		
	
		if(editCredentialsDTO.getUserEmail()!=null &&!editCredentialsDTO.getUserEmail().isEmpty()) {
			
		
		userEntity.setUserEmail(editCredentialsDTO.getUserEmail());
		}
		if(editCredentialsDTO.getUserPassword()!=null && !editCredentialsDTO.getUserPassword().isEmpty()) {
			
		
		String oldPurePassword = editCredentialsDTO.getUserPassword();
		if(encoder.matches(oldPurePassword,userEntity.getPassword())  ) {
			String newHashedPassword = encoder.encode(editCredentialsDTO.getUserNewPassword());
			userEntity.setUserPassword(newHashedPassword);
		}else {
			throw new MissingMinRolesException("Senhas não condizem");
		}
		}
		if(editCredentialsDTO.getUserMultipartFile() !=null && !editCredentialsDTO.getUserMultipartFile().isEmpty() ) {
			if(userEntity.getArchiveName()!=null &&userEntity.getArchiveName()!="") {
				
				makeUploadAndDownloadArchive.deleteFile(userEntity.getArchiveName());
				
				makeUploadAndDownloadArchive.saveArchive(editCredentialsDTO.getUserMultipartFile(),userEntity,usersRepository)
				;
			}else {
				makeUploadAndDownloadArchive.saveArchive(editCredentialsDTO.getUserMultipartFile(),userEntity,usersRepository);
			}
			
		}else {
			usersRepository.save(userEntity);
		}
	    
		return new ApiSucessHandler<String>(true, "Credenciais modificadas com sucesso!", null);	
	}
	
	
	
	
	
	
	
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
		userEntity.setUserMoney(0L);
		userEntity.setUserStatus(UserStatus.ACTIVE);
		Long xp = 0L;
		userEntity.setXp(xp);
		userEntity.setUserFood(0L);
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
		Optional<DataUtilUserDTO> dto = usersRepository.findByUserId(userId);
		dto.ifPresent(DataUtilUserDTO::generateImageUrl);
	    return dto;
	}
	
	public Page<DataUtilUserDTO> ReadInfoUsersByRole(Pageable pageable){

		Page<TeacherDashProjection> teacherPages = usersRepository.findTeachersByRole(pageable);
				
	    return teacherPages.map(tea-> userMappers.toUserDTORefact(tea));
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
			user.setUserFood(0L);
			user.setUserEmail(email);
			user.setUserStatus(UserStatus.ACTIVE);
			user.setuserProviderId(id);
		//	user.setuserImagePerfil(picture);
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
			if(userIdentified==null ) {
				throw new UserNotIdentifiedException("Usuário não encontrado");
			}
			if(userIdentified.getUserRole().ordinal()==2) {
			   throw new  UserAlreadExistsException("Este usuário já é um professor");
			}else {
				userIdentified.setUserRole(UserRoleEnum.INSTRUTOR);
				usersRepository.save(userIdentified);
				return ResponseEntity.status(HttpStatus.OK).body("Designado o titulo de professor = novo membro ao time");
			}
			
			
	}
	public ResponseEntity<String> removeTeacherOfProject(String email) {
		
		UserEntity userIdentified = userAuthRepository.findByUserEmail(email);
		if(userIdentified==null ) {
			throw new UserNotIdentifiedException("Usuário não encontrado");
		}
		if(userIdentified.getUserRole().ordinal()!=2) {
		   throw new  UserNotIdentifiedException("Este usuário não é um professor");
		}else {
			userIdentified.setUserRole(UserRoleEnum.ALUNO);
			usersRepository.save(userIdentified);
			return ResponseEntity.status(HttpStatus.OK).body("Um professor deixou seu cargo");
		}
		
		
}
	
	
	
	
	
	
	
}
