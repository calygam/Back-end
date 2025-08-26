package com.calygam.back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.calygam.back.dtos.LazyCommentsDTO;
import com.calygam.back.dtos.SendMessageDTO;
import com.calygam.back.services.JwtUtilsId;
import com.calygam.back.services.MessageActivityService;
import com.calygam.back.sucesshandlers.ApiSucessHandler;

@RestController
@RequestMapping("/message/activity")
public class MessageActivityController {
	
	@Autowired
	private MessageActivityService messageActivityService;
	
	@Autowired
	private JwtUtilsId jwtUtilsId;
	
	@PostMapping("/{activityId}/send")
	public ApiSucessHandler<String> SendMessageToActivityController(@RequestBody SendMessageDTO messageDTO, @RequestHeader("Authorization") String token, @PathVariable Long activityId,@RequestParam(required=false) Long messageActivityId){
		token = token.replace("Bearer ","");
		Long userId = jwtUtilsId.getUserIdFromToken(token);
		return messageActivityService.sendMessageOrFeedBack(messageDTO, userId, activityId,messageActivityId);
	}
	//CAIO<- TRAZENDO A PAGINA QUE VAMOS AGRUPAR DEPOIS
		@GetMapping("/list-all-basics")
		public Page<LazyCommentsDTO> getLazyCommentsCtrl(
				@RequestParam(required=false) Long activityId,
				@RequestParam(required=false) Long lastMsgId,
				Pageable pageable){
			
			return messageActivityService.getLazyCommentsByActivity(activityId, lastMsgId, pageable);
		}
	
}
