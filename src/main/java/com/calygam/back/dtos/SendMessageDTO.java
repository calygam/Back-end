package com.calygam.back.dtos;

import com.calygam.back.enums.MessageActivityTypeEnum;

public class SendMessageDTO {
	private Long messageActivityId;
	private String messageActivityDescription;
	private MessageActivityTypeEnum messageActivityType;
	private String messageUserMentionedEmail;
	private Boolean messageActivityIsPrivate;
	private Boolean messageUserOwner;
	public SendMessageDTO(Long messageActivityId, String messageActivityDescription,
			MessageActivityTypeEnum messageActivityType, String messageUserMentionedEmail,
			Boolean messageActivityIsPrivate, Boolean messageUserOwner) {
		super();
		this.messageActivityId = messageActivityId;
		this.messageActivityDescription = messageActivityDescription;
		this.messageActivityType = messageActivityType;
		this.messageUserMentionedEmail = messageUserMentionedEmail;
		this.messageActivityIsPrivate = messageActivityIsPrivate;
		this.messageUserOwner = messageUserOwner;
	}
	public SendMessageDTO() {
		super();
	}
	public Long getMessageActivityId() {
		return messageActivityId;
	}
	public void setMessageActivityId(Long messageActivityId) {
		this.messageActivityId = messageActivityId;
	}
	public String getMessageActivityDescription() {
		return messageActivityDescription;
	}
	public void setMessageActivityDescription(String messageActivityDescription) {
		this.messageActivityDescription = messageActivityDescription;
	}
	public MessageActivityTypeEnum getMessageActivityType() {
		return messageActivityType;
	}
	public void setMessageActivityType(MessageActivityTypeEnum messageActivityType) {
		this.messageActivityType = messageActivityType;
	}
	public String getMessageUserMentionedEmail() {
		return messageUserMentionedEmail;
	}
	public void setMessageUserMentionedEmail(String messageUserMentionedEmail) {
		this.messageUserMentionedEmail = messageUserMentionedEmail;
	}
	public Boolean getMessageActivityIsPrivate() {
		return messageActivityIsPrivate;
	}
	public void setMessageActivityIsPrivate(Boolean messageActivityIsPrivate) {
		this.messageActivityIsPrivate = messageActivityIsPrivate;
	}
	public Boolean getMessageUserOwner() {
		return messageUserOwner;
	}
	public void setMessageUserOwner(Boolean messageUserOwner) {
		this.messageUserOwner = messageUserOwner;
	}
	
	
	
}
