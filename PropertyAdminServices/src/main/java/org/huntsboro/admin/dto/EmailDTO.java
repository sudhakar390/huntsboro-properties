package org.huntsboro.admin.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;


public class EmailDTO implements Serializable {

	private String mailTo;
	private String msgText;
	private String mailFrom;
	private String subject;
	
	public EmailDTO(){}
	
	public EmailDTO(@JsonProperty("mailTo") String mailTo, @JsonProperty("msgText") String msgText , @JsonProperty("mailFrom") String mailFrom, @JsonProperty("subject") String subject) {
		super();
		this.mailTo = mailTo;
		this.msgText = msgText;
		this.mailFrom = mailFrom;
		this.subject = subject;
	}

	public String getMailTo() {
		return mailTo;
	}

	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}

	public String getMsgText() {
		return msgText;
	}

	public void setMsgText(String msgText) {
		this.msgText = msgText;
	}

	public String getMailFrom() {
		return mailFrom;
	}

	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	
	
	
}
