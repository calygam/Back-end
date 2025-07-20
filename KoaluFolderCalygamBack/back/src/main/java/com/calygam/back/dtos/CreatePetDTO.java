package com.calygam.back.dtos;

import org.springframework.web.multipart.MultipartFile;

import com.calygam.back.enums.PetStatusEnergyEnum;
import com.fasterxml.jackson.annotation.JsonInclude;

public class CreatePetDTO {
	//CAIO<-ATRIBUTOS DA CARCAÇA DO PET, COMO SE TIVESSE SEM CABEÇA
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long petId;
	
	private String petName;
	//caio<- Boosts multiplicativos
	private Double petBoostMoney;
	private Double petBoostXp;
	private Double petBoostFood;
	//caio<- MIN, CURSOR DEFAULT(PONTEIRO DO MEIO) E MAX DE ENERGIA
	private Long petMinEnergy;
	private Long petDefaultEnergy;
	private Long petMaxEnergy;
	private PetStatusEnergyEnum petStatusEnergy;
	//--------------------------//-----------------------------//
	//caio <- ETAPA DA SKIN DEFAULT -> MEIO QUE A CABEÇA DO PET*/
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long petOutfitId;
	
	private String petOutfitName;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private MultipartFile petOutfitImage;
	
	private Long petPlusMoney;
	private Long petPlusXp;
	private Long petPlusFood;
	private PetStatusEnergyEnum petOutfitMode;
	private String petUrlImage;
	private String petOutfitPackageSkin;
	
	
	




	public CreatePetDTO(Long petId, String petName, Double petBoostMoney, Double petBoostXp, Double petBoostFood,
			Long petMinEnergy, Long petDefaultEnergy, Long petMaxEnergy, PetStatusEnergyEnum petStatusEnergy,
			Long petOutfitId, String petOutfitName, MultipartFile petOutfitImage, Long petPlusMoney, Long petPlusXp,
			Long petPlusFood, PetStatusEnergyEnum petOutfitMode, String petUrlImage, String petOutfitPackageSkin) {
		super();
		this.petId = petId;
		this.petName = petName;
		this.petBoostMoney = petBoostMoney;
		this.petBoostXp = petBoostXp;
		this.petBoostFood = petBoostFood;
		this.petMinEnergy = petMinEnergy;
		this.petDefaultEnergy = petDefaultEnergy;
		this.petMaxEnergy = petMaxEnergy;
		this.petStatusEnergy = petStatusEnergy;
		this.petOutfitId = petOutfitId;
		this.petOutfitName = petOutfitName;
		this.petOutfitImage = petOutfitImage;
		this.petPlusMoney = petPlusMoney;
		this.petPlusXp = petPlusXp;
		this.petPlusFood = petPlusFood;
		this.petOutfitMode = petOutfitMode;
		this.petUrlImage = petUrlImage;
		this.petOutfitPackageSkin = petOutfitPackageSkin;
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

	public PetStatusEnergyEnum getPetStatusEnergy() {
		return petStatusEnergy;
	}

	public void setPetStatusEnergy(PetStatusEnergyEnum petStatusEnergy) {
		this.petStatusEnergy = petStatusEnergy;
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

	public String getPetUrlImage() {
		return petUrlImage;
	}

	public void setPetUrlImage(String petUrlImage) {
		this.petUrlImage = petUrlImage;
	}

	public Long getPetOutfitId() {
		return petOutfitId;
	}

	public void setPetOutfitId(Long petOutfitId) {
		this.petOutfitId = petOutfitId;
	}

	public Long getPetId() {
		return petId;
	}

	public String getPetOutfitPackageSkin() {
		return petOutfitPackageSkin;
	}

	public void setPetOutfitPackageSkin(String petOutfitPackageSkin) {
		this.petOutfitPackageSkin = petOutfitPackageSkin;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
}
