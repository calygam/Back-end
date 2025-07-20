package com.calygam.back.dtos;

import org.springframework.web.multipart.MultipartFile;

import com.calygam.back.enums.PetStatusEnergyEnum;
import com.fasterxml.jackson.annotation.JsonInclude;

public class PetOutfitDTO {
	//--------------------------//-----------------------------//
	//caio <- ETAPA DA SKIN DEFAULT -> MEIO QUE A CABEÇA DO PET*/
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long petOutfitId;
	
	private String petOutfitName;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private MultipartFile petOutfitImage;
	
	private Long petOutfitPlusMoney;
	private Long petOutfitPlusXp;
	private Long petOutfitPlusFood;
	private PetStatusEnergyEnum petOutfitSkinMode;
	private String petUrlImage;
	private String petOutfitPackageSkin;
	
	
	
	
	public PetOutfitDTO() {
		super();
	}

	

	public PetOutfitDTO(Long petOutfitId, String petOutfitName, MultipartFile petOutfitImage, Long petOutfitPlusMoney,
			Long petOutfitPlusXp, Long petOutfitPlusFood, PetStatusEnergyEnum petOutfitSkinMode, String petUrlImage,
			String petOutfitPackageSkin) {
		super();
		this.petOutfitId = petOutfitId;
		this.petOutfitName = petOutfitName;
		this.petOutfitImage = petOutfitImage;
		this.petOutfitPlusMoney = petOutfitPlusMoney;
		this.petOutfitPlusXp = petOutfitPlusXp;
		this.petOutfitPlusFood = petOutfitPlusFood;
		this.petOutfitSkinMode = petOutfitSkinMode;
		this.petUrlImage = petUrlImage;
		this.petOutfitPackageSkin = petOutfitPackageSkin;
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
	public MultipartFile getPetOutfitImage() {
		return petOutfitImage;
	}
	public void setPetOutfitImage(MultipartFile petOutfitImage) {
		this.petOutfitImage = petOutfitImage;
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

	public PetStatusEnergyEnum getPetOutfitSkinMode() {
		return petOutfitSkinMode;
	}
	public void setPetOutfitSkinMode(PetStatusEnergyEnum petOutfitSkinMode) {
		this.petOutfitSkinMode = petOutfitSkinMode;
	}
	public String getPetUrlImage() {
		return petUrlImage;
	}
	public void setPetUrlImage(String petUrlImage) {
		this.petUrlImage = petUrlImage;
	}



	public String getPetOutfitPackageSkin() {
		return petOutfitPackageSkin;
	}



	public void setPetOutfitPackageSkin(String petOutfitPackageSkin) {
		this.petOutfitPackageSkin = petOutfitPackageSkin;
	}
	
	
}
