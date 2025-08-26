package com.calygam.back.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.calygam.back.models.PetOutfitEntity;

@Repository
public interface PetOutfitRepository extends JpaRepository<PetOutfitEntity, Long> {
	
	List<PetOutfitEntity> findByPet_petId(Long petId);
}
