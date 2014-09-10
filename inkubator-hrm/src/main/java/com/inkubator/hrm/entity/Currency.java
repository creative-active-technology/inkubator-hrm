/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.entity;

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
 *
 * @author Deni
 */
@Entity
@Table(name = "currency", catalog = "hrm", uniqueConstraints = @UniqueConstraint(columnNames = "country_code")
)
public class Currency implements java.io.Serializable {

    private long id;
    private Integer version;
    private Country country;
    private String code;
    private String name;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;
    private String description;
    private Set<PaySalaryGrade> paySalaryGrades = new HashSet<PaySalaryGrade>(0);
    private Set<BioBankAccount> bioBankAccounts = new HashSet<BioBankAccount>(0);

    public Currency() {
    }

    public Currency(long id) {
        this.id = id;
    }

    public Currency(long id, Integer version, Country country, String code, String name, String createdBy, Date createdOn, String updatedBy, Date updatedOn, String description) {
        this.id = id;
        this.version = version;
        this.country = country;
        this.code = code;
        this.name = name;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.updatedBy = updatedBy;
        this.updatedOn = updatedOn;
        this.description = description;
    }

    @Id

    @Column(name = "id", unique = true, nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Version
    @Column(name = "version")
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Column(name = "country_code", length = 4)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "country_name", length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "created_by", length = 45)
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on", length = 19)
    public Date getCreatedOn() {
        return createdOn;
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

    @Column(name = "description", length = 65535, columnDefinition="Text")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "currency")
    public Set<PaySalaryGrade> getPaySalaryGrades() {
        return this.paySalaryGrades;
    }

    public void setPaySalaryGrades(Set<PaySalaryGrade> paySalaryGrades) {
        this.paySalaryGrades = paySalaryGrades;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "currency")
    public Set<BioBankAccount> getBioBankAccounts() {
        return bioBankAccounts;
    }

    public void setBioBankAccounts(Set<BioBankAccount> bioBankAccounts) {
        this.bioBankAccounts = bioBankAccounts;
    }

    
}
