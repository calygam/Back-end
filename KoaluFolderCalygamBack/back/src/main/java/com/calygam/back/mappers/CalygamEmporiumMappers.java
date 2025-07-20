package com.calygam.back.mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.calygam.back.dtos.StockPetDTO;
import com.calygam.back.dtos.StockSkinsDTO;
import com.calygam.back.enums.UserRankEnum;
import com.calygam.back.projections.StockPetOutfitProjection;
import com.calygam.back.projections.StockPetProjection;
import com.calygam.back.utils.EnumUtils;

@Component
public class CalygamEmporiumMappers {
	
	@Autowired
	private EnumUtils enumUtils;
	
	public StockPetDTO toStockPetDTO(StockPetProjection sp){
		StockPetDTO stockPetDTO = new StockPetDTO();
		stockPetDTO.setPetId(sp.getPetId());
		stockPetDTO.setPetName(sp.getPetName());
		stockPetDTO.setPetBoostMoney(sp.getPetBoostMoney());
		stockPetDTO.setPetBoostXp(sp.getPetBoostXp());
		stockPetDTO.setPetBoostFood(sp.getPetBoostFood());
		stockPetDTO.setPetMinEnergy(sp.getPetMinEnergy());
		stockPetDTO.setPetDefaultEnergy(sp.getPetDefaultEnergy());
		stockPetDTO.setPetMaxEnergy(sp.getPetMaxEnergy());
		stockPetDTO.setPetOutfitId(sp.getPetOutfitId());
		stockPetDTO.setPetOutfitName(sp.getPetOutfitName());
		stockPetDTO.setPetOutfitUrl(ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/file/read/skins/")        
                .path(sp.getPetOutfitArchiveName())
                .toUriString());
		stockPetDTO.setEmporiumId(sp.getEmporiumId());
		stockPetDTO.setEmporiumItemId(sp.getEmporiumItemId());
		stockPetDTO.setEmporiumItemCatalogType(enumUtils.itemCatalogInventoryEnumNameFromCode(sp.getEmporiumItemCatalogType()));
		stockPetDTO.setEmporiumItemGoldCost(sp.getEmporiumItemGoldCost());
		UserRankEnum userRankEnum = UserRankEnum.fromCode(sp.getEmporiumItemRankRequired());
		String labelRank = userRankEnum.getNameRank();
		stockPetDTO.setEmporiumItemRankRequired(labelRank);
		
		stockPetDTO.setEmporiumItemQtd(sp.getEmporiumItemQtd());
	
		stockPetDTO.setEmporiumItemSelling(sp.getEmporiumItemSelling());
		Integer xpRequired = UserRankEnum.getXpByRankName(labelRank);
		stockPetDTO.setEmporiumXpRequired(xpRequired);
		
		return stockPetDTO;
		
	}
	
	public StockSkinsDTO toStockSkinsDTO(StockPetOutfitProjection skin){
		StockSkinsDTO stockSkinsDTO = new StockSkinsDTO();
		stockSkinsDTO.setPetId(skin.getPetId());
		stockSkinsDTO.setPetName(skin.getPetName());
		stockSkinsDTO.setPetOutfitId(skin.getPetOutfitId());
		stockSkinsDTO.setPetOutfitName(skin.getPetOutfitName());
		stockSkinsDTO.setPetOutfitPackageSkin(skin.getPetOutfitPackageSkin());
		stockSkinsDTO.setPetOutfitPlusMoney(skin.getPetOutfitPlusMoney());
		stockSkinsDTO.setPetOutfitPlusXp(skin.getPetOutfitPlusXp());
		stockSkinsDTO.setPetOutfitPlusFood(skin.getPetOutfitPlusFood());
		stockSkinsDTO.setPetOutfitUrl(ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/file/read/skins/")        
                .path(skin.getPetOutfitArchiveName())
                .toUriString());
		stockSkinsDTO.setEmporiumId(skin.getEmporiumId());
		stockSkinsDTO.setEmporiumItemId(skin.getEmporiumItemId());
		stockSkinsDTO.setEmporiumItemCatalogType(enumUtils.itemCatalogInventoryEnumNameFromCode(skin.getEmporiumItemCatalogType()));
		stockSkinsDTO.setEmporiumItemGoldCost(skin.getEmporiumItemGoldCost());
		UserRankEnum userRankEnum = UserRankEnum.fromCode(skin.getEmporiumItemRankRequired());
		String labelRank = userRankEnum.getNameRank();
		stockSkinsDTO.setEmporiumItemRankRequired(labelRank);
		
		stockSkinsDTO.setEmporiumItemQtd(skin.getEmporiumItemQtd());
	
		stockSkinsDTO.setEmporiumItemSelling(skin.getEmporiumItemSelling());
		Integer xpRequired = UserRankEnum.getXpByRankName(labelRank);
		stockSkinsDTO.setEmporiumXpRequired(xpRequired);
		
		return stockSkinsDTO;
		
	}
}
