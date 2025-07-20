package com.calygam.back.models;

import com.calygam.back.enums.ItemCatalogInventoryEnum;
import com.calygam.back.enums.UserRankEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_calygam_emporium")
public class CalygamEmporiumEntity {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="emporium_id")
	private Long emporiumId;
	
	@Column(name="emporium_item_id")
	private Long emporiumItemId;
	
	@Column(name="emporium_item_catalog_type")
	private ItemCatalogInventoryEnum emporiumItemCatalogType;
	
	@Column(name="emporium_item_gold_cost") 
	private Long emporiumItemGoldCost;
	
	@Column(name="emporium_item_rank_required")
	private UserRankEnum emporiumItemRankRequired;
	
	@Column(name="emporium_item_selling")
	private boolean emporiumItemSelling;
	@Column(name="emporium_item_qtd")
	private Long emporiumItemQtd;

	public CalygamEmporiumEntity() {
		super();
	}

	

	public CalygamEmporiumEntity(Long emporiumId, Long emporiumItemId, ItemCatalogInventoryEnum emporiumItemCatalogType,
			Long emporiumItemGoldCost, UserRankEnum emporiumItemRankRequired, boolean emporiumItemSelling,
			Long emporiumItemQtd) {
		super();
		this.emporiumId = emporiumId;
		this.emporiumItemId = emporiumItemId;
		this.emporiumItemCatalogType = emporiumItemCatalogType;
		this.emporiumItemGoldCost = emporiumItemGoldCost;
		this.emporiumItemRankRequired = emporiumItemRankRequired;
		this.emporiumItemSelling = emporiumItemSelling;
		this.emporiumItemQtd = emporiumItemQtd;
	}



	public Long getEmporiumId() {
		return emporiumId;
	}

	public void setEmporiumId(Long emporiumId) {
		this.emporiumId = emporiumId;
	}

	public Long getEmporiumItemId() {
		return emporiumItemId;
	}

	public void setEmporiumItemId(Long emporiumItemId) {
		this.emporiumItemId = emporiumItemId;
	}

	public ItemCatalogInventoryEnum getEmporiumItemCatalogType() {
		return emporiumItemCatalogType;
	}

	public void setEmporiumItemCatalogType(ItemCatalogInventoryEnum emporiumItemCatalogType) {
		this.emporiumItemCatalogType = emporiumItemCatalogType;
	}

	public Long getEmporiumItemGoldCost() {
		return emporiumItemGoldCost;
	}

	public void setEmporiumItemGoldCost(Long emporiumItemGoldCost) {
		this.emporiumItemGoldCost = emporiumItemGoldCost;
	}

	public UserRankEnum getEmporiumItemRankRequired() {
		return emporiumItemRankRequired;
	}

	public void setEmporiumItemRankRequired(UserRankEnum emporiumItemRankRequired) {
		this.emporiumItemRankRequired = emporiumItemRankRequired;
	}

	public boolean isEmporiumItemSelling() {
		return emporiumItemSelling;
	}

	public void setEmporiumItemSelling(boolean emporiumItemSelling) {
		this.emporiumItemSelling = emporiumItemSelling;
	}



	public Long getEmporiumItemQtd() {
		return emporiumItemQtd;
	}



	public void setEmporiumItemQtd(Long emporiumItemQtd) {
		this.emporiumItemQtd = emporiumItemQtd;
	}
	
	
}
