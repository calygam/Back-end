package com.calygam.back.enums;

public enum PetStatusEnergyEnum {
	HAPPY("FELIZ"),
	EXHAUSTED("EXAUSTO");
	
	private String energyStatusFlag;
	

	
	PetStatusEnergyEnum (String energyStatusFlag){
		this.energyStatusFlag = energyStatusFlag;
	}
	
	
	public String getEnergyStatusFlag() {
		return energyStatusFlag;
	}
	
}
