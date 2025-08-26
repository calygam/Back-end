package com.calygam.back.dtos;

import com.calygam.back.enums.ItemCatalogInventoryEnum;
import com.calygam.back.enums.UserRankEnum;

public class EmporiumItemDTO {
	//--------------------------//-----------------------------//
	//caio <- ETAPA DO ITEM QUE VAI SER ADICIONADO NO EMPÓRIO*/

	private Long emporiumItemId;
	private ItemCatalogInventoryEnum emporiumItemCatalogType;
	private Long emporiumItemGoldCost;
	private UserRankEnum emporiumItemRankRequired;
	private Boolean emporiumItemSelling;
	private Long emporiumItemQtd;
	
	public EmporiumItemDTO() {
		super();
	}



	public EmporiumItemDTO(Long emporiumItemId, ItemCatalogInventoryEnum emporiumItemCatalogType,
			Long emporiumItemGoldCost, UserRankEnum emporiumItemRankRequired, Boolean emporiumItemSelling,
			Long emporiumItemQtd) {
		super();
		this.emporiumItemId = emporiumItemId;
		this.emporiumItemCatalogType = emporiumItemCatalogType;
		this.emporiumItemGoldCost = emporiumItemGoldCost;
		this.emporiumItemRankRequired = emporiumItemRankRequired;
		this.emporiumItemSelling = emporiumItemSelling;
		this.emporiumItemQtd = emporiumItemQtd;
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

	public Boolean getEmporiumItemSelling() {
		return emporiumItemSelling;
	}

	public void setEmporiumItemSelling(Boolean emporiumItemSelling) {
		this.emporiumItemSelling = emporiumItemSelling;
	}



	public Long getEmporiumItemQtd() {
		return emporiumItemQtd;
	}



	public void setEmporiumItemQtd(Long emporiumItemQtd) {
		this.emporiumItemQtd = emporiumItemQtd;
	}

	

	


	
	
	
	
	

	
	
	
	
	
	
	
	
	
	

	
	
}
