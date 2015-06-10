package com.inkubator.hrm.entity;

import java.text.SimpleDateFormat;
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
import javax.persistence.Transient;
import javax.persistence.Version;

@Entity
@Table(name = "leave_implementation_date", catalog = "hrm_payroll"
)
public class LeaveImplementationDate implements java.io.Serializable {

    private Long id;
    private Integer version;
    private Date actualDate;
    private Boolean isCancelled;
    private String description;
    private LeaveImplementation leaveImplementation;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;

    public LeaveImplementationDate() {
    	
    }

    public LeaveImplementationDate(long id) {
        this.id = id;
    }

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "leave_implementation_date_seq_gen")
    @SequenceGenerator(name = "leave_implementation_date_seq_gen", sequenceName = "LEAVE_IMPLEMENTATION_DATE_SEQ")
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

    @Temporal(TemporalType.DATE)
    @Column(name = "actual_date", length = 19, nullable =  false)
	public Date getActualDate() {
		return actualDate;
	}

	public void setActualDate(Date actualDate) {
		this.actualDate = actualDate;
	}

	@Column(name = "is_cancelled")
	public Boolean getIsCancelled() {
		return isCancelled;
	}

	public void setIsCancelled(Boolean isCancelled) {
		this.isCancelled = isCancelled;
	}

	@Column(name = "description", length = 65535, columnDefinition = "Text")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}	

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leave_implementation_id")
	public LeaveImplementation getLeaveImplementation() {
		return leaveImplementation;
	}

	public void setLeaveImplementation(LeaveImplementation leaveImplementation) {
		this.leaveImplementation = leaveImplementation;
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
    
    @Transient
    public String getActualDateAsLabel(){
    	return new SimpleDateFormat("dd MMMM yyyy").format(actualDate);
    }    
    
}
