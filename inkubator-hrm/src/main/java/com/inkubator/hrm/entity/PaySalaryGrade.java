package com.inkubator.hrm.entity;
// Generated Aug 6, 2014 2:00:57 PM by Hibernate Tools 3.6.0

import java.math.BigDecimal;
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
 * PaySalaryGrade generated by hbm2java
 */
@Entity
@Table(name = "pay_salary_grade", catalog = "hrm"
)
public class PaySalaryGrade implements java.io.Serializable {

    private long id;
    private Integer version;
    private Currency currency;
    private Integer gradeSalary;
    private BigDecimal minSalary;
    private BigDecimal mediumSalary;
    private BigDecimal maxSalary;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;
    private Set<Jabatan> jabatans = new HashSet<Jabatan>(0);

    public PaySalaryGrade() {
    }

    public PaySalaryGrade(long id) {
        this.id = id;
    }

    public PaySalaryGrade(long id, Currency currency, Integer gradeSalary, BigDecimal minSalary, BigDecimal mediumSalary, BigDecimal maxSalary, String createdBy, Date createdOn, String updatedBy, Date updatedOn) {
        this.id = id;
        this.currency = currency;
        this.gradeSalary = gradeSalary;
        this.minSalary = minSalary;
        this.mediumSalary = mediumSalary;
        this.maxSalary = maxSalary;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.updatedBy = updatedBy;
        this.updatedOn = updatedOn;
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
    @JoinColumn(name = "currency_id")
    public Currency getCurrency() {
        return this.currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Column(name = "grade_salary")
    public Integer getGradeSalary() {
        return this.gradeSalary;
    }

    public void setGradeSalary(Integer gradeSalary) {
        this.gradeSalary = gradeSalary;
    }

    @Column(name = "min_salary", precision = 10, scale = 0)
    public BigDecimal getMinSalary() {
        return this.minSalary;
    }

    public void setMinSalary(BigDecimal minSalary) {
        this.minSalary = minSalary;
    }

    @Column(name = "medium_salary", precision = 10, scale = 0)
    public BigDecimal getMediumSalary() {
        return this.mediumSalary;
    }

    public void setMediumSalary(BigDecimal mediumSalary) {
        this.mediumSalary = mediumSalary;
    }

    @Column(name = "max_salary", precision = 10, scale = 0)
    public BigDecimal getMaxSalary() {
        return this.maxSalary;
    }

    public void setMaxSalary(BigDecimal maxSalary) {
        this.maxSalary = maxSalary;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "paySalaryGrade")
    public Set<Jabatan> getJabatans() {
        return this.jabatans;
    }

    public void setJabatans(Set<Jabatan> jabatans) {
        this.jabatans = jabatans;
    }

}
