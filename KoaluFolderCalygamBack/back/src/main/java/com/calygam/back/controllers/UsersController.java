package com.calygam.back.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calygam.back.dtos.DataUtilUserDTO;
import com.calygam.back.projections.AdminAnalisisProjection;
import com.calygam.back.services.UsersServices;

@RestController
@RequestMapping("users")
public class UsersController {
	
	@Autowired
	private UsersServices usersServices;
	
	@GetMapping("/readOne")
	public ResponseEntity<?> ReadInfoUserById(@RequestHeader("AUTHORIZATION") String token){
		
		token = token.replace("Bearer ", "");
		Optional<DataUtilUserDTO> user = usersServices.ReadInfoUserByIdService(token);
		return ResponseEntity.ok(user);
	}
	@GetMapping("/dash/count/admin")
	public ResponseEntity<AdminAnalisisProjection> getTotalAnalisisAdminController(){
		return ResponseEntity.ok(usersServices.getTotalAnalisisAdminService());
	}
	
	
	@PutMapping("/teacher/{teacherEmail}")
	public ResponseEntity<String> assignTeacherToProjectController(@PathVariable String teacherEmail){
		return usersServices.assignTeacherToProject(teacherEmail);
		
	}
}
