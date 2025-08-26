package com.calygam.back.dtos;

import java.math.BigInteger;

import org.hibernate.validator.constraints.br.CPF;

import com.calygam.back.enums.UserRankEnum;
import com.calygam.back.enums.UserRoleEnum;
import com.calygam.back.models.UserEntity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class RegisterResponseDTO {
private Long id;
	
    @NotBlank(message = "Nome não pode estar vazio!")
    private String userName;

    @NotBlank(message = "Email não pode estar vazio!")
    @Email(message = "Email inválido!")
    private String userEmail;

    

	private String userRank;
	
	public RegisterResponseDTO() {
		super();
	}

	public RegisterResponseDTO(Long id, @NotBlank(message = "Nome não pode estar vazio!") String userName,
			@NotBlank(message = "Email não pode estar vazio!") @Email(message = "Email inválido!") String userEmail,
			String userRank) {
		super();
		this.id = id;
		this.userName = userName;
		this.userEmail = userEmail;

		this.userRank = userRank;
	}
	
	//caio<- trazendo apenas o necessario na resposta: 
	public RegisterResponseDTO(UserEntity entity,Long xp) {
		id = entity.getUserId();
		userName = entity.getUserName();
		userEmail = entity.getUserEmail();

		userRank = UserRankEnum.getRankForXpToString(xp);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}



	public String getUserRank() {
		return userRank;
	}

	public void setUserRank(String userRank) {
		this.userRank = userRank;
	}
}
