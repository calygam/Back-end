package com.calygam.back.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calygam.back.dtos.CreatePetDTO;
import com.calygam.back.dtos.NewOutfitDTO;
import com.calygam.back.dtos.PetDTO;
import com.calygam.back.services.PetService;

@RestController
@RequestMapping("/pet")
public class PetController {
	
	@Autowired
	private PetService petService;
	
	@PostMapping("/admin/creating")
	public String createANewPet(@ModelAttribute CreatePetDTO petDTO) throws IOException {
		return petService.createANewPet(petDTO);
	}
	
	@PutMapping("/admin/creating/new/skin/{petId}")
	public String PetNewSkinUnlocked(@PathVariable Long petId,@ModelAttribute NewOutfitDTO newOutfitDTO) throws IOException {
		return petService.PetNewSkinUnlocked(petId,newOutfitDTO);
	}
	
	@GetMapping("/read-all")
	public List<PetDTO> searchAllPetsController(){
		return petService.searchAllPetWithSkins();
	}
}
