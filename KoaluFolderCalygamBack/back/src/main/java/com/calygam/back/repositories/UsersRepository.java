package com.calygam.back.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.calygam.back.dtos.DataUtilUserDTO;
import com.calygam.back.models.UserEntity;
import com.calygam.back.projections.AdminAnalisisProjection;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity,Long> {
	
    @Query("SELECT new com.calygam.back.dtos.DataUtilUserDTO(" +
            "u.userId, u.userName, u.userEmail, u.userCpf, u.xp, " +
            " u.userImagePerfil,u.userRole, u.userMoney) " +
            "FROM UserEntity u WHERE u.userId = :userId")
     Optional<DataUtilUserDTO> findByUserId(@Param("userId") Long userId);
    
    @Query("""
    		SELECT u FROM UserEntity u LEFT JOIN FETCH u.trails WHERE u.userId = :userId
    		""")
     Optional<UserEntity> findUserPasswordById(@Param("userId") Long userId);
    
    @Query("SELECT u FROM UserEntity u WHERE u.userId = :userId ")
    Optional<UserEntity>  findEntityByUserId(@Param("userId") Long userId);
    
    @Query("SELECT u FROM UserEntity u WHERE u.userEmail = :userEmail ")
    Optional<UserEntity>  findEntityByEmail(@Param("userEmail") String userEmail);
    

    @Query(value = "SELECT " +
            "   (SELECT COUNT(DISTINCT user_id) FROM tb_users usr WHERE usr.user_role = 1) AS totalTeachers, " +
            "   (SELECT COUNT(*) FROM tb_trail tbt WHERE tbt.trail_status = 0) AS activeTrails, " +
            "   (SELECT COUNT(DISTINCT user_id) FROM tb_trail_x_activity_progress prg) AS members", 
           nativeQuery = true)
    AdminAnalisisProjection getTotalAnalisisAdmin();
    
  
	
}
//