package com.calygam.back.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.calygam.back.models.PetEntity;
import com.calygam.back.projections.PetProjection;

@Repository
public interface PetRepository extends JpaRepository<PetEntity, Long> {
	
	Optional<PetEntity> findByPetName(String petName);
	
	@Query("""
			SELECT p FROM PetEntity p JOIN FETCH p.outfits o
			""")
	List<PetProjection> searchAllPets();
}
