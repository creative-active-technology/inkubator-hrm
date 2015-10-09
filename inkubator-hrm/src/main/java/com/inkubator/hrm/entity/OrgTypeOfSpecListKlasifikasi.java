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

/**
 *
 * @author Deni
 */
@Entity
@Table(name="org_type_of_spec_list_klasifikasi"
    ,catalog="hrm"
)
public class OrgTypeOfSpecListKlasifikasi  implements java.io.Serializable {


     private OrgTypeOfSpecListKlasifikasiId id;
     private KlasifikasiKerja klasifikasiKerja;
     private OrgTypeOfSpecList orgTypeOfSpecList;
     private String description;
     private String createdBy;
     private Date createdOn;
     private String updatedBy;
     private Date updatedOn;
     

    public OrgTypeOfSpecListKlasifikasi() {
    }

	
    public OrgTypeOfSpecListKlasifikasi(OrgTypeOfSpecListKlasifikasiId id, KlasifikasiKerja klasifikasiKerja, OrgTypeOfSpecList orgTypeOfSpecList) {
        this.id = id;
        this.klasifikasiKerja = klasifikasiKerja;
        this.orgTypeOfSpecList = orgTypeOfSpecList;
    }
    public OrgTypeOfSpecListKlasifikasi(OrgTypeOfSpecListKlasifikasiId id, KlasifikasiKerja klasifikasiKerja, OrgTypeOfSpecList orgTypeOfSpecList, String description) {
       this.id = id;
       this.klasifikasiKerja = klasifikasiKerja;
       this.orgTypeOfSpecList = orgTypeOfSpecList;
       this.description = description;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="orgTypeOfSpecListId", column=@Column(name="org_type_of_spec_list_id", nullable=false) ), 
        @AttributeOverride(name="klasifikasiId", column=@Column(name="klasifikasi_id", nullable=false) ) } )
    public OrgTypeOfSpecListKlasifikasiId getId() {
        return this.id;
    }
    
    public void setId(OrgTypeOfSpecListKlasifikasiId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="klasifikasi_id", nullable=false, insertable=false, updatable=false)
    public KlasifikasiKerja getKlasifikasiKerja() {
        return this.klasifikasiKerja;
    }
    
    public void setKlasifikasiKerja(KlasifikasiKerja klasifikasiKerja) {
        this.klasifikasiKerja = klasifikasiKerja;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="org_type_of_spec_list_id", nullable=false, insertable=false, updatable=false)
    public OrgTypeOfSpecList getOrgTypeOfSpecList() {
        return this.orgTypeOfSpecList;
    }
    
    public void setOrgTypeOfSpecList(OrgTypeOfSpecList orgTypeOfSpecList) {
        this.orgTypeOfSpecList = orgTypeOfSpecList;
    }

    
    @Column(name="description", length=65535)
    public String getDescription() {
        return this.description;
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


}
