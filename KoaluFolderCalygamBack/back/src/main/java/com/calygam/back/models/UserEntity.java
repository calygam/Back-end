package com.calygam.back.models;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.calygam.back.enums.UserRankEnum;
import com.calygam.back.enums.UserRoleEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Entity(name = "tb_users")
@Table(name = "tb_users")
public class UserEntity implements UserDetails {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "user_google_id")
	private String userGoogleId;
	
	@Column(name="user_name",nullable = false)
	private String userName;
	
	
	
	@Column(name="user_email", nullable = false)
	@NotBlank(message="Email Vázio não aceito!")
	@Email(message="caligam<- Email Inválido!")
	private String userEmail;
	
	@Column(name="user_password")
	private String userPassword;
	
	@Column(name="user_image_perfil")
	private String userImageParfil;
	
	
	
	@Column(name="user_telefone")
	private String userTelefone;
	
	@Column(name="user_cpf",nullable=false)
	@CPF(message="calygam<- CPF inválido")
	private String userCpf;
	
	@Column(name="user_money")
	private BigInteger userMoney;
	@Column(name="user_xp")
	private Integer xp;
	
	@Enumerated(EnumType.STRING)
	@Column(name="user_rank")
	private UserRankEnum userRank;
	
	@Enumerated(EnumType.STRING)
	@Column(name="user_role")
	private UserRoleEnum userRole;
	










	public UserEntity() {
		super();
	}



	



	public UserEntity(Long userId, String userGoogleId, String userName,
			@NotBlank(message = "Email Vázio não aceito!") @Email(message = "caligam<- Email Inválido!") String userEmail,
			String userPassword, String userImageParfil, String userTelefone,
			@CPF(message = "calygam<- CPF inválido") String userCpf, BigInteger userMoney, Integer xp,
			UserRankEnum userRank, UserRoleEnum userRole) {
		super();
		this.userId = userId;
		this.userGoogleId = userGoogleId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userImageParfil = userImageParfil;
		this.userTelefone = userTelefone;
		this.userCpf = userCpf;
		this.userMoney = userMoney;
		this.xp = xp;
		this.userRank = userRank;
		this.userRole = userRole;
	}








	public Long getUserId() {
		return userId;
	}







	public void setUserId(Long userId) {
		this.userId = userId;
	}







	public String getUserGoogleId() {
		return userGoogleId;
	}

	public void setUserGoogleId(String userGoogleId) {
		this.userGoogleId = userGoogleId;
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



	public String getUserTelefone() {
		return userTelefone;
	}

	public void setUserTelefone(String userTelefone) {
		this.userTelefone = userTelefone;
	}



	public String getUserCpf() {
		return userCpf;
	}

	public void setUserCpf(String userCpf) {
		this.userCpf = userCpf;
	}

	public Integer getXp() {
		return xp;
	}

	public void setXp(Integer xp) {
		this.xp = xp;
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

	public String getUserImageParfil() {
		return userImageParfil;
	}

	public void setUserImageParfil(String userImageParfil) {
		this.userImageParfil = userImageParfil;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(this.userRole == UserRoleEnum.ADMIN) {
			return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),
					new SimpleGrantedAuthority("ROLE_COORDENADOR"),
					new SimpleGrantedAuthority("ROLE_INSTRUTOR"),
					new SimpleGrantedAuthority("ROLE_ALUNO"));
		}else if(this.userRole == UserRoleEnum.COORDENADOR) {
			return List.of(new SimpleGrantedAuthority("ROLE_COORDENADOR"));
		}else if(this.userRole == UserRoleEnum.ALUNO) {
			return List.of(new SimpleGrantedAuthority("ROLE_ALUNO"));
		}
		else if(this.userRole == UserRoleEnum.INSTRUTOR) {
			return List.of(new SimpleGrantedAuthority("ROLE_INSTRUTOR"));
		}
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return userPassword;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userEmail;
	}
	
	
	@Override
	public boolean isAccountNonExpired() {
	
	    return true;
	}

	@Override
	public boolean isAccountNonLocked() {
	  
	    return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
	 
	    return true;
	}

	@Override
	public boolean isEnabled() {
	   
	    return true;
	}

	

	
	
	

}
