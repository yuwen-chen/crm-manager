package com.crm.manager.send.mail.domain;

public class MailTemplate {
	
	private Long id;	
	private String templateId;
    private String description;
    private String templateSubject;
    private String templateContent;
    private String status;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTemplateSubject() {
		return templateSubject;
	}
	public void setTemplateSubject(String templateSubject) {
		this.templateSubject = templateSubject;
	}
	public String getTemplateContent() {
		return templateContent;
	}
	public void setTemplateContent(String templateContent) {
		this.templateContent = templateContent;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "MailTemplate [id=" + id + ", templateId=" + templateId + ", description=" + description
				+ ", templateSubject=" + templateSubject + ", templateContent=" + templateContent + ", status=" + status
				+ "]";
	}
    
}
