package com.calygam.back.models;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.calygam.back.enums.UserRankEnum;
import com.calygam.back.enums.UserRoleEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;

@Entity
@Table(name = "tb_users")
public class UserEntity implements UserDetails {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "user_google_id")
	private String userProviderId;
	
	@Column(name="user_name",nullable = false)
	private String userName;
	
	
	
	@Column(name="user_email", nullable = false)
	@NotBlank(message="Email Vázio não aceito!")
	@Email(message="caligam<- Email Inválido!")
	private String userEmail;
	
	@Column(name="user_password")
	private String userPassword;
	
	@Column(name="user_image_perfil")
	private String userImagePerfil;
	
	
	

	
	@Column(name="user_cpf",nullable=true)
	@CPF(message="calygam<- CPF inválido")
	private String userCpf;
	
	@Column(name="user_money")
	private BigInteger userMoney;
	@Column(name="user_xp")
	private Integer xp;
	
	
	@Column(name="user_rank")
	private UserRankEnum userRank;
	
	
	@Column(name="user_role")
	private UserRoleEnum userRole;
	

	@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
	private List<TrailEntity> trails = new ArrayList<TrailEntity>();
	
	@OneToMany(mappedBy="user",cascade = CascadeType.ALL)
	private List<ActivityProgressEntity> progress = new ArrayList<ActivityProgressEntity>();








	public UserEntity() {
		super();
	}



	












	public UserEntity(Long userId, String userProviderId, String userName,
			@NotBlank(message = "Email Vázio não aceito!") @Email(message = "caligam<- Email Inválido!") String userEmail,
			String userPassword, String userImagePerfil, @CPF(message = "calygam<- CPF inválido") String userCpf,
			BigInteger userMoney, Integer xp, UserRankEnum userRank, UserRoleEnum userRole, List<TrailEntity> trails) {
		super();
		this.userId = userId;
		this.userProviderId = userProviderId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userImagePerfil = userImagePerfil;
		this.userCpf = userCpf;
		this.userMoney = userMoney;
		this.xp = xp;
		this.userRank = userRank;
		this.userRole = userRole;
		this.trails = trails;
	}

	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getuserProviderId() {
		return userProviderId;
	}

	public void setuserProviderId(String userProviderId) {
		this.userProviderId = userProviderId;
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

	public Integer getXp() {
		return xp;
	}

	public void setXp(Integer xp) {
		this.xp = xp;
		 this.userRank = UserRankEnum.getRankForXp(xp);
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

	public String getuserImagePerfil() {
		return userImagePerfil;
	}

	public void setuserImagePerfil(String userImagePerfil) {
		this.userImagePerfil = userImagePerfil;
	}
	public String getUserProviderId() {
		return userProviderId;
	}
	public void setUserProviderId(String userProviderId) {
		this.userProviderId = userProviderId;
	}
	public List<TrailEntity> getTrails() {
		return trails;
	}
	public void setTrails(List<TrailEntity> trails) {
		this.trails = trails;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(this.userRole == UserRoleEnum.ADMIN) {
			return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),
					new SimpleGrantedAuthority("ROLE_COORDENADOR"),
					new SimpleGrantedAuthority("ROLE_INSTRUTOR"),
					new SimpleGrantedAuthority("ROLE_ALUNO"));
		}else if(this.userRole == UserRoleEnum.COORDENADOR) {
			return List.of(
					new SimpleGrantedAuthority("ROLE_COORDENADOR"),
					new SimpleGrantedAuthority("ROLE_INSTRUTOR"),
					new SimpleGrantedAuthority("ROLE_ALUNO"));
		}else if(this.userRole == UserRoleEnum.ALUNO) {
			return List.of(new SimpleGrantedAuthority("ROLE_ALUNO"));
		}
		else if(this.userRole == UserRoleEnum.INSTRUTOR) {
			return List.of(
					new SimpleGrantedAuthority("ROLE_INSTRUTOR"),
					new SimpleGrantedAuthority("ROLE_ALUNO"));
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
















	public List<ActivityProgressEntity> getProgress() {
		return progress;
	}
















	public void setProgress(List<ActivityProgressEntity> progress) {
		this.progress = progress;
	}

	

	
	
	

}
