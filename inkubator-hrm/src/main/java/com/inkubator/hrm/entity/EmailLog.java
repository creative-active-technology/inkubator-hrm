package com.inkubator.hrm.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 *
 * @author rizkykojek
 */
@Entity
@Table(name = "email_log", catalog = "hrm")
public class EmailLog implements Serializable {

	private Long id;
	private Integer version;
	private String mailTo;
	private List<String> mailCc;
	private List<String> mailBcc;
	private String mailSubject;
	private String mailContent;
	private Boolean isContentHtml;
	private Boolean sentStatus;
	private Date sentOn;
	private String referenceClass;
	private Long referenceId;
	private String createdBy;
    private Date createdOn;
    private Set<EmailLogAttachment> emailLogAttachments = new HashSet<EmailLogAttachment>(0);
    
    
    public EmailLog(){
    	
    }
    
    public EmailLog(Long id){
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
    
    @Column(name = "mail_to", nullable = false)
    public String getMailTo() {
		return mailTo;
	}

	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}
	
	@ElementCollection
	@CollectionTable(name = "email_log_cc", joinColumns=@JoinColumn(name = "email_log_id"))
	@Column(name = "mail_cc")
	public List<String> getMailCc() {
		return mailCc;
	}

	public void setMailCc(List<String> mailCc) {
		this.mailCc = mailCc;
	}

	@ElementCollection
	@CollectionTable(name = "email_log_bcc", joinColumns=@JoinColumn(name = "email_log_id"))
	@Column(name = "mail_bcc")
	public List<String> getMailBcc() {
		return mailBcc;
	}

	public void setMailBcc(List<String> mailBcc) {
		this.mailBcc = mailBcc;
	}
	
	@Column(name = "mail_subject", nullable = false)
	public String getMailSubject() {
		return mailSubject;
	}

	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	@Column(name = "mail_content", length = 65535, columnDefinition = "Text", nullable = false)
	public String getMailContent() {
		return mailContent;
	}

	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}

	@Column(name = "sent_status", nullable = false)
	public Boolean getSentStatus() {
		return sentStatus;
	}

	public void setSentStatus(Boolean sentStatus) {
		this.sentStatus = sentStatus;
	}

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "sent_on", length = 19)
	public Date getSentOn() {
		return sentOn;
	}

	public void setSentOn(Date sentOn) {
		this.sentOn = sentOn;
	}

	@Column(name = "reference_class")
	public String getReferenceClass() {
		return referenceClass;
	}

	public void setReferenceClass(String referenceClass) {
		this.referenceClass = referenceClass;
	}

	@Column(name = "reference_id")
	public Long getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(Long referenceId) {
		this.referenceId = referenceId;
	}
	
	@Column(name = "is_content_html", nullable=false)
	public Boolean getIsContentHtml() {
		return isContentHtml;
	}

	public void setIsContentHtml(Boolean isContentHtml) {
		this.isContentHtml = isContentHtml;
	}

	@Column(name = "created_by", length = 45)
    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on", length = 19)
    public Date getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "emailLog", cascade=CascadeType.ALL, orphanRemoval=true)
	public Set<EmailLogAttachment> getEmailLogAttachments() {
		return emailLogAttachments;
	}

	public void setEmailLogAttachments(Set<EmailLogAttachment> emailLogAttachments) {
		this.emailLogAttachments = emailLogAttachments;
	}
    
}
