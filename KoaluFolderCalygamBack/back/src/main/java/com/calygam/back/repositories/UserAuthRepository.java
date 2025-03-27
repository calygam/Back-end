package com.calygam.back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.calygam.back.models.UserEntity;

@Repository
public interface UserAuthRepository extends JpaRepository<UserEntity, Long> {
	UserEntity findByUserEmail(String userEmail);
	UserEntity findByUserCpf(String userCpf);
}
