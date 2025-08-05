package com.calygam.back.projections;


//a
public interface InventoryPetsProjection {
    Long getPetId();
    String getPetName();
    Double getPetBoostMoney();
    Double getPetBoostXp();
    Double getPetBoostFood();
    Long getPetOutfitPlusMoney();
    Long getPetOutfitPlusXp();
    Long getPetOutfitPlusFood();
    Long getPetMinEnergy();
    Long getApprenticePetEnergy();
    Long getPetMaxEnergy();

    Long getPetOutfitId();
    Integer getPetOutfitSkinMode();
    String getPetOutfitName();
    String getPetOutfitArchiveName();

    Long getApprenticeInventoryId();
    Integer getApprenticeInventoryTag();
    Long getApprenticeInventoryItemId();
    boolean isApprenticeInventoryEquipped();
}
