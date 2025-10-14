package com.calygam.back.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.calygam.back.dtos.SecurePasswordDTO;
import com.calygam.back.dtos.TrailDTO;
import com.calygam.back.services.JwtUtilsId;
import com.calygam.back.services.TrailService;

@RestController
@RequestMapping("trail")
public class TrailController {
	
	@Autowired
	TrailService trailService;
	
	@Autowired
	JwtUtilsId jwtUtilsId;
	
	
	
	@PostMapping("/create")
	public TrailDTO createNewTrail(@RequestHeader("Authorization") String token,@ModelAttribute TrailDTO trailDTO) throws IOException{
		token = token.replace("Bearer ","");
		Long userId = jwtUtilsId.getUserIdFromToken(token);
		return trailService.createNewTrail(userId, trailDTO);
	}
	@GetMapping("/read/all-trails")
	public List<TrailDTO> ReadAllTrailsOfTeachers(@RequestHeader("Authorization") String token,@RequestParam(value="haveProgress",required=false) String haveProgress) {
		token = token.replace("Bearer ","");
		Long userId = jwtUtilsId.getUserIdFromToken(token);
		return trailService.ReadAllTrailsOfTeachers(userId,haveProgress);
	}
	@GetMapping("/read/by/teacher")
	public List<TrailDTO> ReadAllTrailsOfOneTeacher(@RequestHeader("Authorization") String token) {
		token = token.replace("Bearer ","");
		Long userId = jwtUtilsId.getUserIdFromToken(token);
		
		return trailService.ReadAllTrailsOfOneTeacher(userId);
	}
	@GetMapping("/read/{trailId}")
	public TrailDTO ReadAllTrails(@PathVariable("trailId") Long trailId) {

		return trailService.ReadTrailById(trailId);
	}
	

	@PutMapping("/update/{trailId}")
	public TrailDTO updateOneTrail(@RequestHeader("Authorization") String token,@PathVariable("trailId") Long trailId,@ModelAttribute TrailDTO trailDTO)throws IOException {
		token = token.replace("Bearer ","");
		System.out.println("Entreeeeeeeeeeei");
		Long userId = jwtUtilsId.getUserIdFromToken(token);
		return trailService.updateInfoTrailByExistsId(userId, trailId,trailDTO);
	}
	
	@GetMapping("/gen/password")
	public SecurePasswordDTO generateSecureRandomPasswordCtrl() {
		return trailService.generateSecurePasswordService();
	}
}
