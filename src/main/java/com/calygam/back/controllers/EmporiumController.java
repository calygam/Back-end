package com.calygam.back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.calygam.back.dtos.EmporiumItemDTO;
import com.calygam.back.dtos.EmporiumStockDTO;
import com.calygam.back.enums.ItemCatalogInventoryEnum;
import com.calygam.back.services.EmporiumService;
import com.calygam.back.services.JwtUtilsId;
import com.calygam.back.sucesshandlers.ApiSucessHandler;

@RestController
@RequestMapping("/emporium")
public class EmporiumController {
	
	@Autowired
	private EmporiumService emporiumService;
	
	@Autowired
	private JwtUtilsId jwtUtilsId;
	
	@PostMapping("/add/item")
	public ApiSucessHandler<String> AddNewItemToEmporium(@RequestBody EmporiumItemDTO emporiumItemDto) {
		return emporiumService.AddNewItemToEmporium(emporiumItemDto);
	}
	
	@PostMapping("/purchase/obtain/item/{emporiumItemId}/type/{emporiumItemCatalogType}")
	public ApiSucessHandler<String> apprenticePurchaseOneItemController(@RequestHeader("Authorization") String token,@PathVariable Long emporiumItemId,@PathVariable ItemCatalogInventoryEnum emporiumItemCatalogType) {
		token = token.replace("Bearer ","");
		Long userId = jwtUtilsId.getUserIdFromToken(token);
		return emporiumService.apprenticePurchaseOneItem(userId, emporiumItemId, emporiumItemCatalogType);
	}
	@GetMapping("/search/stock")
	public EmporiumStockDTO searchItemsStockControlelr(@RequestHeader("Authorization") String token, @RequestParam(required = false) String orderBy) {
		token = token.replace("Bearer ","");
		Long userId = jwtUtilsId.getUserIdFromToken(token);
		return emporiumService.getStockInEmporium(userId,orderBy);
	}
}
