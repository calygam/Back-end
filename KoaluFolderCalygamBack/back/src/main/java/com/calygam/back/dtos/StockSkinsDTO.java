package com.calygam.back.dtos;

public class StockSkinsDTO {
	private Long petId;
	private String petName;
	private Long petOutfitPlusMoney;
	private Long petOutfitPlusXp;
	private Long petOutfitPlusFood;
	private Long petOutfitId;
	private String petOutfitName;
	private String petOutfitPackageSkin;
	private String petOutfitUrl;
	private Long emporiumId;
	private Long emporiumItemId;
	private String emporiumItemCatalogType;
	private Long emporiumItemGoldCost;
	private Long emporiumItemQtd;
	private String emporiumItemRankRequired;
	private boolean emporiumItemSelling;
	private Integer emporiumXpRequired;
	
	
	
	public StockSkinsDTO() {
		super();
	}
	





	public StockSkinsDTO(Long petId, String petName, Long petOutfitPlusMoney, Long petOutfitPlusXp,
			Long petOutfitPlusFood, Long petOutfitId, String petOutfitName, String petOutfitPackageSkin,
			String petOutfitUrl, Long emporiumId, Long emporiumItemId, String emporiumItemCatalogType,
			Long emporiumItemGoldCost, Long emporiumItemQtd, String emporiumItemRankRequired,
			boolean emporiumItemSelling, Integer emporiumXpRequired) {
		super();
		this.petId = petId;
		this.petName = petName;
		this.petOutfitPlusMoney = petOutfitPlusMoney;
		this.petOutfitPlusXp = petOutfitPlusXp;
		this.petOutfitPlusFood = petOutfitPlusFood;
		this.petOutfitId = petOutfitId;
		this.petOutfitName = petOutfitName;
		this.petOutfitPackageSkin = petOutfitPackageSkin;
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
	public void setEmporiumItemRankRequired(String emporiumItemRankRequired) {
		this.emporiumItemRankRequired = emporiumItemRankRequired;
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



	public Long getPetOutfitPlusMoney() {
		return petOutfitPlusMoney;
	}



	public void setPetOutfitPlusMoney(Long petOutfitPlusMoney) {
		this.petOutfitPlusMoney = petOutfitPlusMoney;
	}



	public Long getPetOutfitPlusXp() {
		return petOutfitPlusXp;
	}



	public void setPetOutfitPlusXp(Long petOutfitPlusXp) {
		this.petOutfitPlusXp = petOutfitPlusXp;
	}



	public Long getPetOutfitPlusFood() {
		return petOutfitPlusFood;
	}



	public void setPetOutfitPlusFood(Long petOutfitPlusFood) {
		this.petOutfitPlusFood = petOutfitPlusFood;
	}






	public String getPetOutfitPackageSkin() {
		return petOutfitPackageSkin;
	}






	public void setPetOutfitPackageSkin(String petOutfitPackageSkin) {
		this.petOutfitPackageSkin = petOutfitPackageSkin;
	}
	
	
	
	
}
