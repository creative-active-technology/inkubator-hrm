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
@Table(name = "emp_person_achievement", catalog = "hrm")
public class EmpPersonAchievement implements java.io.Serializable {

    private long id;
    private Integer version;
    private EmpData empData;
    private String achievementName;
    private String description;
    private Date dateAchievement;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;

    public EmpPersonAchievement() {
    }

    public EmpPersonAchievement(long id) {
        this.id = id;
    }

    public EmpPersonAchievement(long id, Integer version, EmpData empData, String achievementName, String description, Date dateAchievement, String createdBy, Date createdOn, String updatedBy, Date updatedOn) {
        this.id = id;
        this.version = version;
        this.empData = empData;
        this.achievementName = achievementName;
        this.description = description;
        this.dateAchievement = dateAchievement;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.updatedBy = updatedBy;
        this.updatedOn = updatedOn;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator = "emp_person_achievement_seq_gen")
    @Column(name = "id", unique = true, nullable = false)
    @SequenceGenerator(name = "emp_person_achievement_seq_gen", sequenceName = "EMP_PERSON_ACHIEVEMENT_SEQ")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Version
    @Column(name="version")
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    public EmpData getEmpData() {
        return empData;
    }

    public void setEmpData(EmpData empData) {
        this.empData = empData;
    }

    @Column(name="achievement_name", length=60)
    public String getAchievementName() {
        return achievementName;
    }

    public void setAchievementName(String achievementName) {
        this.achievementName = achievementName;
    }

    @Column(name="description", length=65535, columnDefinition = "Text")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_achievement", length = 19)
    public Date getDateAchievement() {
        return dateAchievement;
    }

    public void setDateAchievement(Date dateAchievement) {
        this.dateAchievement = dateAchievement;
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
}
