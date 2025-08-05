package com.calygam.back.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calygam.back.dtos.InventoryPetsDTO;
import com.calygam.back.enums.ItemCatalogInventoryEnum;
import com.calygam.back.repositories.ApprenticeInventoryRepository;
import com.calygam.back.services.ApprenticeInventoryService;
import com.calygam.back.services.JwtUtilsId;
import com.calygam.back.sucesshandlers.ApiSucessHandler;

@RestController
@RequestMapping(value="/inventory")
public class ApprenticeInventoryController {
	
	@Autowired
	private ApprenticeInventoryService apprenticeInventoryService; 
	
	@Autowired
	private JwtUtilsId jwtUtilsId;
	
	@GetMapping("/have/item/{catalog}")
	public Boolean checkItemIsInInventory(@RequestHeader("Authorization") String token,@PathVariable ItemCatalogInventoryEnum catalog) {
		token = token.replace("Bearer ","");
		Long userId = jwtUtilsId.getUserIdFromToken(token);
		return apprenticeInventoryService.checkItemIsInInventory(userId, catalog);
	}
	
	@PutMapping("/equip/pet/item/{itemId}/category/{categoryType}")
	public ApiSucessHandler<String> userIsEqquipedPetController(@RequestHeader("Authorization") String token,@PathVariable Long itemId,@PathVariable ItemCatalogInventoryEnum categoryType){
		token = token.replace("Bearer ","");
		Long userId = jwtUtilsId.getUserIdFromToken(token);
		return apprenticeInventoryService.userIsEqquipedPet(userId, categoryType, itemId);
	}
	@PutMapping("/equip/skin/item/{itemId}/category/{categoryType}")
	public ApiSucessHandler<String> userIsEqquipedSkin(@RequestHeader("Authorization") String token,@PathVariable Long itemId,@PathVariable ItemCatalogInventoryEnum categoryType){
		token = token.replace("Bearer ","");
		Long userId = jwtUtilsId.getUserIdFromToken(token);
		return apprenticeInventoryService.userIsEqquipedSkin(userId, categoryType, itemId);
	}
	
	@GetMapping("/get/pets/unequip")
	public List<InventoryPetsDTO> getInvPets(@RequestHeader("Authorization") String token){
		token = token.replace("Bearer ","");
		Long userId = jwtUtilsId.getUserIdFromToken(token);
		return apprenticeInventoryService.getInvPets(userId);
	}
	
	@GetMapping("/get/pet/equipped")
	public InventoryPetsDTO getInvPetEquipped(@RequestHeader("Authorization") String token){
		token = token.replace("Bearer ","");
		Long userId = jwtUtilsId.getUserIdFromToken(token);
		return apprenticeInventoryService.getPetEquipped(userId);
	}
	
}
