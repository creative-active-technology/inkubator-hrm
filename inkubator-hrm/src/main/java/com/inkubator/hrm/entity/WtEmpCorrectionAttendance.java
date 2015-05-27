package com.inkubator.hrm.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "wt_emp_correction_attendance", catalog = "hrm")
public class WtEmpCorrectionAttendance implements Serializable {

    private Long id;
    private Integer version;
    
    private EmpData empData;
    private Date startDate;
    private Date endDate;
    private Date requestDate;
    private String requestCode;  
    private String approvalActivityNumber;
    
    private String createdBy;
    private String updatedBy;
    private Date createdOn;
    private Date updatedOn;
    private Set<WtEmpCorrectionAttendanceDetail> wtEmpCorrectionAttendanceDetails = new HashSet<WtEmpCorrectionAttendanceDetail>(0);

    public WtEmpCorrectionAttendance() {

    }

    public WtEmpCorrectionAttendance(Long id) {
        this.id = id;
    }

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "wt_emp_correction_attendance_seq_gen")
    @SequenceGenerator(name = "wt_emp_correction_attendance_seq_gen", sequenceName = "WT_EMP_CORRECTION_ATTENDANCE_SEQ")
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
    @JoinColumn(name = "emp_data_id", nullable = false)
	public EmpData getEmpData() {
		return empData;
	}

	public void setEmpData(EmpData empData) {
		this.empData = empData;
	}	

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_date", length = 19, nullable = false)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_date", length = 19, nullable = false)
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "request_date", length = 19, nullable = false)
	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	@Column(name = "request_code", length = 45, unique = true, nullable = false)
	public String getRequestCode() {
		return requestCode;
	}

	public void setRequestCode(String requestCode) {
		this.requestCode = requestCode;
	}	
	
	@Column(name = "approval_activity_number", length = 45, unique = true)
    public String getApprovalActivityNumber() {
        return approvalActivityNumber;
    }

    public void setApprovalActivityNumber(String approvalActivityNumber) {
        this.approvalActivityNumber = approvalActivityNumber;
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
    @Column(name = "created_on", length = 19)
    public Date getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_on", length = 19)
    public Date getUpdatedOn() {
        return this.updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "wtEmpCorrectionAttendance")
    @Cascade({CascadeType.SAVE_UPDATE})
	public Set<WtEmpCorrectionAttendanceDetail> getWtEmpCorrectionAttendanceDetails() {
		return wtEmpCorrectionAttendanceDetails;
	}

	public void setWtEmpCorrectionAttendanceDetails(Set<WtEmpCorrectionAttendanceDetail> wtEmpCorrectionAttendanceDetails) {
		this.wtEmpCorrectionAttendanceDetails = wtEmpCorrectionAttendanceDetails;
	}
    
}
