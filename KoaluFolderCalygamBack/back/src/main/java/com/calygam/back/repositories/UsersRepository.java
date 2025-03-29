package com.calygam.back.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.calygam.back.dtos.DataUtilUserDTO;
import com.calygam.back.models.UserEntity;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity,Long> {
	
    @Query("SELECT new com.calygam.back.dtos.DataUtilUserDTO(" +
            "u.userId, u.userName, u.userEmail, u.userCpf, u.xp, " +
            " u.userImageParfil, u.userMoney) " +
            "FROM UserEntity u WHERE u.userId = :userId")
     Optional<DataUtilUserDTO> findByUserId(@Param("userId") Long userId);
	
}
