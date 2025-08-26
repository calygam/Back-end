package com.calygam.back.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.calygam.back.dtos.LazyCommentsDTO;
import com.calygam.back.models.MessageActivityEntity;
 
@Repository
public interface MessageActivityRepository extends JpaRepository<MessageActivityEntity, Long> {
	@Query("""
			SELECT new com.calygam.back.dtos.LazyCommentsDTO (  
				msg.messageActivityId, 
				msg.activity.activityId, 
				msg.messageActivityDescription, 
				msg.messageUserOwner,
				msg.user.userId,
				msg.user.userName,
				msg.user.archiveName,
				msg.replyTo.messageActivityId)  
			FROM MessageActivityEntity msg  
			WHERE msg.activity.activityId = :activityId 
				AND (:lastMsgId IS NULL OR msg.messageActivityId < :lastMsgId) 
				AND msg.messageActivityIsPrivate IS FALSE
				AND msg.messageActivityType = 3
				AND msg.replyTo.messageActivityId IS NULL
			ORDER BY msg.messageActivityId DESC
			""")

	Page<LazyCommentsDTO> findLazyComments(Long activityId, Long lastMsgId, Pageable pageable);
}
