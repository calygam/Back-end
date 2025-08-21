package com.calygam.back.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.calygam.back.models.ControlApprenticePetEntity;

@Repository
public interface ControlApprenticePetRepository extends JpaRepository<ControlApprenticePetEntity, Long> {
	
	@Query("""
			SELECT  ctrl
			FROM ControlApprenticePetEntity ctrl 
			WHERE ctrl.apprentice.userId = :userId 
			AND ctrl.pet.petId = :petId
			""")
	Optional<ControlApprenticePetEntity> findByApprenticeUserIdAndPetId(@Param("userId") Long userId,@Param("petId") Long petId);
}
