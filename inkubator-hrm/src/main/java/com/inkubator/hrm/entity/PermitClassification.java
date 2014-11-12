package com.inkubator.hrm.entity;
// Generated Sep 29, 2014 10:36:37 PM by Hibernate Tools 3.6.0


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
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

/**
 * PermitClassification generated by hbm2java
 */
@Entity
@Table(name="permit_classification"
    ,catalog="hrm"
    , uniqueConstraints = @UniqueConstraint(columnNames="code") 
)
public class PermitClassification  implements java.io.Serializable {


     private long id;
     private Integer version;
     private AttendanceStatus attendanceStatus;
     private String code;
     private String name;
     private Boolean status;
     private Integer calculation;
     private Integer basePeriod;
     private Integer availibility;
     private Date dateIncreased;
     private Integer quantity;
     private Integer limitByDay;
     private Boolean onePerEmployee;
     private Integer maxPerMonth;
     private Double salaryCut;
     private Boolean attachmentRequired;
     private String description;
     private String createdBy;
     private Date createdOn;
     private String updatedBy;
     private Date updatedOn;

    public PermitClassification() {
    }

	
    public PermitClassification(long id, AttendanceStatus attendanceStatus) {
        this.id = id;
        this.attendanceStatus = attendanceStatus;
    }
    
    public PermitClassification(long id) {
        this.id = id;
    }
    
    public PermitClassification(long id, AttendanceStatus attendanceStatus, String code, String name, Boolean status, Integer calculation, Integer basePeriod, Integer availibility, Date dateIncreased, Integer quantity, Integer limitByDay, Boolean onePerEmployee, Integer maxPerMonth, Double salaryCut, Boolean attachmentRequired, String description, String createdBy, Date createdOn, String updatedBy, Date updatedOn) {
       this.id = id;
       this.attendanceStatus = attendanceStatus;
       this.code = code;
       this.name = name;
       this.status = status;
       this.calculation = calculation;
       this.basePeriod = basePeriod;
       this.availibility = availibility;
       this.dateIncreased = dateIncreased;
       this.quantity = quantity;
       this.limitByDay = limitByDay;
       this.onePerEmployee = onePerEmployee;
       this.maxPerMonth = maxPerMonth;
       this.salaryCut = salaryCut;
       this.attachmentRequired = attachmentRequired;
       this.description = description;
       this.createdBy = createdBy;
       this.createdOn = createdOn;
       this.updatedBy = updatedBy;
       this.updatedOn = updatedOn;
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
    @JoinColumn(name="attendance_status_id", nullable=false)
    public AttendanceStatus getAttendanceStatus() {
        return this.attendanceStatus;
    }
    
    public void setAttendanceStatus(AttendanceStatus attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    
    @Column(name="code", unique=true, length=8)
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    
    @Column(name="name", length=60)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="status")
    public Boolean getStatus() {
        return this.status;
    }
    
    public void setStatus(Boolean status) {
        this.status = status;
    }

    
    @Column(name="calculation")
    public Integer getCalculation() {
        return this.calculation;
    }
    
    public void setCalculation(Integer calculation) {
        this.calculation = calculation;
    }

    
    @Column(name="base_period")
    public Integer getBasePeriod() {
        return this.basePeriod;
    }
    
    public void setBasePeriod(Integer basePeriod) {
        this.basePeriod = basePeriod;
    }

    
    @Column(name="availibility")
    public Integer getAvailibility() {
        return this.availibility;
    }
    
    public void setAvailibility(Integer availibility) {
        this.availibility = availibility;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="date_increased", length=10)
    public Date getDateIncreased() {
        return this.dateIncreased;
    }
    
    public void setDateIncreased(Date dateIncreased) {
        this.dateIncreased = dateIncreased;
    }

    
    @Column(name="quantity")
    public Integer getQuantity() {
        return this.quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    
    @Column(name="limit_by_day")
    public Integer getLimitByDay() {
        return this.limitByDay;
    }
    
    public void setLimitByDay(Integer limitByDay) {
        this.limitByDay = limitByDay;
    }

    
    @Column(name="one_per_employee")
    public Boolean getOnePerEmployee() {
        return this.onePerEmployee;
    }
    
    public void setOnePerEmployee(Boolean onePerEmployee) {
        this.onePerEmployee = onePerEmployee;
    }

    
    @Column(name="max_per_month")
    public Integer getMaxPerMonth() {
        return this.maxPerMonth;
    }
    
    public void setMaxPerMonth(Integer maxPerMonth) {
        this.maxPerMonth = maxPerMonth;
    }

    
    @Column(name="salary_cut", precision=22, scale=0)
    public Double getSalaryCut() {
        return this.salaryCut;
    }
    
    public void setSalaryCut(Double salaryCut) {
        this.salaryCut = salaryCut;
    }

    
    @Column(name="attachment_required")
    public Boolean getAttachmentRequired() {
        return this.attachmentRequired;
    }
    
    public void setAttachmentRequired(Boolean attachmentRequired) {
        this.attachmentRequired = attachmentRequired;
    }

    
    @Column(name="description", length=65535, columnDefinition="Text")
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
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




}


