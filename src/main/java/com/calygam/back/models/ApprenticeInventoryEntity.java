package com.calygam.back.models;

import java.time.LocalDateTime;

import com.calygam.back.enums.ItemCatalogInventoryEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_apprentice_inventory")
public class ApprenticeInventoryEntity {
	
	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="apprentice_inventory_id")
	private Long apprenticeInventoryId;
	
	@Column(name="apprentice_inventory_tag")
	private ItemCatalogInventoryEnum apprenticeInventoryTag;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserEntity apprentice;
	
	@Column(name="apprentice_inventory_item_id")
	private Long apprenticeInventoryItemId;
	
	@Column(name = "apprentice_inventory_equipped")
	private boolean apprenticeInventoryEquipped;

	@Column(name = "apprentice_inventory_created_at")
	private LocalDateTime apprenticeInventoryCreatedAt;
	
	
	
	
	
	

	public ApprenticeInventoryEntity() {
		super();
	}

	public ApprenticeInventoryEntity(Long apprenticeInventoryId, ItemCatalogInventoryEnum apprenticeInventoryTag,
			UserEntity apprentice, Long apprenticeInventoryItemId, boolean apprenticeInventoryEquipped,
			LocalDateTime apprenticeInventoryCreatedAt) {
		super();
		this.apprenticeInventoryId = apprenticeInventoryId;
		this.apprenticeInventoryTag = apprenticeInventoryTag;
		this.apprentice = apprentice;
		this.apprenticeInventoryItemId = apprenticeInventoryItemId;
		this.apprenticeInventoryEquipped = apprenticeInventoryEquipped;
		this.apprenticeInventoryCreatedAt = apprenticeInventoryCreatedAt;
	}

	public Long getApprenticeInventoryId() {
		return apprenticeInventoryId;
	}

	public void setApprenticeInventoryId(Long apprenticeInventoryId) {
		this.apprenticeInventoryId = apprenticeInventoryId;
	}

	public ItemCatalogInventoryEnum getApprenticeInventoryTag() {
		return apprenticeInventoryTag;
	}

	public void setApprenticeInventoryTag(ItemCatalogInventoryEnum apprenticeInventoryTag) {
		this.apprenticeInventoryTag = apprenticeInventoryTag;
	}

	public UserEntity getApprentice() {
		return apprentice;
	}

	public void setApprentice(UserEntity apprentice) {
		this.apprentice = apprentice;
	}

	public Long getApprenticeInventoryItemId() {
		return apprenticeInventoryItemId;
	}

	public void setApprenticeInventoryItemId(Long apprenticeInventoryItemId) {
		this.apprenticeInventoryItemId = apprenticeInventoryItemId;
	}

	public boolean isApprenticeInventoryEquipped() {
		return apprenticeInventoryEquipped;
	}

	public void setApprenticeInventoryEquipped(boolean apprenticeInventoryEquipped) {
		this.apprenticeInventoryEquipped = apprenticeInventoryEquipped;
	}

	public LocalDateTime getApprenticeInventoryCreatedAt() {
		return apprenticeInventoryCreatedAt;
	}

	public void setApprenticeInventoryCreatedAt(LocalDateTime apprenticeInventoryCreatedAt) {
		this.apprenticeInventoryCreatedAt = apprenticeInventoryCreatedAt;
	}
	
	
	
}
