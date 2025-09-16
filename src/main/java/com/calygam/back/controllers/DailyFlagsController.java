package com.calygam.back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calygam.back.dtos.FlagsActivityDTO;
import com.calygam.back.services.DailyFlagsService;
import com.calygam.back.services.JwtUtilsId;

@RestController
@RequestMapping(value="/flags")
public class DailyFlagsController {
	@Autowired
	private DailyFlagsService dailyFlagsService;
	@Autowired
	private JwtUtilsId jwtUtilsId;
	@GetMapping("/get-timer")
	public FlagsActivityDTO getFlagsAndTimer(@RequestHeader("Authorization") String token){
		token = token.replace("Bearer ", "");
		Long userId = jwtUtilsId.getUserIdFromToken(token);
		return dailyFlagsService.getFlagsAndTimer(userId);
	}
}
