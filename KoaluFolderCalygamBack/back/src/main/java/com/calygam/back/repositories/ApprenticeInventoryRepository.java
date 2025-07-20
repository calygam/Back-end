package com.calygam.back.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.calygam.back.enums.ItemCatalogInventoryEnum;
import com.calygam.back.models.ApprenticeInventoryEntity;

@Repository
public interface ApprenticeInventoryRepository extends JpaRepository<ApprenticeInventoryEntity, Long> {

	
	Optional<ApprenticeInventoryEntity> findByApprenticeInventoryItemIdAndApprenticeInventoryTag(Long apprenticeInventoryItemId , ItemCatalogInventoryEnum apprenticeInventoryTag);
	Optional<ApprenticeInventoryEntity> findByApprentice_UserIdAndApprenticeInventoryTagAndApprenticeInventoryEquippedTrue(Long userId, ItemCatalogInventoryEnum apprenticeInventoryTag);
	Optional<ApprenticeInventoryEntity> findByApprentice_UserIdAndApprenticeInventoryTagAndApprenticeInventoryItemId(Long userId, ItemCatalogInventoryEnum apprenticeInventoryTag, Long apprenticeInventoryItemId);
	
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
}
