package com.calygam.back.projections;

public interface StockPetOutfitProjection {
	Long getPetId();
	String getPetName();
	Long getPetOutfitPlusMoney();
	Long getPetOutfitPlusXp();
	Long getPetOutfitPlusFood();
	Long getPetMinEnergy();
	Long getPetDefaultEnergy();
	Long getPetMaxEnergy();
	Long getPetOutfitId();
	String getPetOutfitName();
	String getPetOutfitPackageSkin();
	String getPetOutfitArchiveName();
	Long getEmporiumId();
	Long getEmporiumItemId();
	Byte getEmporiumItemCatalogType();
	Long getEmporiumItemGoldCost();
	Long getEmporiumItemQtd();
	Byte getEmporiumItemRankRequired();
	boolean getEmporiumItemSelling();
}
