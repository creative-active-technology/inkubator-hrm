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
@Table(name = "bio_keahlian", catalog = "hrm")
public class BioKeahlian implements java.io.Serializable {

    private long id;
    private Integer version;
    private BioData biodata;
    private String name;
    private Integer tingkatKeahlian;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;

    public BioKeahlian() {
    }

    public BioKeahlian(long id) {
        this.id = id;
    }

    public BioKeahlian(long id, Integer version, BioData biodata, String name, Integer tingkatKeahlian, String createdBy, Date createdOn, String updatedBy, Date updatedOn) {
        this.id = id;
        this.version = version;
        this.biodata = biodata;
        this.name = name;
        this.tingkatKeahlian = tingkatKeahlian;
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

    @Column(name="name", length=100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="tingkat_keahlian", length=1)
    public Integer getTingkatKeahlian() {
        return tingkatKeahlian;
    }

    public void setTingkatKeahlian(Integer tingkatKeahlian) {
        this.tingkatKeahlian = tingkatKeahlian;
    }

    @Column(name="created_by", length=45)
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

    @Column(name="updated_by", length=45)
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
