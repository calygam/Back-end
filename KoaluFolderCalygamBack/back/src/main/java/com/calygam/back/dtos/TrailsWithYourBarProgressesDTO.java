package com.calygam.back.dtos;

import java.util.List;

public class TrailsWithYourBarProgressesDTO {
	List<TrailDTO> trilhas;
	List<ProgressBarTrailDTO> barProgresses;
	public TrailsWithYourBarProgressesDTO() {
		super();
	}
	public TrailsWithYourBarProgressesDTO(List<TrailDTO> trilhas, List<ProgressBarTrailDTO> barProgresses) {
		super();
		this.trilhas = trilhas;
		this.barProgresses = barProgresses;
	}
	public List<TrailDTO> getTrilhas() {
		return trilhas;
	}
	public void setTrilhas(List<TrailDTO> trilhas) {
		this.trilhas = trilhas;
	}
	public List<ProgressBarTrailDTO> getBarProgresses() {
		return barProgresses;
	}
	public void setBarProgresses(List<ProgressBarTrailDTO> barProgresses) {
		this.barProgresses = barProgresses;
	}
	
	
}
