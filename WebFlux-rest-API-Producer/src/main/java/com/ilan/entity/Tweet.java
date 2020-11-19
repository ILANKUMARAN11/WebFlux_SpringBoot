package com.ilan.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;


@Document(collection = "tweets")
public class Tweet {
    @Id
    private String id;

    @Field
    @Size(max = 40)
    private String sender;
    
    @Size(max = 40)
    private String receiver;
    
    @Size(max = 140)
    private String subject;
    
    @NotBlank
    @Size(max = 300)
    private String text;

    @NotNull
    private Date createdAt = new Date();

    public Tweet() {

    }

    public Tweet(String sender, String receiver, String subject, String text) {
    	this.sender=sender;
    	this.receiver=receiver;
    	this.subject=subject;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
    
    
}
