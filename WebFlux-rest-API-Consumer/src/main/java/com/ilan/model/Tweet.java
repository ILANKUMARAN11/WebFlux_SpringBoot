package com.ilan.model;

import java.util.Date;


public class Tweet {
	
	private String id;
    
    private String sender;
    
    
    private String receiver;
    
    private String subject;
    
    
    private String text;

    
    private Date createdAt;

    public Tweet() {

    }

    public Tweet(String sender, String receiver, String subject, String text) {
    	this.sender=sender;
    	this.receiver=receiver;
    	this.subject=subject;
        this.text = text;
        this.createdAt=new Date();
    }
    
    
    

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
    
    
}
