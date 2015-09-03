package com.inkubator.hrm.entity;
// Generated Sep 1, 2014 8:41:20 PM by Hibernate Tools 3.6.0

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
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

/**
 * PublicHoliday generated by hbm2java
 */
@Entity
@Table(name = "public_holiday", catalog = "hrm_payroll_backup", uniqueConstraints = {
    @UniqueConstraint(columnNames = "code")})

public class PublicHoliday implements java.io.Serializable {

    private long id;
    private Integer version;
//    private LeaveScheme leaveScheme;
    private Leave leave;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;
    private Date startDate;
    private Date endDate;
    private String description;
    private String code;
    private Set<PublicHolidayException> publicHolidayExceptions = new HashSet<PublicHolidayException>(0);

    public PublicHoliday() {
    }

    public PublicHoliday(long id) {
        this.id = id;
    }

    public PublicHoliday(long id, Leave leave) {
        this.id = id;
        this.leave = leave;
    }

    public PublicHoliday(long id,Leave leave, String createdBy, Date createdOn, String updatedBy, Date updatedOn, Date startDate, Date endDate, String description, Set<PublicHolidayException> publicHolidayExceptions) {
        this.id = id;
        this.leave = leave;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.updatedBy = updatedBy;
        this.updatedOn = updatedOn;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.publicHolidayExceptions = publicHolidayExceptions;
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

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "leave_scheme_id", nullable = false)
//    public LeaveScheme getLeaveScheme() {
//        return this.leaveScheme;
//    }
//
//    public void setLeaveScheme(LeaveScheme leaveScheme) {
//        this.leaveScheme = leaveScheme;
//    }

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

    @Temporal(TemporalType.DATE)
    @Column(name = "start_date", length = 10)
    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "end_date", length = 10)
    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Column(name = "description", length = 65535, columnDefinition = "Text")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "publicHoliday")
    public Set<PublicHolidayException> getPublicHolidayExceptions() {
        return this.publicHolidayExceptions;
    }

    public void setPublicHolidayExceptions(Set<PublicHolidayException> publicHolidayExceptions) {
        this.publicHolidayExceptions = publicHolidayExceptions;
    }

    @Column(name = "code", unique = true, length = 60)
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leave_id", nullable = false)
    public Leave getLeave() {
        return leave;
    }

    public void setLeave(Leave leave) {
        this.leave = leave;
    }

}
