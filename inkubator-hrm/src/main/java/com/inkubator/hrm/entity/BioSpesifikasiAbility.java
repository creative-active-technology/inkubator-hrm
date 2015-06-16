/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.entity;

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name="bio_spesifikasi_ability"
    ,catalog="hrm_payroll_backup"
)
public class BioSpesifikasiAbility  implements java.io.Serializable {


     private BioSpesifikasiAbilityId id;
     private Integer version;
     private BioData bioData;
     private SpecificationAbility specificationAbility;
     private String createdBy;
     private Date createdOn;
     private String optionAbility;
     private String value;
     private String updatedBy;
     private Date updatedOn;

    public BioSpesifikasiAbility() {
    }

	
    public BioSpesifikasiAbility(BioSpesifikasiAbilityId id, BioData bioData, SpecificationAbility specificationAbility, String optionAbility) {
        this.id = id;
        this.bioData = bioData;
        this.specificationAbility = specificationAbility;
        this.optionAbility = optionAbility;
    }
    public BioSpesifikasiAbility(BioSpesifikasiAbilityId id, BioData bioData, SpecificationAbility specificationAbility, String createdBy, Date createdOn, String optionAbility, String value, String updatedBy, Date updatedOn) {
       this.id = id;
       this.bioData = bioData;
       this.specificationAbility = specificationAbility;
       this.createdBy = createdBy;
       this.createdOn = createdOn;
       this.optionAbility = optionAbility;
       this.value = value;
       this.updatedBy = updatedBy;
       this.updatedOn = updatedOn;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="biodataId", column=@Column(name="biodata_id", nullable=false) ), 
        @AttributeOverride(name="specificationId", column=@Column(name="specification_id", nullable=false) ) } )
    public BioSpesifikasiAbilityId getId() {
        return this.id;
    }
    
    public void setId(BioSpesifikasiAbilityId id) {
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
    @JoinColumn(name="biodata_id", nullable=false, insertable=false, updatable=false)
    public BioData getBioData() {
        return this.bioData;
    }
    
    public void setBioData(BioData bioData) {
        this.bioData = bioData;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="specification_id", nullable=false, insertable=false, updatable=false)
    public SpecificationAbility getSpecificationAbility() {
        return this.specificationAbility;
    }
    
    public void setSpecificationAbility(SpecificationAbility specificationAbility) {
        this.specificationAbility = specificationAbility;
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

    
    @Column(name="option_ability", nullable=false, length=100)
    public String getOptionAbility() {
        return this.optionAbility;
    }
    
    public void setOptionAbility(String optionAbility) {
        this.optionAbility = optionAbility;
    }

    
    @Column(name="value", length=45)
    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
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
