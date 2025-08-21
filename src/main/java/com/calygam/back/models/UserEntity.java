package com.calygam.back.models;

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
import com.calygam.back.enums.UserStatus;
import com.calygam.back.utils.GenericFileManagement;

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
public class UserEntity implements UserDetails,GenericFileManagement {
	
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
	
	@Column(name="user_archive_name")
	private String archiveName;
	
	@Column(name="user_original_name")
	private String originalName;
	
	@Column(name="user_archive_path")
	private String archivePath;
	
	@Column(name="user_archive_type")
	private String archiveType;
	
	
	

	
	@Column(name="user_cpf",nullable=true)
	@CPF(message="calygam<- CPF inválido")
	private String userCpf;
	
	@Column(name="user_money")
	private Long userMoney;
	@Column(name="user_food")
	private Long userFood;
	@Column(name="user_xp")
	private Long xp;
	
	
	@Column(name="user_rank")
	private UserRankEnum userRank;
	
	
	@Column(name="user_role")
	private UserRoleEnum userRole;
	
	@Column(name="user_status")
	private UserStatus userStatus;
	

	@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
	private List<TrailEntity> trails = new ArrayList<TrailEntity>();
	
	@OneToMany(mappedBy="user",cascade = CascadeType.ALL)
	private List<ActivityProgressEntity> progress = new ArrayList<ActivityProgressEntity>();

	@OneToMany(mappedBy="apprentice",cascade = CascadeType.ALL)
	private List<ApprenticeInventoryEntity> items = new ArrayList<ApprenticeInventoryEntity>();

	@OneToMany(mappedBy="apprentice",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<ControlApprenticePetEntity> pets = new ArrayList<ControlApprenticePetEntity>();
	
	@OneToMany(mappedBy="user",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DailyFlagsEntity> flags = new ArrayList<DailyFlagsEntity>();
	@OneToMany(mappedBy="user",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MessageActivityEntity> messages = new ArrayList<MessageActivityEntity>();
	
	@OneToMany(mappedBy="recipient",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MessageActivityEntity> recipients = new ArrayList<MessageActivityEntity>();




	public UserEntity() {
		super();
	}



	












	

	/*public UserEntity(Long userId, String userProviderId, String userName,
			@NotBlank(message = "Email Vázio não aceito!") @Email(message = "caligam<- Email Inválido!") String userEmail,
			String userPassword,  @CPF(message = "calygam<- CPF inválido") String userCpf,
			Long userMoney,Long userFood, Long xp, UserRankEnum userRank, UserRoleEnum userRole, UserStatus userStatus,
			List<TrailEntity> trails, List<ActivityProgressEntity> progress,List<ControlApprenticePetEntity> pets,List<ApprenticeInventoryEntity> items,List<DailyFlagsEntity> flags,List<MessageActivityEntity> messages,List<MessageActivityEntity> recipients) {
		super();
		this.userId = userId;
		this.userProviderId = userProviderId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;

		this.userCpf = userCpf;
		this.userMoney = userMoney;
		this.userFood = userFood;
		this.xp = xp;
		this.userRank = userRank;
		this.userRole = userRole;
		this.userStatus = userStatus;
		this.trails = trails;
		this.pets = pets;
		this.items = items;
		this.flags = flags;
		this.messages = messages;
		this.recipients = recipients;
		
	}*/
	
	


















	public Long getUserId() {
		return userId;
	}
	public UserEntity(Long userId, String userProviderId, String userName,
			@NotBlank(message = "Email Vázio não aceito!") @Email(message = "caligam<- Email Inválido!") String userEmail,
			String userPassword, String archiveName, String originalName, String archivePath, String archiveType,
			@CPF(message = "calygam<- CPF inválido") String userCpf, Long userMoney, Long userFood, Long xp,
			UserRankEnum userRank, UserRoleEnum userRole, UserStatus userStatus, List<TrailEntity> trails,
			List<ActivityProgressEntity> progress, List<ApprenticeInventoryEntity> items,
			List<ControlApprenticePetEntity> pets, List<DailyFlagsEntity> flags, List<MessageActivityEntity> messages,
			List<MessageActivityEntity> recipients) {
		super();
		this.userId = userId;
		this.userProviderId = userProviderId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.archiveName = archiveName;
		this.originalName = originalName;
		this.archivePath = archivePath;
		this.archiveType = archiveType;
		this.userCpf = userCpf;
		this.userMoney = userMoney;
		this.userFood = userFood;
		this.xp = xp;
		this.userRank = userRank;
		this.userRole = userRole;
		this.userStatus = userStatus;
		this.trails = trails;
		this.progress = progress;
		this.items = items;
		this.pets = pets;
		this.flags = flags;
		this.messages = messages;
		this.recipients = recipients;
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

	public Long getXp() {
		return xp;
	}

	public void setXp(Long xp) {
		this.xp = xp;
		 this.userRank = UserRankEnum.getRankForXp(xp);
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

	
	public String getArchiveName() {
		return archiveName;
	}



	public void setArchiveName(String archiveName) {
		this.archiveName = archiveName;
	}


















	public String getOriginalName() {
		return originalName;
	}


















	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}


















	public String getArchivePath() {
		return archivePath;
	}


















	public void setArchivePath(String archivePath) {
		this.archivePath = archivePath;
	}


















	public String getArchiveType() {
		return archiveType;
	}


















	public void setArchiveType(String archiveType) {
		this.archiveType = archiveType;
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
	
	
	public List<ControlApprenticePetEntity> getPets() {
		return pets;
	}

	public void setPets(List<ControlApprenticePetEntity> pets) {
		this.pets = pets;
	}
	
	
	public List<ApprenticeInventoryEntity> getItems() {
		return items;
	}



	public void setItems(List<ApprenticeInventoryEntity> items) {
		this.items = items;
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


	public UserStatus getUserStatus() {
		return userStatus;
	}
	

	public Long getUserFood() {
		return userFood;
	}

	public void setUserFood(Long userFood) {
		this.userFood = userFood;
	}


	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}


	public List<ActivityProgressEntity> getProgress() {
		return progress;
	}


	public void setProgress(List<ActivityProgressEntity> progress) {
		this.progress = progress;
	}

	public List<DailyFlagsEntity> getFlags() {
		return flags;
	}

	public void setFlags(List<DailyFlagsEntity> flags) {
		this.flags = flags;
	}



	public List<MessageActivityEntity> getMessages() {
		return messages;
	}

	public void setMessages(List<MessageActivityEntity> messages) {
		this.messages = messages;
	}


















	public List<MessageActivityEntity> getRecipients() {
		return recipients;
	}


















	public void setRecipients(List<MessageActivityEntity> recipients) {
		this.recipients = recipients;
	}
	
	
	
	

	

	
	
	

}
