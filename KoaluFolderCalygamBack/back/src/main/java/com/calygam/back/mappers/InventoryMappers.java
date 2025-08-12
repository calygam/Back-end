package com.calygam.back.mappers;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.calygam.back.dtos.InventoryPetsDTO;
import com.calygam.back.enums.ItemCatalogInventoryEnum;
import com.calygam.back.enums.PetStatusEnergyEnum;
import com.calygam.back.projections.InventoryPetsProjection;

@Component
public class InventoryMappers {
	
	public InventoryPetsDTO toInvPetsDTO(InventoryPetsProjection invP,Long userFood) {
		InventoryPetsDTO invPetsDTO = new InventoryPetsDTO();
	    Long maxEnergy = invP.getPetMaxEnergy();
	    Long baseFraction = (maxEnergy != null) ? Math.round((double) maxEnergy / 10) : 0L;
		invPetsDTO.setPetId(invP.getPetId());
		invPetsDTO.setPetName(invP.getPetName());
		invPetsDTO.setPetBoostMoney(invP.getPetBoostMoney());
		invPetsDTO.setPetBoostXp(invP.getPetBoostXp());
		invPetsDTO.setPetBoostFood(invP.getPetBoostFood());
		
		for(Long i=1L; i<=10L;i++){
	        Long TargetMultiplier = baseFraction*i;
	      
	        if(TargetMultiplier + invP.getApprenticePetEnergy()>=maxEnergy || TargetMultiplier + invP.getApprenticePetEnergy()>userFood){
	        	invPetsDTO.setPetMultiplierUp(i);
	         break;
	          
	        }
	      }
		
		if(invP.getPetOutfitPlusMoney()!=null&&invP.getPetOutfitPlusMoney()>0L) {
			invPetsDTO.setPetOutfitPlusMoney(invP.getPetOutfitPlusMoney());
		}else {
			invPetsDTO.setPetOutfitPlusMoney(0L);
		}
		if(invP.getPetOutfitPlusXp()!=null&&invP.getPetOutfitPlusXp()>0L) {
			invPetsDTO.setPetOutfitPlusXp(invP.getPetOutfitPlusXp());
		}else {
			invPetsDTO.setPetOutfitPlusXp(0L);
		}
		if(invP.getPetOutfitPlusFood()!=null&&invP.getPetOutfitPlusFood()>0L) {
			invPetsDTO.setPetOutfitPlusFood(invP.getPetOutfitPlusFood());
		}else {
			invPetsDTO.setPetOutfitPlusFood(0L);
		}
	    invPetsDTO.setPetMinEnergy(invP.getPetMinEnergy());
	    invPetsDTO.setApprenticePetEnergy(invP.getApprenticePetEnergy());
	    invPetsDTO.setPetMaxEnergy(invP.getPetMaxEnergy());

	    invPetsDTO.setPetFeedQtd(baseFraction);

	    invPetsDTO.setPetOutfitId(invP.getPetOutfitId());
	    invPetsDTO.setPetOutfitSkinMode(PetStatusEnergyEnum.values()[invP.getPetOutfitSkinMode()]);
	    invPetsDTO.setPetOutfitName(invP.getPetOutfitName());
	    invPetsDTO.setPetOutfitUrl(ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/file/read/skins/")        
                .path(invP.getPetOutfitArchiveName())
                .toUriString());
	    invPetsDTO.setApprenticeInventoryId(invP.getApprenticeInventoryId());
	    invPetsDTO.setApprenticeInventoryItemId(invP.getApprenticeInventoryItemId());
	    invPetsDTO.setInventapprenticeInventoryTag(ItemCatalogInventoryEnum.values()[invP.getApprenticeInventoryTag()]);
	    invPetsDTO.setApprenticeInventoryEquipped(invP.isApprenticeInventoryEquipped());
	    
	    return invPetsDTO;
	}
}
