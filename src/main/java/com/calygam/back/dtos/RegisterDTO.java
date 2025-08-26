package com.calygam.back.dtos;

import java.math.BigInteger;

import org.hibernate.validator.constraints.br.CPF;

import com.calygam.back.enums.UserRankEnum;
import com.calygam.back.enums.UserRoleEnum;
import com.calygam.back.models.UserEntity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class RegisterDTO {
	
	private Long id;
	
    @NotBlank(message = "Nome não pode estar vazio!")
    private String userName;

    @NotBlank(message = "Email não pode estar vazio!")
    @Email(message = "Email inválido!")
    private String userEmail;

    @NotBlank(message = "Senha não pode estar vazia!")
    private String userPassword;


    
	private Long userMoney;
	private UserRankEnum userRank;
	private UserRoleEnum userRole;
	
	


	public RegisterDTO() {
		super();
	}


	public RegisterDTO(Long id, @NotBlank(message = "Nome não pode estar vazio!") String userName,
			@NotBlank(message = "Email não pode estar vazio!") @Email(message = "Email inválido!") String userEmail,
			@NotBlank(message = "Senha não pode estar vazia!") String userPassword,
			Long userMoney, UserRankEnum userRank, UserRoleEnum userRole) {
		super();
		this.id = id;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;

		this.userMoney = userMoney;
		this.userRank = userRank;
		this.userRole = userRole;
	}
	
	
	public RegisterDTO(UserEntity entity) {
		super();
		id = entity.getUserId();
		userName = entity.getUserName();
		userEmail = entity.getUserEmail();
		userPassword = entity.getPassword();
		userMoney = entity.getUserMoney();
		userRank = entity.getUserRank();
		userRole = entity.getUserRole();
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
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}


	public Long getUserMoney() {
		return userMoney;
	}
	public void setUserMoney(Long userMoney) {
		this.userMoney = userMoney;
	}
	public UserRankEnum getUserRank() {
		return userRank;
	}
	public void setUserRank(UserRankEnum userRank) {
		this.userRank = userRank;
	}
	public UserRoleEnum getUserRole() {
		return userRole;
	}
	public void setUserRole(UserRoleEnum userRole) {
		this.userRole = userRole;
	}
	
	
	
	

}
