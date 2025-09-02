package com.calygam.back.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.calygam.back.dtos.LazyCommentsDTO;
import com.calygam.back.models.MessageActivityEntity;
//..
@Repository
public interface MessageActivityRepository extends JpaRepository<MessageActivityEntity, Long> {
	@Query("""
		    SELECT new com.calygam.back.dtos.LazyCommentsDTO(  
		        msg.messageActivityId, 
		        msg.activity.activityId, 
		        msg.messageActivityDescription, 
		        msg.messageUserOwner,
		        msg.user.userId,
		        msg.user.userName,
		        msg.user.archiveName,
		        msg.replyTo.messageActivityId,
		        msg.messageSize,
		        COUNT(r.messageActivityId)
		    )  
		    FROM MessageActivityEntity msg  
		      LEFT JOIN MessageActivityEntity r ON r.replyTo.messageActivityId = msg.messageActivityId 
		    WHERE msg.activity.activityId = :activityId 
		      AND (:lastMsgId IS NULL OR msg.messageActivityId < :lastMsgId) 
		      AND msg.messageActivityIsPrivate IS FALSE
		      AND msg.messageActivityType = 3
		      AND msg.replyTo.messageActivityId IS NULL
		    GROUP BY 
		        msg.messageActivityId, 
		        msg.activity.activityId, 
		        msg.messageActivityDescription, 
		        msg.messageUserOwner,
		        msg.user.userId,
		        msg.user.userName,
		        msg.user.archiveName,
		        msg.replyTo.messageActivityId,
		        msg.messageSize
		    ORDER BY msg.messageActivityId DESC
		""")
		Page<LazyCommentsDTO> findLazyComments(@Param("activityId") Long activityId,
		                                       @Param("lastMsgId") Long lastMsgId,
		                                       Pageable pageable);


	
	@Query("""
			SELECT new com.calygam.back.dtos.LazyCommentsDTO (  
				msg.messageActivityId, 
				msg.activity.activityId, 
				msg.messageActivityDescription, 
				msg.messageUserOwner,
				msg.user.userId,
				msg.user.userName,
				msg.user.archiveName,
				msg.replyTo.messageActivityId,
				msg.messageSize,
				msg.messageResSize)  
			FROM MessageActivityEntity msg  
			WHERE msg.activity.activityId = :activityId 
				AND (:lastMsgId IS NULL OR msg.messageActivityId < :lastMsgId) 
				AND msg.replyTo.messageActivityId = :messageActivityId
				AND msg.messageActivityIsPrivate IS FALSE
				AND msg.messageActivityType = 1
				AND msg.replyTo.messageActivityId IS NOT NULL
			ORDER BY msg.messageActivityId DESC
			""")

	Page<LazyCommentsDTO> findLazyCommentsResponse(@Param("messageActivityId") Long messageActivityId,Long activityId, Long lastMsgId, Pageable pageable);
	
	List<MessageActivityEntity> findAllByActivity_activityIdAndReplyToIsNull(Long activityId);
}
