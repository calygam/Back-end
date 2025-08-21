package com.calygam.back.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.calygam.back.dtos.GoogleTokenResponseDTO;
import com.calygam.back.dtos.GoogleUserInfoDTO;
import com.calygam.back.enums.UserRoleEnum;
import com.calygam.back.exceptions.GoogleAuthException;
import com.calygam.back.exceptions.UserNotIdentifiedException;
import com.calygam.back.models.UserEntity;
import com.calygam.back.repositories.UsersRepository;
import com.calygam.back.services.GoogleAuthService;
import com.calygam.back.services.TokenService;
import com.calygam.back.services.UsersServices;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("auth")
public class GoogleOauthController {
	@Autowired
	private GoogleAuthService googleAuthService;
	
	@Autowired
	private UsersServices userService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Value("${spring.security.oauth2.client.registration.google.client-id}")
	private String clientId;
	
	@Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
	private String redirectUri;
	
	@Value("${frontend.url}")
	private String frontendUrl;
	
	@GetMapping("/google")
	public ResponseEntity<Void> goToGoogle(HttpServletResponse response) throws IOException {
		String googleUriMapping = UriComponentsBuilder.fromUriString("https://accounts.google.com/o/oauth2/v2/auth")
				.queryParam("client_id", clientId)
				.queryParam("redirect_uri", redirectUri)
				.queryParam("response_type", "code")
				.queryParam("scope", "email profile")
				.build().toUriString();
		
		response.sendRedirect(googleUriMapping);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("google/callback")
	public ResponseEntity<Void> goToGoogleResponse(@RequestParam("code") String code, HttpServletResponse response) throws GoogleAuthException {
		try {
			// Obter token de acesso do Google
			GoogleTokenResponseDTO tokenResponse = googleAuthService.extractTokenForCode(code);
			
			// Obter informações do usuário do Google
			GoogleUserInfoDTO userGoogleInfo = googleAuthService.collectGoogleUserInfo(tokenResponse.getAcessToken());
			
			// Verificar se o usuário já existe
			UserEntity user;
			
			
			// Criar ou atualizar usuário
			
			System.out.println("""
					///////////////////////////////
					EMAIL DO USER =
					/////////////////////////////////
					""" + userGoogleInfo.getEmail());
			
			if (usersRepository.findEntityByEmail(userGoogleInfo.getEmail()).isEmpty()) {
				user = userService.createGoogleUserService(
					userGoogleInfo.getEmail(),
					userGoogleInfo.getName(),
					userGoogleInfo.getId(),
					userGoogleInfo.getPicture()
				);
			} else {
				user = userService.readUserGoogleService(
					userGoogleInfo.getEmail()
				);
			}
			
			// Gerar token JWT
			String jwtToken = tokenService.generateToken(user);
			
			// Redirecionar para o frontend com o token
			response.sendRedirect(frontendUrl + "/home?token=" + jwtToken);
			return ResponseEntity.ok().build();
			
		} catch (Exception e) {
			throw new GoogleAuthException("Erro durante o processo de autenticação com Google", e);
		}
	}
}
