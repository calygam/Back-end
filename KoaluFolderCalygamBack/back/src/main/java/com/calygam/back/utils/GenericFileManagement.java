package com.calygam.back.utils;


public interface GenericFileManagement {
	
	void setArchiveName(String archiveName);
	void setOriginalName(String originalName);
	void setArchivePath(String archivePath);
	void setArchiveType(String archiveType);
	
	String getArchiveName();
    String getOriginalName();
    String getArchivePath();
    String getArchiveType();
	
}
