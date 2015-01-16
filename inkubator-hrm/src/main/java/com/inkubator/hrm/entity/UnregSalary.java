package com.inkubator.hrm.entity;
// Generated Dec 24, 2014 8:49:26 AM by Hibernate Tools 4.3.1

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
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

/**
 * UnregSalary generated by hbm2java
 */
@Entity
@Table(name = "unreg_salary", catalog = "hrm", uniqueConstraints = @UniqueConstraint(columnNames = "code")
)
public class UnregSalary implements java.io.Serializable {

    private long id;
    private Integer version;
    private String code;
    private String name;
    private Date startPeriodDate;
    private Date endPeriodDate;
    private WtPeriode wtPeriode;
    private Date createdOn;
    private String createdBy;
    private Date updatedOn;
    private String updatedBy;
    private Date salaryDate;
    private Set<UnregDepartement> unregDepartements = new HashSet<UnregDepartement>(0);
    private Set<UnregGoljab> unregGoljabs = new HashSet<UnregGoljab>(0);
    private Set<UnregEmpType> unregEmpTypes = new HashSet<UnregEmpType>(0);
    private Set<UnregPayComponents> unregPayComponentses = new HashSet<UnregPayComponents>(0);
    private Set<UnregEmpReligion> unregEmpReligions = new HashSet<UnregEmpReligion>(0);
    private Integer totalUnregPayComponents;

    public UnregSalary() {
    }

    public UnregSalary(long id) {
        this.id = id;
    }

    public UnregSalary(long id, String code, String name, Date startPeriodDate, Date endPeriodDate, Long basedPeriodId, Date createdOn, String createdBy, Date updatedOn, String updatedBy, Date salaryDate, Set<UnregDepartement> unregDepartements, Set<UnregGoljab> unregGoljabs, Set<UnregEmpType> unregEmpTypes, Set<UnregPayComponents> unregPayComponentses, Set<UnregEmpReligion> unregEmpReligions) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.startPeriodDate = startPeriodDate;
        this.endPeriodDate = endPeriodDate;

        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.updatedOn = updatedOn;
        this.updatedBy = updatedBy;
        this.salaryDate = salaryDate;
        this.unregDepartements = unregDepartements;
        this.unregGoljabs = unregGoljabs;
        this.unregEmpTypes = unregEmpTypes;
        this.unregPayComponentses = unregPayComponentses;
        this.unregEmpReligions = unregEmpReligions;
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

    @Column(name = "code", unique = true, length = 8)
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "name", length = 100)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "start_period_date", length = 10)
    public Date getStartPeriodDate() {
        return this.startPeriodDate;
    }

    public void setStartPeriodDate(Date startPeriodDate) {
        this.startPeriodDate = startPeriodDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "end_period_date", length = 10)
    public Date getEndPeriodDate() {
        return this.endPeriodDate;
    }

    public void setEndPeriodDate(Date endPeriodDate) {
        this.endPeriodDate = endPeriodDate;
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_on", length = 19)
    public Date getUpdatedOn() {
        return this.updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    @Column(name = "updated_by", length = 45)
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "salary_date", length = 10)
    public Date getSalaryDate() {
        return this.salaryDate;
    }

    public void setSalaryDate(Date salaryDate) {
        this.salaryDate = salaryDate;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "unregSalary")
    public Set<UnregDepartement> getUnregDepartements() {
        return this.unregDepartements;
    }

    public void setUnregDepartements(Set<UnregDepartement> unregDepartements) {
        this.unregDepartements = unregDepartements;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "unregSalary")
    public Set<UnregGoljab> getUnregGoljabs() {
        return this.unregGoljabs;
    }

    public void setUnregGoljabs(Set<UnregGoljab> unregGoljabs) {
        this.unregGoljabs = unregGoljabs;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "unregSalary")
    public Set<UnregEmpType> getUnregEmpTypes() {
        return this.unregEmpTypes;
    }

    public void setUnregEmpTypes(Set<UnregEmpType> unregEmpTypes) {
        this.unregEmpTypes = unregEmpTypes;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "unregSalary")
    public Set<UnregPayComponents> getUnregPayComponentses() {
        return this.unregPayComponentses;
    }

    public void setUnregPayComponentses(Set<UnregPayComponents> unregPayComponentses) {
        this.unregPayComponentses = unregPayComponentses;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "unregSalary")
    public Set<UnregEmpReligion> getUnregEmpReligions() {
        return this.unregEmpReligions;
    }

    public void setUnregEmpReligions(Set<UnregEmpReligion> unregEmpReligions) {
        this.unregEmpReligions = unregEmpReligions;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "based_period_id")
    public WtPeriode getWtPeriode() {
        return this.wtPeriode;
    }

    public void setWtPeriode(WtPeriode wtPeriode) {
        this.wtPeriode = wtPeriode;
    }

    @Transient
	public Integer getTotalUnregPayComponents() {
		return totalUnregPayComponents;
	}

	public void setTotalUnregPayComponents(Integer totalUnregPayComponents) {
		this.totalUnregPayComponents = totalUnregPayComponents;
	}
    
}