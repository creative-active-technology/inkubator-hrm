package com.inkubator.hrm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name = "rmbs_cancelation", catalog = "hrm")
public class RmbsCancelation implements java.io.Serializable {

    private Long id;
    private Integer version;
    private String code;
    private Date cancelledDate;
    private String reason;
    private RmbsApplication rmbsApplication;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;

    public RmbsCancelation() {    	
    }

    public RmbsCancelation(long id) {
        this.id = id;
    }

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "rmbs_cancelation_seq_gen")
    @SequenceGenerator(name = "rmbs_cancelation_seq_gen", sequenceName = "RMBS_CANCELATION_SEQ")
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
    
    @Column(name = "code", unique = true, nullable = false, length = 45)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}	
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "cancelled_date", length = 19)
    public Date getCancelledDate() {
		return cancelledDate;
	}

	public void setCancelledDate(Date cancelledDate) {
		this.cancelledDate = cancelledDate;
	}	
	
	@Column(name = "description", length = 65535, columnDefinition = "Text", nullable = false)
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rmbs_application_id", nullable = false)
	public RmbsApplication getRmbsApplication() {
		return rmbsApplication;
	}

	public void setRmbsApplication(RmbsApplication rmbsApplication) {
		this.rmbsApplication = rmbsApplication;
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
        
}
