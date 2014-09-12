package com.inkubator.hrm.entity;
// Generated Sep 1, 2014 8:41:20 PM by Hibernate Tools 3.6.0

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
 * PublicHolidayException generated by hbm2java
 */
@Entity
@Table(name = "public_holiday_exception", catalog = "hrm"
)
public class PublicHolidayException implements java.io.Serializable {

    private long id;
    private Integer version;
    private EmpData empData;
    private PublicHoliday publicHoliday;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;
    private String description;

    public PublicHolidayException() {
    }

    public PublicHolidayException(long id) {
        this.id = id;
    }

    public PublicHolidayException(long id, EmpData empData, PublicHoliday publicHoliday) {
        this.id = id;
        this.empData = empData;
        this.publicHoliday = publicHoliday;
    }

    public PublicHolidayException(long id, EmpData empData, PublicHoliday publicHoliday, String createdBy, Date createdOn, String updatedBy, Date updatedOn, String description) {
        this.id = id;
        this.empData = empData;
        this.publicHoliday = publicHoliday;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.updatedBy = updatedBy;
        this.updatedOn = updatedOn;
        this.description = description;
    }

    @Id

    @Column(name = "id", unique = true, nullable = false)
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
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
    @JoinColumn(name = "emp_id", nullable = false)
    public EmpData getEmpData() {
        return this.empData;
    }

    public void setEmpData(EmpData empData) {
        this.empData = empData;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "public_holiday_id", nullable = false)
    public PublicHoliday getPublicHoliday() {
        return this.publicHoliday;
    }

    public void setPublicHoliday(PublicHoliday publicHoliday) {
        this.publicHoliday = publicHoliday;
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

    
    @Column(name="description", length=65535, columnDefinition = "Text")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
