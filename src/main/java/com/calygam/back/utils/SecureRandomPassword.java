package com.calygam.back.utils;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class SecureRandomPassword {
	
	private String CHARACTERS_MONTAGE = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789";
	private SecureRandom RANDOM = new SecureRandom();
	public String GenerateSecureRandomPassword(int size) {
		List<Character> chars = new ArrayList<>();
		for(char c : CHARACTERS_MONTAGE.toCharArray()) {
			chars.add(c);
		}
		Collections.shuffle(chars,RANDOM);
		StringBuilder password = new StringBuilder(size);
		for(int i = 0; i< size; i++) { password.append(chars.get(i));}
		return password.toString().replaceAll("(.{4})(?!$)", "$1-");
	}
}
