package com.calygam.back.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_daily_flags")
public class DailyFlagsEntity {
		
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="daily_flag_id")
	private Long DailyFlagId;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserEntity user;
	
	@Column(name="daily_flag_created_at")
	private LocalDate dailyFlagCreatedAt;
	
	@Column(name="user_flags")
	private Long userFlags;

	public DailyFlagsEntity() {
		super();
	}

	public DailyFlagsEntity(Long dailyFlagId, UserEntity user, LocalDate dailyFlagCreatedAt, Long userFlags) {
		super();
		DailyFlagId = dailyFlagId;
		this.user = user;
		this.dailyFlagCreatedAt = dailyFlagCreatedAt;
		this.userFlags = userFlags;
	}

	public Long getDailyFlagId() {
		return DailyFlagId;
	}

	public void setDailyFlagId(Long dailyFlagId) {
		DailyFlagId = dailyFlagId;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public LocalDate getDailyFlagCreatedAt() {
		return dailyFlagCreatedAt;
	}

	public void setDailyFlagCreatedAt(LocalDate dailyFlagCreatedAt) {
		this.dailyFlagCreatedAt = dailyFlagCreatedAt;
	}

	public Long getUserFlags() {
		return userFlags;
	}

	public void setUserFlags(Long userFlags) {
		this.userFlags = userFlags;
	}
	
	
	
	
	
	
}
