package com.calygam.back.dtos;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import com.calygam.back.enums.UserRankEnum;
import com.calygam.back.enums.UserRoleEnum;
import com.calygam.back.models.TrailEntity;
import com.calygam.back.models.UserEntity;

public class DataUtilUserDTO {
		
	private Long id;
	private String userName;
	private String userEmail;
	private String userCpf;
	private Integer userXp;
	private String userRank;	
	private Integer userRankPoints;
	private String userImage;
	private UserRoleEnum userRole;
	private BigInteger userMoney;
	private List<TrailDTO> trails;
	public DataUtilUserDTO() {
		super();
	}
	public DataUtilUserDTO(Long id, String userName, String userEmail, String userCpf, Integer userXp,
			String userImage, UserRoleEnum userRole, BigInteger userMoney) {
		super();
		this.id = id;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userCpf = userCpf;
		this.userXp = userXp;
		this.userRank = UserRankEnum.getRankForXpToString(userXp);
		this.userRankPoints =UserRankEnum.getRankForXpPoints(userXp);
		this.userImage = userImage;
		this.userRole = userRole;
		this.userMoney = userMoney;

		
	}
	
	
	
	
	public DataUtilUserDTO(UserEntity entity) {
		super();
		id = entity.getUserId();
		userName = entity.getUserName();
		userEmail = entity.getUserEmail();
		userCpf = entity.getUserCpf();
		userXp = entity.getXp();
		this.userRank = UserRankEnum.getRankForXpToString(userXp);
		this.userRankPoints =UserRankEnum.getRankForXpPoints(userXp);
		userImage = entity.getuserImagePerfil();
		userMoney = entity.getUserMoney();
        this.trails = entity.getTrails() != null
                ? entity.getTrails().stream()
                        .map(TrailDTO::new)
                        .collect(Collectors.toList())
                : List.of(); 
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
	public String getUserCpf() {
		return userCpf;
	}
	public void setUserCpf(String userCpf) {
		this.userCpf = userCpf;
	}
	public Integer getUserXp() {
		return userXp;
	}
	public void setUserXp(Integer userXp) {
		this.userXp = userXp;
	}
	public String getUserRank() {
		return userRank;
	}
	public void setUserRank(String userRank) {
		this.userRank = userRank;
	}
	public String getUserImage() {
		return userImage;
	}
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
	public BigInteger getUserMoney() {
		return userMoney;
	}
	public void setUserMoney(BigInteger userMoney) {
		this.userMoney = userMoney;
	}
	public Integer getUserRankPoints() {
		return userRankPoints;
	}
	public void setUserRankPoints(Integer userRankPoints) {
		this.userRankPoints = userRankPoints;
	}
	public List<TrailDTO> getTrails() {
		return trails;
	}
	public void setTrails(List<TrailDTO> trails) {
		this.trails = trails;
	}
	public UserRoleEnum getUserRole() {
		return userRole;
	}
	public void setUserRole(UserRoleEnum userRole) {
		this.userRole = userRole;
	}
	
	
	
	
	
	
	
}
