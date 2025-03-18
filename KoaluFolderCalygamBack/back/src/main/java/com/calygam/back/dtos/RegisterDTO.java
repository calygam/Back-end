package com.calygam.back.dtos;

import java.math.BigInteger;

import org.hibernate.validator.constraints.br.CPF;

import com.calygam.back.enums.UserRankEnum;
import com.calygam.back.enums.UserRoleEnum;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class RegisterDTO {
    @NotBlank(message = "Nome não pode estar vazio!")
    private String userName;

    @NotBlank(message = "Email não pode estar vazio!")
    @Email(message = "Email inválido!")
    private String userEmail;

    @NotBlank(message = "Senha não pode estar vazia!")
    private String userPassword;


    @NotBlank(message = "CPF não pode estar vazio!")
    @CPF(message = "CPF inválido!")
    private String userCpf;
    
	private BigInteger userMoney;
	private UserRankEnum userRank;
	private UserRoleEnum userRole;

	public RegisterDTO(@NotBlank(message = "Nome não pode estar vazio!") String userName,
			@NotBlank(message = "Email não pode estar vazio!") @Email(message = "Email inválido!") String userEmail,
			@NotBlank(message = "Senha não pode estar vazia!") String userPassword,
			@NotBlank(message = "CPF não pode estar vazio!") @CPF(message = "CPF inválido!") String userCpf,
			BigInteger userMoney, UserRankEnum userRank, UserRoleEnum userRole) {
		super();
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userCpf = userCpf;
		this.userMoney = userMoney;
		this.userRank = userRank;
		this.userRole = userRole;
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

	public String getUserCpf() {
		return userCpf;
	}
	public void setUserCpf(String userCpf) {
		this.userCpf = userCpf;
	}
	public BigInteger getUserMoney() {
		return userMoney;
	}
	public void setUserMoney(BigInteger userMoney) {
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
