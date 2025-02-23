package com.calygam.back.enums;

public enum UserRankEnum {
		BRONZEI(0,"BRONZE-I"),
		BRONZEII(500,"BRONZE-II"),
		BRONZEIII(1000,"BRONZE-III"),
		
		SILVERI(1500,"SILVER-I"),
		SILVERII(2000,"SILVER-II"),
		SILVERIII(2500,"SILVER-III"),
		IRONI(3000,"IRON-I"),
		IRONII(3500,"IRON-II"),
		IRONIII(4000,"IRON-III"),	
		
		GOLDI(4500,"GOLD-I"),
		GOLDII(5000,"GOLD-II"),
		GOLDIII(5500,"GOLD-III"),
		
		PLATINUMI(6000,"PLATINUM-I"),
		PLATINUMII(6500,"PLATINUM-II"),
		PLATINUMIII(7000,"PLATINUM-III"),
		
		DIAMOND(7500,"DIAMOND-I"),
		DIAMONDII(8000,"DIAMOND-II"),
		DIAMOUNDIII(8500,"DIAMOND-III"),
		
		ASCENDENT(10000,"ASCENDENT-I"),
		ASCENDENTII(12000,"ASCENDENT-II"),
		ASCENDENTIII(15000,"ASCENDENT-III");
	
	private Integer points;
	private String nameRank;
	private UserRankEnum(Integer points, String nameRank) {
		this.points = points;
		this.nameRank = nameRank;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	public String getNameRank() {
		return nameRank;
	}
	public void setNameRank(String nameRank) {
		this.nameRank = nameRank;
	}
	
	
	
	
	
	
	
}
