package com.calygam.back.dtos;

public class FlagsActivityDTO {
	private Long flagsQtd;
	private Long flagGenerateTimer;
	public FlagsActivityDTO() {
		super();
	}
	public FlagsActivityDTO(Long flagsQtd, Long flagGenerateTimer) {
		super();
		this.flagsQtd = flagsQtd;
		this.flagGenerateTimer = flagGenerateTimer;
	}
	public Long getFlagsQtd() {
		return flagsQtd;
	}
	public void setFlagsQtd(Long flagsQtd) {
		this.flagsQtd = flagsQtd;
	}
	public Long getFlagGenerateTimer() {
		return flagGenerateTimer;
	}
	public void setFlagGenerateTimer(Long flagGenerateTimer) {
		this.flagGenerateTimer = flagGenerateTimer;
	}
	
	
}
