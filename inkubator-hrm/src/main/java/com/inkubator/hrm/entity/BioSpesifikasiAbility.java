/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.entity;

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
 *
 * @author Deni
 */
@Entity
@Table(name = "bio_spesifikasi_ability", catalog = "hrm")
public class BioSpesifikasiAbility implements java.io.Serializable {

    private long id;
    private Integer version;
    private BioData biodata;
    private SpecificationAbility specificationAbility;
    private String score;
    private String optionAbility;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;

    public BioSpesifikasiAbility() {
    }

    public BioSpesifikasiAbility(long id) {
        this.id = id;
    }

    public BioSpesifikasiAbility(long id, Integer version, BioData biodata, SpecificationAbility specificationAbility, String score, String optionAbility, String createdBy, Date createdOn, String updatedBy, Date updatedOn) {
        this.id = id;
        this.version = version;
        this.biodata = biodata;
        this.specificationAbility = specificationAbility;
        this.score = score;
        this.optionAbility = optionAbility;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.updatedBy = updatedBy;
        this.updatedOn = updatedOn;
    }
    
    @Id
    @Column(name="id", unique=true, nullable=false)
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
    @JoinColumn(name = "biodata_id")
    public BioData getBiodata() {
        return biodata;
    }

    public void setBiodata(BioData biodata) {
        this.biodata = biodata;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="specification_id")
    public SpecificationAbility getSpecificationAbility() {
        return specificationAbility;
    }

    public void setSpecificationAbility(SpecificationAbility specificationAbility) {
        this.specificationAbility = specificationAbility;
    }

    @Column(name="value", length=45)
    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Column(name = "option_ability", nullable = false)
    public String getOptionAbility() {
        return optionAbility;
    }

    public void setOptionAbility(String optionAbility) {
        this.optionAbility = optionAbility;
    }

    @Column(name="created_by", length=45)
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_on", length=19)
    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Column(name="updated_by", length=45)
    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_on", length=19)
    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }
}
