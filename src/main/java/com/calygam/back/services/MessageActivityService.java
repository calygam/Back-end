package com.calygam.back.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calygam.back.dtos.SendMessageDTO;
import com.calygam.back.exceptions.ExcededMaxDelimiter;
import com.calygam.back.exceptions.SoftNotFoundException;
import com.calygam.back.models.ActivityEntity;
import com.calygam.back.models.ActivityProgressEntity;
import com.calygam.back.models.MessageActivityEntity;

import com.calygam.back.models.UserEntity;
import com.calygam.back.repositories.ActivityRepository;

import com.calygam.back.repositories.MessageActivityRepository;
import com.calygam.back.repositories.ProgressRepository;
import com.calygam.back.repositories.UsersRepository;
import com.calygam.back.sucesshandlers.ApiSucessHandler;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MessageActivityService {
	
	@Autowired
	private ActivityRepository activityRepository;
	
	@Autowired
	private MessageActivityRepository messageActivityRepository;
	
	@Autowired
	private ProgressRepository progressRepository;
	
	@Autowired
	private UsersRepository usersRepository;
	
	//caio<- e pode ter também alguem que mande mensagem para outra pessoa como resposta

	
	public ApiSucessHandler<String> sendMessageOrFeedBack(SendMessageDTO messageDTO,Long userId,Long activityId,Long messageActivityId){
		ActivityEntity activityEntity = activityRepository.findById(activityId)
				.orElseThrow(()-> new SoftNotFoundException("Atividade não encontrada!")); 
		
	     ActivityProgressEntity progressEntityCompleted = progressRepository.findByUserTrailAndActivity(userId, activityEntity.getTrail().getTrailId(), activityId)
	             .orElseThrow(() -> new EntityNotFoundException("Progresso não identificado"));
		
		UserEntity userRequestMassageEntity = usersRepository.findById(userId)
				.orElseThrow(()-> new SoftNotFoundException("Usuário não encontrado!"));
		MessageActivityEntity messageActivityEntity = new MessageActivityEntity();
		
		
		if(messageDTO.getMessageUserMentionedEmail() !=null && activityEntity.getTrail().getUser().getUserId().equals(userRequestMassageEntity.getUserId()) && messageActivityId==null) {
			UserEntity userByEmail = usersRepository.findEntityByEmail(messageDTO.getMessageUserMentionedEmail()).orElseThrow(()-> new SoftNotFoundException("usuario que recebe não encontrado!"));
			messageActivityEntity.setRecipient(userByEmail);
		}
		
		if(activityEntity.getTrail().getUser().getUserId().equals(userRequestMassageEntity.getUserId())){
			messageActivityEntity.setMessageUserOwner(true);
		}else {
			messageActivityEntity.setMessageUserOwner(false);
		}
		
		if(messageActivityId!=null) {
			MessageActivityEntity targetReplyToMessage = messageActivityRepository.findById(messageActivityId).orElseThrow(()-> new SoftNotFoundException("mensagem não encontrada para responder!"));
			
			List<MessageActivityEntity> messageLimmit =targetReplyToMessage.getReplies().stream().filter(targetMessage -> !targetMessage.getMessageUserOwner()).collect(Collectors.toList());
			if(!activityEntity.getTrail().getUser().getUserId().equals(userRequestMassageEntity.getUserId())){
				System.out.println("array com tamanho = "+ messageLimmit.size());
				if(messageLimmit.size()>=44) {
					throw new ExcededMaxDelimiter("Este usuário já recebeu muitas respostas nessa atividade!");
				}
			}
			

			messageActivityEntity.setReplyTo(targetReplyToMessage);
		}
		
		messageActivityEntity.setMessageActivityDescription(messageDTO.getMessageActivityDescription());
		messageActivityEntity.setUser(userRequestMassageEntity);
		messageActivityEntity.setMessageActivityType(messageDTO.getMessageActivityType());
		messageActivityEntity.setActivity(activityEntity);
		messageActivityEntity.setMessageActivityIsPrivate(messageDTO.getMessageActivityIsPrivate());
	
		messageActivityRepository.save(messageActivityEntity);
		
		
		return new ApiSucessHandler<String>(true,"mensagem enviada com sucesso!", null);
		
		
	}
	
	

	
}
