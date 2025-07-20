package com.calygam.back.models;

import com.calygam.back.enums.PetStatusEnergyEnum;
import com.calygam.back.utils.GenericFileManagement;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_pet_outfits")
public class PetOutfitEntity implements GenericFileManagement {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pet_outfit_id")
	private Long petOutfitId;
	
	@Column(name="pet_outfit_name")
	private String petOutfitName;
	
	@Column(name="pet_outfit_archive_name")
	private String archiveName;
	
	@Column(name="pet_outfit_original_name")
	private String originalName;
	
	@Column(name="pet_outfit_archive_path")
	private String archivePath;
	
	@Column(name="pet_outfit_archive_type")
	private String archiveType;
	
	@Column(name="pet_outfit_plus_xp")
	private Long petOutfitPlusXp;
	
	@Column(name="pet_outfit_plus_food")
	private Long petOutfitPlusFood;
	
	@Column(name="pet_outfit_plus_money")
	private Long petOutfitPlusMoney;
	
	@ManyToOne
	@JoinColumn(name="pet_id")
	private PetEntity pet;
	
	@Column(name="pet_outfit_skin_mode")
	private PetStatusEnergyEnum petOutfitSkinMode;
	@Column(name="pet_outfit_package_skin")
	private String petOutfitPackageSkin;


	
	public PetOutfitEntity() {
		super();
	}

	

	



	







	public PetOutfitEntity(Long petOutfitId, String petOutfitName, String archiveName, String originalName,
			String archivePath, String archiveType, Long petOutfitPlusXp, Long petOutfitPlusFood,
			Long petOutfitPlusMoney, PetEntity pet, PetStatusEnergyEnum petOutfitSkinMode,
			String petOutfitPackageSkin) {
		super();
		this.petOutfitId = petOutfitId;
		this.petOutfitName = petOutfitName;
		this.archiveName = archiveName;
		this.originalName = originalName;
		this.archivePath = archivePath;
		this.archiveType = archiveType;
		this.petOutfitPlusXp = petOutfitPlusXp;
		this.petOutfitPlusFood = petOutfitPlusFood;
		this.petOutfitPlusMoney = petOutfitPlusMoney;
		this.pet = pet;
		this.petOutfitSkinMode = petOutfitSkinMode;
		this.petOutfitPackageSkin = petOutfitPackageSkin;
	}















	public Long getPetOutfitId() {
		return petOutfitId;
	}

	public void setPetOutfitId(Long petOutfitId) {
		this.petOutfitId = petOutfitId;
	}

	public String getArchiveName() {
		return archiveName;
	}

	public void setArchiveName(String archiveName) {
		this.archiveName = archiveName;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public String getArchivePath() {
		return archivePath;
	}

	public void setArchivePath(String archivePath) {
		this.archivePath = archivePath;
	}

	public String getArchiveType() {
		return archiveType;
	}

	public void setArchiveType(String archiveType) {
		this.archiveType = archiveType;
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

	public Long getPetOutfitPlusMoney() {
		return petOutfitPlusMoney;
	}

	public void setPetOutfitPlusMoney(Long petOutfitPlusMoney) {
		this.petOutfitPlusMoney = petOutfitPlusMoney;
	}

	public PetEntity getPet() {
		return pet;
	}

	public void setPet(PetEntity pet) {
		this.pet = pet;
	}

	public String getPetOutfitName() {
		return petOutfitName;
	}

	public void setPetOutfitName(String petOutfitName) {
		this.petOutfitName = petOutfitName;
	}

	public PetStatusEnergyEnum getPetOutfitSkinMode() {
		return petOutfitSkinMode;
	}

	public void setPetOutfitSkinMode(PetStatusEnergyEnum petOutfitSkinMode) {
		this.petOutfitSkinMode = petOutfitSkinMode;
	}















	public String getPetOutfitPackageSkin() {
		return petOutfitPackageSkin;
	}


	public void setPetOutfitPackageSkin(String petOutfitPackageSkin) {
		this.petOutfitPackageSkin = petOutfitPackageSkin;
	}







	
	
	
	
}
