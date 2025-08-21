package com.calygam.back.projections;

import com.calygam.back.enums.PetStatusEnergyEnum;

public interface PetOutfitProjection {
	//DADOS - CABEÇA SABENDO ONDE ESTÁ O CORPO
	Long getPetOutfitId();
	String getPetOutfitName();
	Long getPetOutfitPlusMoney();
	Long getPetOutfitPlusXp();
	Long getPetOutfitPlusFood();
	PetStatusEnergyEnum getPetOutfitSkinMode();
	String getArchiveName();
	String getPetOutfitPackageSkin();
}
