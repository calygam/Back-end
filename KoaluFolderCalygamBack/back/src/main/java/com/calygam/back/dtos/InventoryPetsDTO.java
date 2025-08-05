package com.calygam.back.dtos;

import com.calygam.back.enums.ItemCatalogInventoryEnum;
import com.calygam.back.enums.PetStatusEnergyEnum;
import com.fasterxml.jackson.annotation.JsonInclude;

public class InventoryPetsDTO {
    private Long petId;
    private String petName;
    private Double petBoostMoney;
    private Double petBoostXp;
    private Double petBoostFood;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long petOutfitPlusMoney;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long petOutfitPlusXp;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long petOutfitPlusFood;
    
    private Long petMinEnergy;
    private Long apprenticePetEnergy;
    private Long petMaxEnergy;
    private Long petFeedQtd;

    private Long petOutfitId;
    private PetStatusEnergyEnum petOutfitSkinMode;
    private String petOutfitName;
    private String  petOutfitUrl;

    private Long apprenticeInventoryId;
    private ItemCatalogInventoryEnum InventapprenticeInventoryTag;
    private Long apprenticeInventoryItemId;
    private boolean apprenticeInventoryEquipped;
	public InventoryPetsDTO() {
		super();
	}






	public InventoryPetsDTO(Long petId, String petName, Double petBoostMoney, Double petBoostXp, Double petBoostFood,
			Long petOutfitPlusMoney, Long petOutfitPlusXp, Long petOutfitPlusFood, Long petMinEnergy,
			Long apprenticePetEnergy, Long petMaxEnergy, Long petFeedQtd, Long petOutfitId,
			PetStatusEnergyEnum petOutfitSkinMode, String petOutfitName, String petOutfitUrl,
			Long apprenticeInventoryId, ItemCatalogInventoryEnum inventapprenticeInventoryTag,
			Long apprenticeInventoryItemId, boolean apprenticeInventoryEquipped) {
		super();
		this.petId = petId;
		this.petName = petName;
		this.petBoostMoney = petBoostMoney;
		this.petBoostXp = petBoostXp;
		this.petBoostFood = petBoostFood;
		this.petOutfitPlusMoney = petOutfitPlusMoney;
		this.petOutfitPlusXp = petOutfitPlusXp;
		this.petOutfitPlusFood = petOutfitPlusFood;
		this.petMinEnergy = petMinEnergy;
		this.apprenticePetEnergy = apprenticePetEnergy;
		this.petMaxEnergy = petMaxEnergy;
		this.petFeedQtd = petFeedQtd;
		this.petOutfitId = petOutfitId;
		this.petOutfitSkinMode = petOutfitSkinMode;
		this.petOutfitName = petOutfitName;
		this.petOutfitUrl = petOutfitUrl;
		this.apprenticeInventoryId = apprenticeInventoryId;
		InventapprenticeInventoryTag = inventapprenticeInventoryTag;
		this.apprenticeInventoryItemId = apprenticeInventoryItemId;
		this.apprenticeInventoryEquipped = apprenticeInventoryEquipped;
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
	public Long getApprenticePetEnergy() {
		return apprenticePetEnergy;
	}
	public void setApprenticePetEnergy(Long apprenticePetEnergy) {
		this.apprenticePetEnergy = apprenticePetEnergy;
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
	public PetStatusEnergyEnum getPetOutfitSkinMode() {
		return petOutfitSkinMode;
	}
	public void setPetOutfitSkinMode(PetStatusEnergyEnum petOutfitSkinMode) {
		this.petOutfitSkinMode = petOutfitSkinMode;
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

	public Long getApprenticeInventoryId() {
		return apprenticeInventoryId;
	}
	public void setApprenticeInventoryId(Long apprenticeInventoryId) {
		this.apprenticeInventoryId = apprenticeInventoryId;
	}
	public ItemCatalogInventoryEnum getInventapprenticeInventoryTag() {
		return InventapprenticeInventoryTag;
	}
	public void setInventapprenticeInventoryTag(ItemCatalogInventoryEnum inventapprenticeInventoryTag) {
		InventapprenticeInventoryTag = inventapprenticeInventoryTag;
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



	public Long getPetFeedQtd() {
		return petFeedQtd;
	}



	public void setPetFeedQtd(Long petFeedQtd) {
		this.petFeedQtd = petFeedQtd;
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
	
	
    
    
}
