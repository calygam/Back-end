package com.calygam.back.utils;

import org.springframework.stereotype.Component;

@Component
public class EnumUtils {
	 public  String itemCatalogInventoryEnumNameFromCode(Byte code) {
	        if (code == null) return null;
	        switch(code) {
	            case 0: return "PET";
	            case 1: return "SKIN";
	            case 2: return "THEME";
	            default: throw new IllegalArgumentException("Código inválido: " + code);
	        }
	    }
}
