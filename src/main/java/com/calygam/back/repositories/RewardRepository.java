package com.calygam.back.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.calygam.back.enums.DifficultyEnum;
import com.calygam.back.models.RewardPackageEntity;
import com.calygam.back.projections.RewardPackageProjection;

@Repository
public interface RewardRepository extends JpaRepository<RewardPackageEntity, Long> {
	
	Optional<RewardPackageEntity> findByRewardActivityDifficulty(DifficultyEnum rewardActivityDifficulty);
	
	@Query(value="""
			SELECT * FROM tb_reward_package
			""",nativeQuery=true)
	List<RewardPackageProjection> findAllRewards();
	
}
