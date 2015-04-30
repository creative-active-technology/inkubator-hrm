/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
@Table(name="org_type_of_spec_jabatan"
    ,catalog="hrm"
)
public class OrgTypeOfSpecJabatan  implements java.io.Serializable {


     private OrgTypeOfSpecJabatanId id;
     private Integer version;
     private Jabatan jabatan;
     private OrgTypeOfSpecList orgTypeOfSpecList;
     private String description;
     private Date createdOn;
     private Date updatedOn;
     private String createdBy;

    public OrgTypeOfSpecJabatan() {
    }

	
    public OrgTypeOfSpecJabatan(OrgTypeOfSpecJabatanId id, Jabatan jabatan, OrgTypeOfSpecList orgTypeOfSpecList) {
        this.id = id;
        this.jabatan = jabatan;
        this.orgTypeOfSpecList = orgTypeOfSpecList;
    }
    public OrgTypeOfSpecJabatan(OrgTypeOfSpecJabatanId id, Jabatan jabatan, OrgTypeOfSpecList orgTypeOfSpecList, String description, Date createdOn, Date updatedOn, String createdBy) {
       this.id = id;
       this.jabatan = jabatan;
       this.orgTypeOfSpecList = orgTypeOfSpecList;
       this.description = description;
       this.createdOn = createdOn;
       this.updatedOn = updatedOn;
       this.createdBy = createdBy;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="typeOfSpecListId", column=@Column(name="type_of_spec_list_id", nullable=false) ), 
        @AttributeOverride(name="jabatanId", column=@Column(name="jabatan_id", nullable=false) ) } )
    public OrgTypeOfSpecJabatanId getId() {
        return this.id;
    }
    
    public void setId(OrgTypeOfSpecJabatanId id) {
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
    @JoinColumn(name="jabatan_id", nullable=false, insertable=false, updatable=false)
    public Jabatan getJabatan() {
        return this.jabatan;
    }
    
    public void setJabatan(Jabatan jabatan) {
        this.jabatan = jabatan;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="type_of_spec_list_id", nullable=false, insertable=false, updatable=false)
    public OrgTypeOfSpecList getOrgTypeOfSpecList() {
        return this.orgTypeOfSpecList;
    }
    
    public void setOrgTypeOfSpecList(OrgTypeOfSpecList orgTypeOfSpecList) {
        this.orgTypeOfSpecList = orgTypeOfSpecList;
    }

    
    @Column(name="description")
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_on", length=19)
    public Date getCreatedOn() {
        return this.createdOn;
    }
    
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_on", length=19)
    public Date getUpdatedOn() {
        return this.updatedOn;
    }
    
    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    
    @Column(name="created_by", length=45)
    public String getCreatedBy() {
        return this.createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }




}
