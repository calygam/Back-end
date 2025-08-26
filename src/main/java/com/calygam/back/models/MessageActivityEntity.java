package com.calygam.back.models;

import java.util.ArrayList;
import java.util.List;

import com.calygam.back.enums.MessageActivityTypeEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_message_activity")
public class MessageActivityEntity {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="message_activity_id")
	private Long messageActivityId;
	@Column(name="message_activity_description",columnDefinition="TEXT")
	private String messageActivityDescription;
	@Column(name="message_activity_type")
	private MessageActivityTypeEnum messageActivityType;

	@Column(name="message_user_is_private")
	private Boolean messageActivityIsPrivate;
	@Column(name="message_user_owner")
	private Boolean messageUserOwner;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserEntity user;
	@ManyToOne
	@JoinColumn(name="user_received_feedback_id")
	private UserEntity recipient;
	
	@ManyToOne
	@JoinColumn(name="activity_id")
	private ActivityEntity activity;
	
	@ManyToOne
	@JoinColumn(name = "reply_to_id")
	private MessageActivityEntity replyTo;

	@OneToMany(mappedBy = "replyTo", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MessageActivityEntity> replies = new ArrayList<>();
	
	

	public MessageActivityEntity() {
		super();
	}



	


	public MessageActivityEntity(Long messageActivityId, String messageActivityDescription,
			MessageActivityTypeEnum messageActivityType, Boolean messageActivityIsPrivate, Boolean messageUserOwner,
			UserEntity user, UserEntity recipient, ActivityEntity activity, MessageActivityEntity replyTo,
			List<MessageActivityEntity> replies) {
		super();
		this.messageActivityId = messageActivityId;
		this.messageActivityDescription = messageActivityDescription;
		this.messageActivityType = messageActivityType;
		this.messageActivityIsPrivate = messageActivityIsPrivate;
		this.messageUserOwner = messageUserOwner;
		this.user = user;
		this.recipient = recipient;
		this.activity = activity;
		this.replyTo = replyTo;
		this.replies = replies;
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


	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public ActivityEntity getActivity() {
		return activity;
	}

	public void setActivity(ActivityEntity activity) {
		this.activity = activity;
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



	



	public UserEntity getRecipient() {
		return recipient;
	}



	public void setRecipient(UserEntity recipient) {
		this.recipient = recipient;
	}






	public MessageActivityEntity getReplyTo() {
		return replyTo;
	}






	public void setReplyTo(MessageActivityEntity replyTo) {
		this.replyTo = replyTo;
	}






	public List<MessageActivityEntity> getReplies() {
		return replies;
	}






	public void setReplies(List<MessageActivityEntity> replies) {
		this.replies = replies;
	}
	
	
	
	
	
}