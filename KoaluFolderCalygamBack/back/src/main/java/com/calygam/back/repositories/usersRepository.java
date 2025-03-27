package com.calygam.back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.calygam.back.models.UserEntity;

@Repository
public interface usersRepository extends JpaRepository<UserEntity,Long> {
	
}
