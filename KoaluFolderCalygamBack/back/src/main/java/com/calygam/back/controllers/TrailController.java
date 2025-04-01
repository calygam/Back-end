package com.calygam.back.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("trail")
public class TrailController {
	
	@GetMapping("hello-world")
	public ResponseEntity<?> getMyMessage(){
		return ResponseEntity.ok("oiee eu sou um professor!");
	}
}
