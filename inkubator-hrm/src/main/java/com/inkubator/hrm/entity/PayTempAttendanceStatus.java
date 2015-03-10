package com.inkubator.hrm.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * PayTempKalkulasiEmpPajak generated by hbm2java
 */
@Entity
@Table(name="pay_temp_attendance_status" ,catalog="hrm_payroll")
public class PayTempAttendanceStatus  implements java.io.Serializable {


     private long id;
     private Integer version;
     private EmpData empData;    
     private Integer totalAttendance;
     private String createdBy;
     private Date createdOn;
     private String updatedBy;
     private Date updatedOn;

    public PayTempAttendanceStatus() {
    }

	
    public PayTempAttendanceStatus(long id) {
        this.id = id;
    }
    public PayTempAttendanceStatus(long id, EmpData empData,  String createdBy, Date createdOn) {
       this.id = id;
       this.empData = empData;       
       this.createdBy = createdBy;
       this.createdOn = createdOn;       
    }
   
     @Id   
    @Column(name="id", unique=true, nullable=false)
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }

    @Version
    @Column(name="version")
    public Integer getVersion() {
        return this.version;
    }
    
    public void setVersion(Integer version) {
        this.version = version;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="emp_data_id")
    public EmpData getEmpData() {
        return this.empData;
    }
    
    public void setEmpData(EmpData empData) {
        this.empData = empData;
    }

    @Column(name="total_attendance")
    public Integer getTotalAttendance() {
		return totalAttendance;
	}


	public void setTotalAttendance(Integer totalAttendance) {
		this.totalAttendance = totalAttendance;
	}


	@Column(name="created_by", length=45)
    public String getCreatedBy() {
        return this.createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_on", length=19)
    public Date getCreatedOn() {
        return this.createdOn;
    }
    
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
    
    @Column(name = "updated_by", length = 45)
    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_on", length = 19)
    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

}


