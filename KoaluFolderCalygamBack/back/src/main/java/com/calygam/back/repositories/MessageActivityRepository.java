package com.calygam.back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.calygam.back.models.MessageActivityEntity;

@Repository
public interface MessageActivityRepository extends JpaRepository<MessageActivityEntity, Long> {

}
