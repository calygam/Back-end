package com.calygam.back.dtos;

import org.springframework.web.multipart.MultipartFile;

import com.calygam.back.enums.PetStatusEnergyEnum;

public class NewOutfitDTO {
	
	//--------------------------//-----------------------------//
	//caio <- ETAPA DA SKIN DEFAULT -> MEIO QUE A CABEÇA DO PET*/
	
	private String petOutfitName;
	private MultipartFile petOutfitImage;
	private Long petPlusMoney;
	private Long petPlusXp;
	private Long petPlusFood;
	private PetStatusEnergyEnum petOutfitMode;
	private String petOutfitPackageSkin;
	
	public NewOutfitDTO(String petOutfitName, MultipartFile petOutfitImage, Long petPlusMoney, Long petPlusXp,
			Long petPlusFood, PetStatusEnergyEnum petOutfitMode, String petOutfitPackageSkin) {
		super();
		this.petOutfitName = petOutfitName;
		this.petOutfitImage = petOutfitImage;
		this.petPlusMoney = petPlusMoney;
		this.petPlusXp = petPlusXp;
		this.petPlusFood = petPlusFood;
		this.petOutfitMode = petOutfitMode;
		this.petOutfitPackageSkin = petOutfitPackageSkin;
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
	public Long getPetPlusMoney() {
		return petPlusMoney;
	}
	public void setPetPlusMoney(Long petPlusMoney) {
		this.petPlusMoney = petPlusMoney;
	}
	public Long getPetPlusXp() {
		return petPlusXp;
	}
	public void setPetPlusXp(Long petPlusXp) {
		this.petPlusXp = petPlusXp;
	}
	public Long getPetPlusFood() {
		return petPlusFood;
	}
	public void setPetPlusFood(Long petPlusFood) {
		this.petPlusFood = petPlusFood;
	}
	public PetStatusEnergyEnum getPetOutfitMode() {
		return petOutfitMode;
	}
	public void setPetOutfitMode(PetStatusEnergyEnum petOutfitMode) {
		this.petOutfitMode = petOutfitMode;
	}
	public String getPetOutfitPackageSkin() {
		return petOutfitPackageSkin;
	}
	public void setPetOutfitPackageSkin(String petOutfitPackageSkin) {
		this.petOutfitPackageSkin = petOutfitPackageSkin;
	}
	
	

	
	
	
	
	
	
	
	
	
	

	
	
}
