package com.calygam.back.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calygam.back.dtos.ProgressSubmitActivityDTO;
import com.calygam.back.dtos.SubmittedActivityUserDTO;
import com.calygam.back.services.JwtUtilsId;
import com.calygam.back.services.SubmissionService;

@RestController
@RequestMapping("/submission")
public class SubmissionController {
	
	@Autowired
	private SubmissionService submissionService;
	
	@Autowired
	private JwtUtilsId jwtUtilsId;
	
	@GetMapping("/download/{progressId}")
	public List<ProgressSubmitActivityDTO> ListenerOfDowloadableArchivesSubmitedController(@PathVariable Long progressId){
		return submissionService.ListenerOfDowloadableArchivesSubmitedService(progressId);
	}
	
	@DeleteMapping("/delete/progress/{progressId}/submission/{submissionId}")
	public Boolean deleteSubmitedArchive(@PathVariable Long progressId,@PathVariable Long submissionId) {
		return submissionService.deleteSubmission(submissionId,progressId);
	}
	@GetMapping("/delivered/{progressId}")
	public List<SubmittedActivityUserDTO>  deliveredToDTO(@RequestHeader("Authorization") String token,@PathVariable Long progressId){
		token = token.replace("Bearer ","");
		Long userId = jwtUtilsId.getUserIdFromToken(token);
		return submissionService.ListenerSubmittedArchivesUser(userId,progressId);
	}
}
