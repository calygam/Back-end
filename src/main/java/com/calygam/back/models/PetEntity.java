package com.calygam.back.models;

import java.util.ArrayList;
import java.util.List;

import com.calygam.back.enums.PetStatusEnergyEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_pets")
public class PetEntity {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pet_id")
	private Long petId;
	
	@Column(name="pet_name")
	private String petName;
	
	@Column(name="pet_boost_xp")
	private Double petBoostXp;
	
	@Column(name="pet_boost_money")
	private Double petBoostMoney;
	
	@Column(name="pet_boost_food")
	private Double petBoostFood;
	
	@Column(name="pet_min_energy")
	private Long petMinEnergy;
	
	@Column(name="pet_default_energy")
	private Long petDefaultEnergy;
	
	@Column(name="pet_max_energy")
	private Long petMaxEnergy;
	
	
	@Column(name="pet_status_energy")
	private PetStatusEnergyEnum petStatusEnergy;
	
	@OneToMany(mappedBy="pet",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PetOutfitEntity> outfits = new ArrayList<>();
	
	
	@OneToMany(mappedBy="pet",cascade = CascadeType.ALL,orphanRemoval = true)
	List<ControlApprenticePetEntity> apprenticesPet = new ArrayList<ControlApprenticePetEntity>();
	
	


	public PetEntity() {
		super();
	}

	public PetEntity(Long petId, String petName, Double petBoostXp, Double petBoostMoney, Double petBoostFood,
			Long petMinEnergy, Long petDefaultEnergy, Long petMaxEnergy, PetStatusEnergyEnum petStatusEnergy,
			List<PetOutfitEntity> outfits, List<ControlApprenticePetEntity> apprenticesPet) {
		super();
		this.petId = petId;
		this.petName = petName;
		this.petBoostXp = petBoostXp;
		this.petBoostMoney = petBoostMoney;
		this.petBoostFood = petBoostFood;
		this.petMinEnergy = petMinEnergy;
		this.petDefaultEnergy = petDefaultEnergy;
		this.petMaxEnergy = petMaxEnergy;
		this.petStatusEnergy = petStatusEnergy;
		this.outfits = outfits;
		this.apprenticesPet = apprenticesPet;
	}

	public List<PetOutfitEntity> getOutfits() {
		return outfits;
	}

	public void setOutfits(List<PetOutfitEntity> outfits) {
		this.outfits = outfits;
	}

	public Long getPetId() {
		return petId;
	}

	public void setPetId(Long petId) {
		this.petId = petId;
	}

	public Double getPetBoostXp() {
		return petBoostXp;
	}

	public void setPetBoostXp(Double petBoostXp) {
		this.petBoostXp = petBoostXp;
	}

	public Double getPetBoostMoney() {
		return petBoostMoney;
	}

	public void setPetBoostMoney(Double petBoostMoney) {
		this.petBoostMoney = petBoostMoney;
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

	public List<ControlApprenticePetEntity> getApprenticesPet() {
		return apprenticesPet;
	}

	public void setApprenticesPet(List<ControlApprenticePetEntity> apprenticesPet) {
		this.apprenticesPet = apprenticesPet;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public Long getPetDefaultEnergy() {
		return petDefaultEnergy;
	}

	public void setPetDefaultEnergy(Long petDefaultEnergy) {
		this.petDefaultEnergy = petDefaultEnergy;
	}
	
	
	
	
	
}
