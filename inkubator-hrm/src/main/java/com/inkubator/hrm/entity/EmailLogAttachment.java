package com.inkubator.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 *
 * @author rizkykojek
 */
@Entity
@Table(name = "email_log_attachment", catalog = "hrm")
public class EmailLogAttachment {

	private Long id;
	private Integer version;
	private EmailLog emailLog;
	private byte[] attachment;
	private String fileName;
	private String contentType;
	private Boolean isInlineResources;
	private String contentId;
	
	public EmailLogAttachment(){
    	
    }
    
    public EmailLogAttachment(Long id){
    	this.id = id;
    }
    
    @Id
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Version
    @Column(name = "version")
    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email_log_id", nullable = false)
	public EmailLog getEmailLog() {
		return emailLog;
	}

	public void setEmailLog(EmailLog emailLog) {
		this.emailLog = emailLog;
	}

	@Column(name = "attachment", columnDefinition = "MEDIUMBLOB")
	public byte[] getAttachment() {
		return attachment;
	}

	public void setAttachment(byte[] attachment) {
		this.attachment = attachment;
	}

	@Column(name = "file_name")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "content_type", nullable=false)
	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	@Column(name = "is_inline_resources", nullable=false)
	public Boolean getIsInlineResources() {
		return isInlineResources;
	}

	public void setIsInlineResources(Boolean isInlineResources) {
		this.isInlineResources = isInlineResources;
	}

	@Column(name = "content_id", nullable=false)
	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}
	
}
