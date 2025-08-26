package com.calygam.back.enums;

public enum StatusOfLife {
	
	ENABLE("ATIVO"),
	DESABLED("DESATIVO"),
	COMPLETE("COMPLETA"),
	WRONG("ERRADA"),
	CORRECT("CORRETA"),
	BUILDING("CONSTRUINDO");
	
	
	private String statusName;
	StatusOfLife(String statusName) {
		this.statusName = statusName;
	}

   public int getOrdinalValue() {
       return this.ordinal();
   }

   public String getEnumName() {
       return this.name();
   }

   public String getStatusNameName() {
       return this.statusName;
   }
}
