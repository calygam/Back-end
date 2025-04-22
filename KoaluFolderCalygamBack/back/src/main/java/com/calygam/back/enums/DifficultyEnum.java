package com.calygam.back.enums;

public enum DifficultyEnum {
	
	EASY("FÀCIL"),
	MEDIUM("MÈDIO"),
	HARD("DIFÍCIL"),
	BOSS("CHEFE");
	
	private String difficultyName;
	 DifficultyEnum(String difficultyName) {
		this.difficultyName = difficultyName;
	}

    public int getOrdinalValue() {
        return this.ordinal();
    }

    public String getEnumName() {
        return this.name();
    }

    public String getDifficultyName() {
        return this.difficultyName;
    }
	
	
}
