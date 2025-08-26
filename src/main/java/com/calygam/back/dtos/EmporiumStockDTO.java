package com.calygam.back.dtos;

import java.util.List;

public class EmporiumStockDTO {
	List<StockPetDTO> pets;
	List<StockSkinsDTO> skins;
	
	

	public EmporiumStockDTO() {
		super();
	}







	public EmporiumStockDTO(List<StockPetDTO> pets, List<StockSkinsDTO> skins) {
		super();
		this.pets = pets;
		this.skins = skins;
	}







	public List<StockPetDTO> getPets() {
		return pets;
	}



	public void setPets(List<StockPetDTO> pets) {
		this.pets = pets;
	}



	public List<StockSkinsDTO> getSkins() {
		return skins;
	}



	public void setSkins(List<StockSkinsDTO> skins) {
		this.skins = skins;
	}
	
	
	
	
	
}
