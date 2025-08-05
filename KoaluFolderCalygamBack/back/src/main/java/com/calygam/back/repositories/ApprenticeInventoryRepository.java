package com.calygam.back.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.calygam.back.enums.ItemCatalogInventoryEnum;
import com.calygam.back.models.ApprenticeInventoryEntity;
import com.calygam.back.projections.InventoryPetsProjection;

@Repository
public interface ApprenticeInventoryRepository extends JpaRepository<ApprenticeInventoryEntity, Long> {
	
	@Query(value="""
		SELECT  * FROM tb_apprentice_inventory inv 
		INNER JOIN tb_pet_outfits petOutfits ON petOutfits.pet_outfit_id = inv.apprentice_inventory_item_id 
		WHERE inv.apprentice_inventory_equipped=1 AND inv.apprentice_inventory_tag=1  AND petOutfits.pet_id = :petId AND inv.user_id = :userId
			""",nativeQuery=true)
	Optional<ApprenticeInventoryEntity> targetExistsByEquippedTrueAndPetId(@Param("petId") Long petId, @Param("userId") Long userId);

	
	Optional<ApprenticeInventoryEntity> findByApprenticeInventoryItemIdAndApprenticeInventoryTag(Long apprenticeInventoryItemId , ItemCatalogInventoryEnum apprenticeInventoryTag);
	Optional<ApprenticeInventoryEntity> findByApprentice_UserIdAndApprenticeInventoryTagAndApprenticeInventoryItemIdAndApprenticeInventoryEquippedTrue(Long userId, ItemCatalogInventoryEnum apprenticeInventoryTag, Long apprenticeInventoryItemId);
	Optional<ApprenticeInventoryEntity> findByApprentice_UserIdAndApprenticeInventoryTagAndApprenticeInventoryEquippedTrue(Long userId, ItemCatalogInventoryEnum apprenticeInventoryTag);
	
	Optional<ApprenticeInventoryEntity> findByApprentice_UserIdAndApprenticeInventoryTagAndApprenticeInventoryItemId(Long userId, ItemCatalogInventoryEnum apprenticeInventoryTag, Long apprenticeInventoryItemId);

	boolean existsByApprentice_UserIdAndApprenticeInventoryTag(Long userId, ItemCatalogInventoryEnum catalogType);
	
	@Query(value = """
			SELECT inv.apprentice_inventory_item_id  
			FROM tb_apprentice_inventory inv 
			WHERE inv.apprentice_inventory_tag=0 
			AND inv.user_id =:userId;
			""",nativeQuery=true)
	List<Long> getIdsFromPetsInInventory(@Param("userId") Long userId);
	
	@Query(value = """
			SELECT inv.apprentice_inventory_item_id  
			FROM tb_apprentice_inventory inv
			WHERE inv.apprentice_inventory_tag=1 
			AND inv.user_id =:userId;
			""",nativeQuery=true)
	List<Long> getIdsFromSkinsInInventory(@Param("userId") Long userId);
	
	@Query(value="""
			SELECT 
			    pet.pet_id AS petId,
			    pet.pet_name AS petName,
			    pet.pet_boost_money AS petBoostMoney,
			    pet.pet_boost_xp AS petBoostXp,
			    pet.pet_boost_food AS petBoostFood,
			    pet.pet_min_energy AS petMinEnergy,
			    ctrl.apprentice_pet_energy AS apprenticePetEnergy,
			    pet.pet_max_energy AS petMaxEnergy,
			
			    skin.pet_outfit_id AS petOutfitId,
			    skin.pet_outfit_skin_mode AS petOutfitSkinMode,
			    skin.pet_outfit_name AS petOutfitName,
			    skin.pet_outfit_archive_name AS petOutfitArchiveName,
			
			    inv.apprentice_inventory_id AS apprenticeInventoryId,
			    inv.apprentice_inventory_tag AS apprenticeInventoryTag,
			    inv.apprentice_inventory_item_id AS apprenticeInventoryItemId,
			    inv.apprentice_inventory_equipped AS apprenticeInventoryEquipped
			    
			FROM tb_pets pet 
			
			INNER JOIN tb_pet_outfits skin 
			    ON skin.pet_id = pet.pet_id 
			    
			INNER JOIN tb_control_apprentice_x_pet ctrl ON ctrl.pet_id = pet.pet_id

			INNER JOIN tb_apprentice_inventory invSkin 
			    ON invSkin.apprentice_inventory_item_id = skin.pet_outfit_id
			    
			INNER JOIN tb_apprentice_inventory inv 
			    ON inv.apprentice_inventory_item_id = pet.pet_id
			    
			WHERE   inv.user_id= :userId 
				AND invSkin.user_id = :userId
				AND ctrl.user_id = :userId
			    AND inv.apprentice_inventory_tag = 0  
			    AND inv.apprentice_inventory_equipped = 0  AND invSkin.apprentice_inventory_tag = 1  
			    AND invSkin.apprentice_inventory_equipped = 1
			""",nativeQuery=true)
	List<InventoryPetsProjection> getInventoryPetsOfOneUser(@Param("userId") Long userId);
	
	
	@Query(value="""
			SELECT 
			    pet.pet_id AS petId,
			    pet.pet_name AS petName,
			    pet.pet_boost_money AS petBoostMoney,
			    pet.pet_boost_xp AS petBoostXp,
			    pet.pet_boost_food AS petBoostFood,
			    skin.pet_outfit_plus_money as petOutfitPlusMoney,
			    skin.pet_outfit_plus_xp as petOutfitPlusXp,
			    skin.pet_outfit_plus_food as petOutfitPlusFood,
			    pet.pet_min_energy AS petMinEnergy,
			    ctrl.apprentice_pet_energy AS apprenticePetEnergy,
			    pet.pet_max_energy AS petMaxEnergy,
			
			    skin.pet_outfit_id AS petOutfitId,
			    skin.pet_outfit_skin_mode AS petOutfitSkinMode,
			    skin.pet_outfit_name AS petOutfitName,
			    skin.pet_outfit_archive_name AS petOutfitArchiveName,
			
			    inv.apprentice_inventory_id AS apprenticeInventoryId,
			    inv.apprentice_inventory_tag AS apprenticeInventoryTag,
			    inv.apprentice_inventory_item_id AS apprenticeInventoryItemId,
			    inv.apprentice_inventory_equipped AS apprenticeInventoryEquipped
			    
			FROM tb_pets pet 
			
			INNER JOIN tb_pet_outfits skin 
			    ON skin.pet_id = pet.pet_id 
			    
			INNER JOIN tb_control_apprentice_x_pet ctrl ON ctrl.pet_id = pet.pet_id

			INNER JOIN tb_apprentice_inventory invSkin 
			    ON invSkin.apprentice_inventory_item_id = skin.pet_outfit_id
			    
			INNER JOIN tb_apprentice_inventory inv 
			    ON inv.apprentice_inventory_item_id = pet.pet_id
			    
			WHERE   inv.user_id= :userId 
				AND invSkin.user_id = :userId
				AND ctrl.user_id = :userId
			    AND inv.apprentice_inventory_tag = 0  
			    AND inv.apprentice_inventory_equipped = 1  AND invSkin.apprentice_inventory_tag = 1  
			    AND invSkin.apprentice_inventory_equipped = 1
			""",nativeQuery=true)
	InventoryPetsProjection getInventoryPetEquipped(@Param("userId") Long userId);
}
