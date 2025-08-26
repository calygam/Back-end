package com.calygam.back.dtos;

import com.calygam.back.enums.ItemCatalogInventoryEnum;
import com.calygam.back.enums.UserRankEnum;

public class StockPetDTO {
	private Long petId;
	private String petName;
	private Double petBoostMoney;
	private Double petBoostXp;
	private Double petBoostFood;
	private Long petMinEnergy;
	private Long petDefaultEnergy;
	private Long petMaxEnergy;
	private Long petOutfitId;
	private String petOutfitName;
	private String petOutfitUrl;
	private Long emporiumId;
	private Long emporiumItemId;
	private String emporiumItemCatalogType;
	private Long emporiumItemGoldCost;
	private Long emporiumItemQtd;
	private String emporiumItemRankRequired;
	private boolean emporiumItemSelling;
	private Integer emporiumXpRequired;
	
	
	
	
	public StockPetDTO() {
		super();
	}



















	public StockPetDTO(Long petId, String petName, Double petBoostMoney, Double petBoostXp, Double petBoostFood,
			Long petMinEnergy, Long petDefaultEnergy, Long petMaxEnergy, Long petOutfitId, String petOutfitName,
			String petOutfitUrl, Long emporiumId, Long emporiumItemId, String emporiumItemCatalogType,
			Long emporiumItemGoldCost, Long emporiumItemQtd, String emporiumItemRankRequired,
			boolean emporiumItemSelling, Integer emporiumXpRequired) {
		super();
		this.petId = petId;
		this.petName = petName;
		this.petBoostMoney = petBoostMoney;
		this.petBoostXp = petBoostXp;
		this.petBoostFood = petBoostFood;
		this.petMinEnergy = petMinEnergy;
		this.petDefaultEnergy = petDefaultEnergy;
		this.petMaxEnergy = petMaxEnergy;
		this.petOutfitId = petOutfitId;
		this.petOutfitName = petOutfitName;
		this.petOutfitUrl = petOutfitUrl;
		this.emporiumId = emporiumId;
		this.emporiumItemId = emporiumItemId;
		this.emporiumItemCatalogType = emporiumItemCatalogType;
		this.emporiumItemGoldCost = emporiumItemGoldCost;
		this.emporiumItemQtd = emporiumItemQtd;
		this.emporiumItemRankRequired = emporiumItemRankRequired;
		this.emporiumItemSelling = emporiumItemSelling;
		this.emporiumXpRequired = emporiumXpRequired;
	}



















	public Long getPetId() {
		return petId;
	}




	public void setPetId(Long petId) {
		this.petId = petId;
	}









	public String getPetName() {
		return petName;
	}




	public void setPetName(String petName) {
		this.petName = petName;
	}




	public Double getPetBoostMoney() {
		return petBoostMoney;
	}




	public void setPetBoostMoney(Double petBoostMoney) {
		this.petBoostMoney = petBoostMoney;
	}




	public Double getPetBoostXp() {
		return petBoostXp;
	}




	public void setPetBoostXp(Double petBoostXp) {
		this.petBoostXp = petBoostXp;
	}




	public Double getPetBoostFood() {
		return petBoostFood;
	}




	public void setPetBoostFood(Double petBoostFood) {
		this.petBoostFood = petBoostFood;
	}




	public Long getPetMinEnergy() {
		return petMinEnergy;
	}




	public void setPetMinEnergy(Long petMinEnergy) {
		this.petMinEnergy = petMinEnergy;
	}




	public Long getPetDefaultEnergy() {
		return petDefaultEnergy;
	}




	public void setPetDefaultEnergy(Long petDefaultEnergy) {
		this.petDefaultEnergy = petDefaultEnergy;
	}




	public Long getPetMaxEnergy() {
		return petMaxEnergy;
	}




	public void setPetMaxEnergy(Long petMaxEnergy) {
		this.petMaxEnergy = petMaxEnergy;
	}




	public Long getPetOutfitId() {
		return petOutfitId;
	}




	public void setPetOutfitId(Long petOutfitId) {
		this.petOutfitId = petOutfitId;
	}




	public String getPetOutfitName() {
		return petOutfitName;
	}




	public void setPetOutfitName(String petOutfitName) {
		this.petOutfitName = petOutfitName;
	}




	public String getPetOutfitUrl() {
		return petOutfitUrl;
	}




	public void setPetOutfitUrl(String petOutfitUrl) {
		this.petOutfitUrl = petOutfitUrl;
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




	



	public String getEmporiumItemCatalogType() {
		return emporiumItemCatalogType;
	}









	public void setEmporiumItemCatalogType(String emporiumItemCatalogType) {
		this.emporiumItemCatalogType = emporiumItemCatalogType;
	}









	public void setEmporiumItemRankRequired(String emporiumItemRankRequired) {
		this.emporiumItemRankRequired = emporiumItemRankRequired;
	}









	public Long getEmporiumItemGoldCost() {
		return emporiumItemGoldCost;
	}




	public void setEmporiumItemGoldCost(Long emporiumItemGoldCost) {
		this.emporiumItemGoldCost = emporiumItemGoldCost;
	}




	public Long getEmporiumItemQtd() {
		return emporiumItemQtd;
	}




	public void setEmporiumItemQtd(Long emporiumItemQtd) {
		this.emporiumItemQtd = emporiumItemQtd;
	}




	



	public String getEmporiumItemRankRequired() {
		return emporiumItemRankRequired;
	}









	public boolean isEmporiumItemSelling() {
		return emporiumItemSelling;
	}




	public void setEmporiumItemSelling(boolean emporiumItemSelling) {
		this.emporiumItemSelling = emporiumItemSelling;
	}



















	public Integer getEmporiumXpRequired() {
		return emporiumXpRequired;
	}



















	public void setEmporiumXpRequired(Integer emporiumXpRequired) {
		this.emporiumXpRequired = emporiumXpRequired;
	}

	


	
	
	
	
}
