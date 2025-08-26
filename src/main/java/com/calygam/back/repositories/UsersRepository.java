package com.calygam.back.repositories;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.calygam.back.dtos.DataUtilUserDTO;
import com.calygam.back.models.UserEntity;
import com.calygam.back.projections.AdminAnalisisProjection;
import com.calygam.back.projections.TeacherDashProjection;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity,Long> {
	
    @Query("SELECT new com.calygam.back.dtos.DataUtilUserDTO(" +
            "u.userId, u.userName, u.userEmail,u.archiveName, u.xp, " +
            " u.userRole, u.userMoney) " +
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
            "   (SELECT COUNT(DISTINCT user_id) FROM tb_users usr WHERE usr.user_role = 2) AS totalTeachers, " +
            "   (SELECT COUNT(*) FROM tb_trail tbt WHERE tbt.trail_status = 0) AS activeTrails, " +
            "   (SELECT COUNT(DISTINCT user_id) FROM tb_trail_x_activity_progress prg) AS members", 
           nativeQuery = true)
    AdminAnalisisProjection getTotalAnalisisAdmin();
    
    @Query(value = """
    	    SELECT u.user_id AS userId,
    	           u.user_name AS userName,
    	           u.user_email AS userEmail,
    	           
    	           u.user_xp AS xp,
    	           u.user_archive_name AS userImagePerfil,
    	
    	           u.user_role AS userRole,
    	           u.user_money AS userMoney,
    	           u.user_status AS userStatus
    	    FROM tb_users u
    	    WHERE u.user_role = 2
    	""", 
    	countQuery = "SELECT COUNT(*) FROM tb_users WHERE user_role = 2",
    	nativeQuery = true)
    	Page<TeacherDashProjection> findTeachersByRole(Pageable pageable);
    
  
	
}
//