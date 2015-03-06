/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
@Table(name="bio_potensi_swot"
    ,catalog="hrm"
)
public class BioPotensiSwot  implements java.io.Serializable {


     private long id;
     private Integer version;
     private BioData bioData;
     private Integer klasifikasi;
     private String labelPotensi;
     private Double potensiPoint;
     private String description;
     private String createdBy;
     private Date createdOn;
     private String updatedBy;
     private Date updatedOn;

    public BioPotensiSwot() {
    }

	
    public BioPotensiSwot(long id, int klasifikasi) {
        this.id = id;
        this.klasifikasi = klasifikasi;
    }
    public BioPotensiSwot(long id, BioData bioData, int klasifikasi, String labelPotensi, Double potensiPoint, String description, String createdBy, Date createdOn, String updatedBy, Date updatedOn) {
       this.id = id;
       this.bioData = bioData;
       this.klasifikasi = klasifikasi;
       this.labelPotensi = labelPotensi;
       this.potensiPoint = potensiPoint;
       this.description = description;
       this.createdBy = createdBy;
       this.createdOn = createdOn;
       this.updatedBy = updatedBy;
       this.updatedOn = updatedOn;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
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
    @JoinColumn(name="bio_data_id")
    public BioData getBioData() {
        return this.bioData;
    }
    
    public void setBioData(BioData bioData) {
        this.bioData = bioData;
    }

    
    @Column(name="klasifikasi", nullable=false)
    public Integer getKlasifikasi() {
        return this.klasifikasi;
    }
    
    public void setKlasifikasi(Integer klasifikasi) {
        this.klasifikasi = klasifikasi;
    }

    
    @Column(name="label_potensi", length=60)
    public String getLabelPotensi() {
        return this.labelPotensi;
    }
    
    public void setLabelPotensi(String labelPotensi) {
        this.labelPotensi = labelPotensi;
    }

    
    @Column(name="potensi_point", precision=3, scale=0)
    public Double getPotensiPoint() {
        return this.potensiPoint;
    }
    
    public void setPotensiPoint(Double potensiPoint) {
        this.potensiPoint = potensiPoint;
    }

    
    @Column(name="description", length = 65535, columnDefinition = "Text")
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
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