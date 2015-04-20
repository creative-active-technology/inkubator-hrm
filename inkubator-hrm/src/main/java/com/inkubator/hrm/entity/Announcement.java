package com.inkubator.hrm.entity;
// Generated Apr 2, 2015 2:03:18 PM by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * Announcement generated by hbm2java
 */
@Entity
@Table(name = "announcement", catalog = "hrm"
)
public class Announcement implements java.io.Serializable {

    private Long id;
    private Integer version;
    private Company company;
    private String subject;
    private String announcementContent;
    private Integer timeModel;
    private Date periodeStartDate;
    private Date periodeEndDate;
    private Integer viewModel;
    private Boolean internetPublish;
    private String attachmentPath;
    private Integer status;
    private String approvalActivityNumber;
    private Date createdOn;
    private String createdBy;
    private String updatedBy;    
    private Date updatedOn;
    private Set<AnnouncementGoljab> announcementGoljabs = new HashSet<AnnouncementGoljab>(0);
    private Set<AnnouncementUnit> announcementUnits = new HashSet<AnnouncementUnit>(0);
    private Set<AnnouncementEmpType> announcementEmpTypes = new HashSet<AnnouncementEmpType>(0);

    public Announcement() {
    }

    public Announcement(Long id, Company company, String subject, Integer viewModel, boolean internetPublish) {
        this.id = id;
        this.company = company;
        this.subject = subject;
        this.viewModel = viewModel;
        this.internetPublish = internetPublish;
    }

    public Announcement(Long id, Company company, String subject, String announcementContent, Integer timeModel, Date periodeStartDate, Date periodeEndDate, Integer viewModel, Boolean internetPublish, String attachmentPath, Date createdOn, String createdBy, String updatedBy, Date updatedOn, Set<AnnouncementGoljab> announcementGoljabs, Set<AnnouncementUnit> announcementUnits, Set<AnnouncementEmpType> announcementEmpTypes) {
        this.id = id;
        this.company = company;
        this.subject = subject;
        this.announcementContent = announcementContent;
        this.timeModel = timeModel;
        this.periodeStartDate = periodeStartDate;
        this.periodeEndDate = periodeEndDate;
        this.viewModel = viewModel;
        this.internetPublish = internetPublish;
        this.attachmentPath = attachmentPath;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.updatedOn = updatedOn;
        this.announcementGoljabs = announcementGoljabs;
        this.announcementUnits = announcementUnits;
        this.announcementEmpTypes = announcementEmpTypes;
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
    @JoinColumn(name = "company_id", nullable = false)
    public Company getCompany() {
        return this.company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Column(name = "subject", nullable = false)
    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Column(name = "announcement_content", length = 65535, columnDefinition="Text", nullable = false)
    public String getAnnouncementContent() {
        return this.announcementContent;
    }

    public void setAnnouncementContent(String announcementContent) {
        this.announcementContent = announcementContent;
    }

    @Column(name = "time_model", nullable = false)
    public Integer getTimeModel() {
        return this.timeModel;
    }

    public void setTimeModel(Integer timeModel) {
        this.timeModel = timeModel;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "periode_start_date", length = 10)
    public Date getPeriodeStartDate() {
        return this.periodeStartDate;
    }

    public void setPeriodeStartDate(Date periodeStartDate) {
        this.periodeStartDate = periodeStartDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "periode_end_date", length = 10)
    public Date getPeriodeEndDate() {
        return this.periodeEndDate;
    }

    public void setPeriodeEndDate(Date periodeEndDate) {
        this.periodeEndDate = periodeEndDate;
    }

	@Column(name = "view_model", nullable = false)
    public Integer getViewModel() {
        return this.viewModel;
    }
	
	public void setViewModel(Integer viewModel) {
		this.viewModel = viewModel;
	}

    @Column(name = "internet_publish", nullable = false)
    public Boolean getInternetPublish() {
        return this.internetPublish;
    }

    public void setInternetPublish(Boolean internetPublish) {
        this.internetPublish = internetPublish;
    }
    
    @Column(name = "attachment_path", length = 200)
    public String getAttachmentPath() {
        return this.attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    @Column(name = "status", nullable = false)
    public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "approval_activity_number", length = 45, unique = true)
	public String getApprovalActivityNumber() {
		return approvalActivityNumber;
	}

	public void setApprovalActivityNumber(String approvalActivityNumber) {
		this.approvalActivityNumber = approvalActivityNumber;
	}

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on", length = 19)
    public Date getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Column(name = "created_by", length = 45)
    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Column(name = "updated_by", length = 45)
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_on", length = 19)
    public Date getUpdatedOn() {
        return this.updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "announcement")
    public Set<AnnouncementGoljab> getAnnouncementGoljabs() {
        return this.announcementGoljabs;
    }

    public void setAnnouncementGoljabs(Set<AnnouncementGoljab> announcementGoljabs) {
        this.announcementGoljabs = announcementGoljabs;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "announcement")
    public Set<AnnouncementUnit> getAnnouncementUnits() {
        return this.announcementUnits;
    }

    public void setAnnouncementUnits(Set<AnnouncementUnit> announcementUnits) {
        this.announcementUnits = announcementUnits;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "announcement")
    public Set<AnnouncementEmpType> getAnnouncementEmpTypes() {
        return this.announcementEmpTypes;
    }

    public void setAnnouncementEmpTypes(Set<AnnouncementEmpType> announcementEmpTypes) {
        this.announcementEmpTypes = announcementEmpTypes;
    }

}
