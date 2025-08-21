package com.calygam.back.mappers;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.calygam.back.dtos.PetDTO;
import com.calygam.back.dtos.PetOutfitDTO;
import com.calygam.back.projections.PetOutfitProjection;
import com.calygam.back.projections.PetProjection;

@Component
public class PetMapper {
	
	public PetOutfitDTO toPetOutfitDTO(PetOutfitProjection petOutfitProjection) {
		PetOutfitDTO petOutfitDTO = new PetOutfitDTO();
		petOutfitDTO.setPetOutfitId(petOutfitProjection.getPetOutfitId());
		petOutfitDTO.setPetOutfitName(petOutfitProjection.getPetOutfitName());
		petOutfitDTO.setPetOutfitImage(null);
		petOutfitDTO.setPetOutfitPlusMoney(petOutfitProjection.getPetOutfitPlusMoney());
		petOutfitDTO.setPetOutfitPlusXp(petOutfitProjection.getPetOutfitPlusXp());
		petOutfitDTO.setPetOutfitPlusFood(petOutfitProjection.getPetOutfitPlusFood());
		petOutfitDTO.setPetUrlImage(ServletUriComponentsBuilder
	                .fromCurrentContextPath()
	                .path("/file/read/skins/")        
	                .path(petOutfitProjection.getArchiveName())
	                .toUriString());
		petOutfitDTO.setPetOutfitSkinMode(petOutfitProjection.getPetOutfitSkinMode());
		petOutfitDTO.setPetOutfitPackageSkin(petOutfitProjection.getPetOutfitPackageSkin());
		return petOutfitDTO;

	}
	
	public PetDTO toPetDTO(PetProjection petProjection) {
		PetDTO petDTO = new PetDTO();
		petDTO.setPetId(petProjection.getPetId());
		petDTO.setPetName(petProjection.getPetName());
		petDTO.setPetBoostMoney(petProjection.getPetBoostMoney());
		petDTO.setPetBoostXp(petProjection.getPetBoostXp());
		petDTO.setPetBoostFood(petProjection.getPetBoostFood());
		petDTO.setPetMinEnergy(petProjection.getPetMinEnergy());
		petDTO.setPetDefaultEnergy(petProjection.getPetDefaultEnergy());
		petDTO.setPetMaxEnergy(petProjection.getPetMaxEnergy());
		petDTO.setPetStatusEnergy(petProjection.getPetStatusEnergy());
	
		petDTO.setOutfits(petProjection.getOutfits().stream()
		.map(this::toPetOutfitDTO)
		.collect(Collectors.toList()));
		return petDTO;

	}
}
