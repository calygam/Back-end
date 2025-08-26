package com.calygam.back.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.calygam.back.enums.ItemCatalogInventoryEnum;
import com.calygam.back.models.CalygamEmporiumEntity;
import com.calygam.back.projections.StockPetOutfitProjection;
import com.calygam.back.projections.StockPetProjection;

@Repository
public interface EmporiumRepository extends JpaRepository<CalygamEmporiumEntity, Long> {
	
	
	Optional<CalygamEmporiumEntity> findByEmporiumItemIdAndEmporiumItemCatalogType(Long emporiumItemId, ItemCatalogInventoryEnum emporiumItemCatalogType);
	
	@Query(value="""
			SELECT 
				pet.pet_id AS petId,
				pet.pet_name AS petName,
				pet.pet_boost_money AS petBoostMoney,
				pet.pet_boost_xp AS petBoostXp,
				pet.pet_boost_food AS petBoostFood,
			    pet.pet_min_energy AS petMinEnergy,
				pet.pet_default_energy AS petDefaultEnergy,
			    pet.pet_max_energy AS petMaxEnergy,
			    skin.pet_outfit_id AS petOutfitId,
			    skin.pet_outfit_name AS petOutfitName,
			    skin.pet_outfit_archive_name AS petOutfitArchiveName,
			    emp.emporium_id AS emporiumId,
			    emp.emporium_item_catalog_type AS emporiumItemCatalogType,
			    emp.emporium_item_id AS emporiumItemId,
			    emp.emporium_item_gold_cost AS emporiumItemGoldCost,
			    emp.emporium_item_qtd AS emporiumItemQtd,
			    emp.emporium_item_rank_required AS emporiumItemRankRequired,
			    emp.emporium_item_selling AS emporiumItemSelling 
			FROM tb_pets pet 
			INNER JOIN tb_pet_outfits skin ON skin.pet_id = pet.pet_id
			INNER JOIN tb_calygam_emporium emp ON emporium_item_id = pet.pet_id 
			WHERE skin.pet_outfit_skin_mode = 1 AND skin.pet_outfit_package_skin LIKE '%DEFAULT_EXHAUSTED%'
			""",nativeQuery=true)
	List<StockPetProjection> getStockPetsInEmporium();
	
	@Query(value="""
			SELECT 
				pet.pet_id AS petId,
				pet.pet_name AS petName,
				pet.pet_boost_money AS petBoostMoney,
				pet.pet_boost_xp AS petBoostXp,
				pet.pet_boost_food AS petBoostFood,
			    pet.pet_min_energy AS petMinEnergy,
				pet.pet_default_energy AS petDefaultEnergy,
			    pet.pet_max_energy AS petMaxEnergy,
			    skin.pet_outfit_id AS petOutfitId,
			    skin.pet_outfit_name AS petOutfitName,
			    skin.pet_outfit_archive_name AS petOutfitArchiveName,
			    emp.emporium_id AS emporiumId,
			    emp.emporium_item_catalog_type AS emporiumItemCatalogType,
			    emp.emporium_item_id AS emporiumItemId,
			    emp.emporium_item_gold_cost AS emporiumItemGoldCost,
			    emp.emporium_item_qtd AS emporiumItemQtd,
			    emp.emporium_item_rank_required AS emporiumItemRankRequired,
			    emp.emporium_item_selling AS emporiumItemSelling 
			FROM tb_pets pet 
			INNER JOIN tb_pet_outfits skin ON skin.pet_id = pet.pet_id
			INNER JOIN tb_calygam_emporium emp ON emporium_item_id = pet.pet_id 
			WHERE skin.pet_outfit_skin_mode = 1 AND skin.pet_outfit_package_skin LIKE '%DEFAULT_EXHAUSTED%' ORDER BY emp.emporium_item_gold_cost ASC
			""",nativeQuery=true)
	List<StockPetProjection> getStockPetsInEmporiumASC();
	
	@Query(value="""
			SELECT 
				pet.pet_id AS petId,
				pet.pet_name AS petName,
				pet.pet_boost_money AS petBoostMoney,
				pet.pet_boost_xp AS petBoostXp,
				pet.pet_boost_food AS petBoostFood,
			    pet.pet_min_energy AS petMinEnergy,
				pet.pet_default_energy AS petDefaultEnergy,
			    pet.pet_max_energy AS petMaxEnergy,
			    skin.pet_outfit_id AS petOutfitId,
			    skin.pet_outfit_name AS petOutfitName,
			    skin.pet_outfit_archive_name AS petOutfitArchiveName,
			    emp.emporium_id AS emporiumId,
			    emp.emporium_item_catalog_type AS emporiumItemCatalogType,
			    emp.emporium_item_id AS emporiumItemId,
			    emp.emporium_item_gold_cost AS emporiumItemGoldCost,
			    emp.emporium_item_qtd AS emporiumItemQtd,
			    emp.emporium_item_rank_required AS emporiumItemRankRequired,
			    emp.emporium_item_selling AS emporiumItemSelling 
			FROM tb_pets pet 
			INNER JOIN tb_pet_outfits skin ON skin.pet_id = pet.pet_id
			INNER JOIN tb_calygam_emporium emp ON emporium_item_id = pet.pet_id 
			WHERE skin.pet_outfit_skin_mode = 1 AND skin.pet_outfit_package_skin LIKE '%DEFAULT_EXHAUSTED%' ORDER BY emp.emporium_item_gold_cost DESC
			""",nativeQuery=true)
	List<StockPetProjection> getStockPetsInEmporiumDESC();
	
@Query(value = """
SELECT 
	pet.pet_id,
	pet.pet_name,
    skin.pet_outfit_plus_money,
    skin.pet_outfit_plus_xp,
    skin.pet_outfit_plus_food,
    pet.pet_min_energy,
	pet.pet_default_energy,
    pet.pet_max_energy,
    skin.pet_outfit_id,
    skin.pet_outfit_name,
    skin.pet_outfit_package_skin,
    skin.pet_outfit_archive_name,
    emp.emporium_id,
    emp.emporium_item_catalog_type,
    emp.emporium_item_id,
    emp.emporium_item_gold_cost,
    emp.emporium_item_qtd,
    emp.emporium_item_rank_required,
    emp.emporium_item_selling
 FROM tb_pets pet 
INNER JOIN tb_pet_outfits skin ON skin.pet_id = pet.pet_id
INNER JOIN tb_calygam_emporium emp ON emporium_item_id = skin.pet_outfit_id
WHERE skin.pet_outfit_skin_mode = 1 AND skin.pet_outfit_package_skin LIKE '%EXHAUSTED%' AND skin.pet_outfit_package_skin NOT LIKE '%DEFAULT%';
		""",nativeQuery=true)
List<StockPetOutfitProjection> getStockOutfitsInEmporium();
}
