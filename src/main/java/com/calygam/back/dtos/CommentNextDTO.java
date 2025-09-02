package com.calygam.back.dtos;

import java.util.List;

public class CommentNextDTO<T> {
	private List<T> content;
	private Boolean hasNext;
	public CommentNextDTO(List<T> content, Boolean hasNext) {
		super();
		this.content = content;
		this.hasNext = hasNext;
	}
	public List<T> getContent() {
		return content;
	}
	public void setContent(List<T> content) {
		this.content = content;
	}
	public Boolean getHasNext() {
		return hasNext;
	}
	public void setHasNext(Boolean hasNext) {
		this.hasNext = hasNext;
	}
	
	
}
