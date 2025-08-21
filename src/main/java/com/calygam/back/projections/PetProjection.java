package com.calygam.back.projections;

import java.util.List;

import com.calygam.back.dtos.PetOutfitDTO;
import com.calygam.back.enums.PetStatusEnergyEnum;

public interface PetProjection {
	//DADOS - CORPO SEM SABER ONDE ESTÁ A CABEÇA
	Long getPetId();
	String getPetName();
	Double getPetBoostMoney();
	Double getPetBoostXp();
	Double getPetBoostFood();
	Long getPetMinEnergy();
	Long getPetDefaultEnergy();
	Long getPetMaxEnergy();
	PetStatusEnergyEnum getPetStatusEnergy();
	List<PetOutfitProjection> getOutfits();

	
}
