/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

/**
 *
 * @author Deni
 */
@Entity
@Table(name="implementation_of_overtime"
    ,catalog="hrm_personalia"
        , uniqueConstraints = @UniqueConstraint(columnNames="code")
)
public class ImplementationOfOverTime implements java.io.Serializable {


     private long id;
     private Integer version;
     private EmpData empData;
     private WtOverTime wtOverTime;
     private String code;
     private Date implementationDate;
     private Date startTime;
     private Date endTime;
     private String approvalActivityNumber;
     private String createdBy;
     private Date createdOn;
     private String updatedBy;
     private Date updatedOn;
     private String overTimeName;

    public ImplementationOfOverTime() {
    }

	
    public ImplementationOfOverTime(long id, EmpData empData, WtOverTime wtOverTime, String code, Date startTime, Date endTime) {
        this.id = id;
        this.empData = empData;
        this.wtOverTime = wtOverTime;
        this.code = code;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public ImplementationOfOverTime(long id, EmpData empData, WtOverTime wtOverTime, String code, Date implementationDate, Date startTime, Date endTime, String createdBy, Date createdOn, String updatedBy, Date updatedOn) {
       this.id = id;
       this.empData = empData;
       this.wtOverTime = wtOverTime;
       this.code = code;
       this.implementationDate = implementationDate;
       this.startTime = startTime;
       this.endTime = endTime;
       this.createdBy = createdBy;
       this.createdOn = createdOn;
       this.updatedBy = updatedBy;
       this.updatedOn = updatedOn;
    }
   
     @Id 
    @GeneratedValue(strategy=GenerationType.AUTO, generator = "implementation_of_overtime_seq_gen")
    @Column(name="id", unique=true, nullable=false)
     @SequenceGenerator(name = "implementation_of_overtime_seq_gen", sequenceName = "IMPLEMENTATION_OF_OVERTIME_SEQ")
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
    @JoinColumn(name="employee_id", nullable=false)
    public EmpData getEmpData() {
        return this.empData;
    }
    
    public void setEmpData(EmpData empData) {
        this.empData = empData;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="overtime_id", nullable=false)
    public WtOverTime getWtOverTime() {
        return this.wtOverTime;
    }
    
    public void setWtOverTime(WtOverTime wtOverTime) {
        this.wtOverTime = wtOverTime;
    }
    
    @Column(name="code", nullable=false, length=45)
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="implementation_date", length=10)
    public Date getImplementationDate() {
        return this.implementationDate;
    }
    
    public void setImplementationDate(Date implementationDate) {
        this.implementationDate = implementationDate;
    }
    @Temporal(TemporalType.TIME)
    @Column(name="start_time", nullable=false, length=8)
    public Date getStartTime() {
        return this.startTime;
    }
    
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    @Temporal(TemporalType.TIME)
    @Column(name="end_time", nullable=false, length=8)
    public Date getEndTime() {
        return this.endTime;
    }
    
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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
    
    @Column(name="updated_by", length=45)
    public String getUpdatedBy() {
        return this.updatedBy;
    }
    
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_on", length=19)
    public Date getUpdatedOn() {
        return this.updatedOn;
    }
    
    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    @Column(name="approval_activity_number", length=45, unique=true)
    public String getApprovalActivityNumber() {
        return approvalActivityNumber;
    }

    public void setApprovalActivityNumber(String approvalActivityNumber) {
        this.approvalActivityNumber = approvalActivityNumber;
    }

    public String getOverTimeName() {
        return overTimeName;
    }

    public void setOverTimeName(String overTimeName) {
        this.overTimeName = overTimeName;
    }
    
    
}
