package com.calygam.back.projections;

import com.calygam.back.enums.ItemCatalogInventoryEnum;
import com.calygam.back.enums.UserRankEnum;

public interface StockPetProjection {
	Long getPetId();
	String getPetName();
	Double getPetBoostMoney();
	Double getPetBoostXp();
	Double getPetBoostFood();
	Long getPetMinEnergy();
	Long getPetDefaultEnergy();
	Long getPetMaxEnergy();
	Long getPetOutfitId();
	String getPetOutfitName();
	String getPetOutfitArchiveName();
	Long getEmporiumId();
	Long getEmporiumItemId();
	Byte getEmporiumItemCatalogType();
	Long getEmporiumItemGoldCost();
	Long getEmporiumItemQtd();
	Byte getEmporiumItemRankRequired();
	boolean getEmporiumItemSelling();
}
