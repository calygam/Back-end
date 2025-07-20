package com.calygam.back.enums;



public enum UserRankEnum {
		BRONZEI(0,"BRONZE-I"),
		BRONZEII(500,"BRONZE-II"),
		BRONZEIII(1000,"BRONZE-III"),
		
		SILVERI(1500,"SILVER-I"),
		SILVERII(2000,"SILVER-II"),
		SILVERIII(2500,"SILVER-III"),
		
		
		GOLDI(4500,"GOLD-I"),
		GOLDII(5000,"GOLD-II"),
		GOLDIII(5500,"GOLD-III"),
		
		PLATINUMI(6000,"PLATINUM-I"),
		PLATINUMII(6500,"PLATINUM-II"),
		PLATINUMIII(7000,"PLATINUM-III"),
		
		DIAMOND(7500,"DIAMOND-I"),
		DIAMONDII(8000,"DIAMOND-II"),
		DIAMONDIII(8500,"DIAMOND-III"),
		
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

	public String getNameRank() {
		return nameRank;
	}
	public static Integer getRankForXpPoints(Long xp) {
		Integer bestRank = BRONZEI.getPoints();
        
        for (UserRankEnum rank : UserRankEnum.values()) {
            if ( rank.getPoints()> xp) {
                bestRank = rank.getPoints();
                break;
            }
       
        }
        
        return bestRank;
    }
	
	public static Integer getXpByRankName(String rankName) {
	    for (UserRankEnum rank : UserRankEnum.values()) {
	        if (rank.getNameRank().equalsIgnoreCase(rankName)) {
	            return rank.getPoints();
	        }
	    }
	    return null;
	}

	public static UserRankEnum getRankForXp(Long xp) {
        UserRankEnum bestRank = BRONZEI;
        
        for (UserRankEnum rank : UserRankEnum.values()) {
            if (xp <= rank.getPoints()) {
                break;
            }
            bestRank = rank;
        }
        
        return bestRank;
    }
	
	public static String getRankForXpToString(Long xp) {
        String bestRank = BRONZEI.getNameRank();
        
        for (UserRankEnum rank : UserRankEnum.values()) {
            if (xp < rank.getPoints()) {
                break;
            }
            bestRank = rank.getNameRank();
        }
        
        return bestRank;
    }
	
	 public static UserRankEnum fromCode(int code) {
	        UserRankEnum[] values = values();
	        if (code < 0 || code >= values.length) {
	            throw new IllegalArgumentException("Código inválido para UserRankEnum: " + code);
	        }
	        return values[code];
	    }
	
	
	
	
	
	
	
	
}
