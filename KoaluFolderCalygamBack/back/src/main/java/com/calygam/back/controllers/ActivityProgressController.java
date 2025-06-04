package com.calygam.back.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.calygam.back.dtos.ActivityProgressResponseDTO;
import com.calygam.back.services.JwtUtilsId;
import com.calygam.back.services.ProgressActivityService;

@RestController
@RequestMapping("/progress")
public class ActivityProgressController {
	@Autowired
	private JwtUtilsId jwtUtilsId;
	@Autowired
	private ProgressActivityService progressActivityService;
	@PostMapping("/join/{trailId}")
	public ResponseEntity<List<ActivityProgressResponseDTO>> assignStudent(@RequestHeader("Authorization") String token,@PathVariable Long trailId,@RequestParam(name="trailPassword",required=false) String trailPassword){
		token = token.replace("Bearer ","");
		System.out.println("Entrou no método assignStudent");
		
		Long userId = jwtUtilsId.getUserIdFromToken(token);
		List<ActivityProgressResponseDTO> progress = progressActivityService.progressAssignStudent(trailPassword,userId, trailId);
		return ResponseEntity.status(HttpStatus.CREATED).body(progress);
	}
}
