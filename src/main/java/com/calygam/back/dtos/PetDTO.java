package com.calygam.back.dtos;

import java.util.ArrayList;
import java.util.List;

import com.calygam.back.enums.PetStatusEnergyEnum;
import com.fasterxml.jackson.annotation.JsonInclude;

public class PetDTO {
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
		private List<PetOutfitDTO> outfits = new ArrayList<PetOutfitDTO>();
		
		public PetDTO() {
			super();
		}
	
		public PetDTO(Long petId, String petName, Double petBoostMoney, Double petBoostXp, Double petBoostFood,
				Long petMinEnergy, Long petDefaultEnergy, Long petMaxEnergy, PetStatusEnergyEnum petStatusEnergy,
				List<PetOutfitDTO> outfits) {
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
			this.outfits = outfits;
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
		public PetStatusEnergyEnum getPetStatusEnergy() {
			return petStatusEnergy;
		}
		public void setPetStatusEnergy(PetStatusEnergyEnum petStatusEnergy) {
			this.petStatusEnergy = petStatusEnergy;
		}

		public List<PetOutfitDTO> getOutfits() {
			return outfits;
		}

		public void setOutfits(List<PetOutfitDTO> outfits) {
			this.outfits = outfits;
		}

	
		
		
		
}
