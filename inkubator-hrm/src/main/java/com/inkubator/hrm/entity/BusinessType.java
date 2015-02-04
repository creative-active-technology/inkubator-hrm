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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "business_type", catalog = "hrm_personalia", uniqueConstraints = @UniqueConstraint(columnNames = "code")
)
public class BusinessType implements java.io.Serializable {
    private long id;
    private Integer version;
    private String code;
    private String name;
    private String description;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;
    private Set<Company> companies = new HashSet<Company>(0);

    public BusinessType() {
    }

    public BusinessType(long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator = "business_type_seq_gen")
    @Column(name = "id", unique = true, nullable = false)
    @SequenceGenerator(name = "business_type_seq_gen", sequenceName = "BUSINESS_TYPE_SEQ")
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

    @Column(name = "code", unique = true, nullable = false, length = 10)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "name", length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description", length = 65535, columnDefinition = "Text")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "businessType")
	public Set<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(Set<Company> companies) {
		this.companies = companies;
	}
        
}
