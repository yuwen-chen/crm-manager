package com.crm.manager.send.mail.domain;

import java.util.List;

public class EmailBean {

	/**
	 * 邮件内容
	 */
	private String message = null;

	/**
	 * email地址
	 */
	private String email = null;

	/**
	 * 抄送
	 */
	private String toCc = null;

	/**
	 * 暗送
	 */
	private String toBcc = null;

	/**
	 * 主题
	 */
	private String subject = null;
	
	/**
	 * 邮件发送方式
	 */
	private String sendType = null;

	/**
	 * 附件列表
	 */
	private List<String> attachmentList = null;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToCc() {
		return toCc;
	}

	public void setToCc(String toCc) {
		this.toCc = toCc;
	}

	public String getToBcc() {
		return toBcc;
	}

	public void setToBcc(String toBcc) {
		this.toBcc = toBcc;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSendType() {
		return sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

	public List<String> getAttachmentList() {
		return attachmentList;
	}

	public void setAttachmentList(List<String> attachmentList) {
		this.attachmentList = attachmentList;
	}

}
