package com.calygam.back.enums;





public enum UserRoleEnum {                      
	ADMIN("admin"),
	COORDENADOR("coordenador"),
	INSTRUTOR("instrutor"),
	ALUNO("aluno");
	
	private String role;
	
	UserRoleEnum (String role){
		this.role = role;
	}
	
	
	public String getRole() {
		return role;
	}



	
	
	
}
