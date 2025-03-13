package com.calygam.back.controllers;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calygam.back.dtos.AuthenticationDTO;
import com.calygam.back.dtos.LoginResponseDTO;
import com.calygam.back.dtos.RegisterDTO;
import com.calygam.back.models.UserEntity;
import com.calygam.back.services.TokenService;
import com.calygam.back.services.UserServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthorizationController {
	@Autowired
	private UserServices service;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	
	@PostMapping("/register")
	public ResponseEntity<?> CreateOneNewUserInMyBase(@RequestBody RegisterDTO data)throws Exception{
		if(service.userExistentInMyBaseService(data.getUserEmail(),data.getUserCpf())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já está cadastrado!");
		}else {
			String encrypitedPassword = new BCryptPasswordEncoder().encode(data.getUserPassword());

			return 	service.CreateOneNewUserInMyBaseService(
					data.getUserName(),
					data.getUserEmail(),
					encrypitedPassword,
					data.getUserTelefone(),
					data.getUserCpf(),
					
					data.getUserRank().BRONZEI.name(),
					data.getUserRole().ALUNO.name());
		
					
		}
				
	}
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody AuthenticationDTO data) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(data.getUserEmail(), data.getUserPassword());
		var auth = this.authenticationManager.authenticate(usernamePassword);
		var token = tokenService.generateToken((UserEntity) auth.getPrincipal());
		
		return  ResponseEntity.ok(new LoginResponseDTO(token));
	}
}
