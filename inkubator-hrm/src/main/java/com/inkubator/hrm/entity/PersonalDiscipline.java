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
import javax.persistence.Version;

/**
 *
 * @author Deni
 */
@Entity
@Table(name = "personal_discipline", catalog = "hrm" )
public class PersonalDiscipline implements java.io.Serializable{
    private Long id;
    private Integer version;
    private EmpData empData;
    private AdmonitionType admonitionType;
    private Date expiredDate;
    private Date startDate;
    private String description;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;
    private CareerDisciplineType careerDisciplineType;

    public PersonalDiscipline() {
    }

    public PersonalDiscipline(Long id) {
        this.id = id;
    }

    public PersonalDiscipline(Long id, Integer version, EmpData empData, AdmonitionType admonitionType, Date expiredDate, Date startDate, String description, String createdBy, Date createdOn, String updatedBy, Date updatedOn, CareerDisciplineType careerDisciplineType) {
        this.id = id;
        this.version = version;
        this.empData = empData;
        this.admonitionType = admonitionType;
        this.expiredDate = expiredDate;
        this.startDate = startDate;
        this.description = description;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.updatedBy = updatedBy;
        this.updatedOn = updatedOn;
        this.careerDisciplineType = careerDisciplineType;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator = "personal_discipline_seq_gen")
    @Column(name = "id", unique = true, nullable = false)
    @SequenceGenerator(name = "personal_discipline_seq_gen", sequenceName = "PERSONAL_DISCIPLINE_SEQ")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
    @JoinColumn(name = "employee_id", nullable = false)
    public EmpData getEmpData() {
        return empData;
    }

    public void setEmpData(EmpData empData) {
        this.empData = empData;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admonition_type_id", nullable = false)
    public AdmonitionType getAdmonitionType() {
        return admonitionType;
    }

    public void setAdmonitionType(AdmonitionType admonitionType) {
        this.admonitionType = admonitionType;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expire_date", length = 19)
    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_date", length = 19)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Column(name="description", length=65535, columnDefinition="Text")
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

    @ManyToOne
    @JoinColumn(name="career_discipline_id")
	public CareerDisciplineType getCareerDisciplineType() {
		return careerDisciplineType;
	}

	public void setCareerDisciplineType(CareerDisciplineType careerDisciplineType) {
		this.careerDisciplineType = careerDisciplineType;
	}
    
    
}
