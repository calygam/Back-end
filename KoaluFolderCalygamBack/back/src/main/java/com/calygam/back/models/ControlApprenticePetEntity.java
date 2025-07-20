package com.calygam.back.models;

import com.calygam.back.enums.PetStatusEnergyEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_control_apprentice_x_pet")
public class ControlApprenticePetEntity {
	
	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="apprentice_pet_id")
	private Long apprenticePetId;
	
	@Column(name="apprentice_pet_energy")
	private Long apprenticePetEnergy;
	
	@Column(name="apprentice_pet_energy_state")
	private PetStatusEnergyEnum apprenticePetEnergyState;
	
	
	@Column(name="apprentice_pet_equipped_skin")
	private boolean apprenticePetEquippedSkin;
	
	@ManyToOne
	@JoinColumn(name="pet_id")
	private PetEntity pet;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserEntity apprentice;
	
	
	
	
	
	public Long getApprenticePetId() {
		return apprenticePetId;
	}



	public void setApprenticePetId(Long apprenticePetId) {
		this.apprenticePetId = apprenticePetId;
	}



	public Long getApprenticePetEnergy() {
		return apprenticePetEnergy;
	}



	public void setApprenticePetEnergy(Long apprenticePetEnergy) {
		 this.apprenticePetEnergy = apprenticePetEnergy;

		    if (this.apprenticePetEnergy < this.pet.getPetMinEnergy()) {
		        this.apprenticePetEnergyState = PetStatusEnergyEnum.EXHAUSTED;
		    } else {
		        this.apprenticePetEnergyState = PetStatusEnergyEnum.HAPPY;
		    }
	}



	public PetStatusEnergyEnum getApprenticePetEnergyState() {
		return apprenticePetEnergyState;
	}



	public void setApprenticePetEnergyState(PetStatusEnergyEnum apprenticePetEnergyState) {
		this.apprenticePetEnergyState = apprenticePetEnergyState;
	}



	public PetEntity getPet() {
		return pet;
	}



	public void setPet(PetEntity pet) {
		this.pet = pet;
	}



	public UserEntity getApprentice() {
		return apprentice;
	}



	public void setApprentice(UserEntity apprentice) {
		this.apprentice = apprentice;
	}



}
