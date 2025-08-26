package com.calygam.back.dtos;

public class LazyCommentsDTO {
	private Long messageActivityId; 
	private Long activityId; 
	private String messageActivityDescription; 
	private Boolean messageUserOwner;
	private Long userId;
	private String userName;
	private String userImageUrl;
	private String archiveName;
	private Long idToReply;
	public LazyCommentsDTO(Long messageActivityId, Long activityId, String messageActivityDescription,
			Boolean messageUserOwner, Long userId, String userName, String archiveName,Long idToReply) {
		super();
		this.messageActivityId = messageActivityId;
		this.activityId = activityId;
		this.messageActivityDescription = messageActivityDescription;
		this.messageUserOwner = messageUserOwner;
		this.userId = userId;
		this.userName = userName;
		this.archiveName = archiveName;
		this.idToReply = idToReply;
	} 
	public Long getMessageActivityId() {
		return messageActivityId;
	}
	public void setMessageActivityId(Long messageActivityId) {
		this.messageActivityId = messageActivityId;
	}
	public Long getActivityId() {
		return activityId;
	}
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
	public String getMessageActivityDescription() {
		return messageActivityDescription;
	}
	public void setMessageActivityDescription(String messageActivityDescription) {
		this.messageActivityDescription = messageActivityDescription;
	}
	public Boolean getMessageUserOwner() {
		return messageUserOwner;
	}
	public void setMessageUserOwner(Boolean messageUserOwner) {
		this.messageUserOwner = messageUserOwner;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserImageUrl() {
		return userImageUrl;
	}
	//
	public void setUserImageUrl(String userImageUrl) {
		this.userImageUrl = userImageUrl;
	}
	public String getArchiveName() {
		return archiveName;
	}
	public void setArchiveName(String archiveName) {
		this.archiveName = archiveName;
	}
	public Long getIdToReply() {
		return idToReply;
	}
	public void setIdToReply(Long idToReply) {
		this.idToReply = idToReply;
	}
}
