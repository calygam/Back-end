package com.calygam.back.controllers;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.calygam.back.dtos.DataUtilUserDTO;
import com.calygam.back.dtos.EditCredentialsDTO;
import com.calygam.back.projections.AdminAnalisisProjection;
import com.calygam.back.services.JwtUtilsId;
import com.calygam.back.services.UsersServices;
import com.calygam.back.sucesshandlers.ApiSucessHandler;

@RestController
@RequestMapping("users")
public class UsersController {
	
	@Autowired
	private UsersServices usersServices;
	
	@Autowired
	private JwtUtilsId jwtUtilsId;
	
	@GetMapping("/readOne")
	public ResponseEntity<?> ReadInfoUserById(@RequestHeader("AUTHORIZATION") String token){
		
		token = token.replace("Bearer ", "");
		Optional<DataUtilUserDTO> user = usersServices.ReadInfoUserByIdService(token);
		return ResponseEntity.ok(user);
	}
	@GetMapping("/readAllUsers/teacher")
	public Page<DataUtilUserDTO> ReadInfoTeachersByRole(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam int size,
			@RequestParam(defaultValue = "userId,desc") String sort){
/*		Sort sortObj = Sort.by(sort[0].split(","));
		Pageable pageable = PageRequest.of(page, size,sortObj);*/
	    String[] sortParts = sort.split(",");
	    String sortField = sortParts[0];
	    Sort.Direction direction = (sortParts.length > 1 && sortParts[1].equalsIgnoreCase("desc"))
	        ? Sort.Direction.DESC
	        : Sort.Direction.ASC;

	    Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortField));
	    return usersServices.ReadInfoUsersByRole(pageable);
	}
	
	@GetMapping("/dash/count/admin")
	public ResponseEntity<AdminAnalisisProjection> getTotalAnalisisAdminController(){
		return ResponseEntity.ok(usersServices.getTotalAnalisisAdminService());
	}
	
	
	@PutMapping("/teacher/{teacherEmail}")
	public ResponseEntity<String> assignTeacherToProjectController(@PathVariable String teacherEmail){
		return usersServices.assignTeacherToProject(teacherEmail);
		
	}
	@PutMapping("/teacher/remove/{teacherEmail}")
	public ResponseEntity<String> removeTeacherOfProjectController(@PathVariable String teacherEmail){
		return usersServices.removeTeacherOfProject(teacherEmail);
		
	}

		
	
	@PutMapping("/editOne")
	public ApiSucessHandler<String> EditCredentialsUser(@RequestHeader("AUTHORIZATION") String token,@ModelAttribute EditCredentialsDTO editCredentialsDTO) throws IOException {
		token = token.replace("Bearer ", "");
		Long userId = jwtUtilsId.getUserIdFromToken(token);
		return usersServices.EditCredentialsUser(userId,editCredentialsDTO);
	}
}
