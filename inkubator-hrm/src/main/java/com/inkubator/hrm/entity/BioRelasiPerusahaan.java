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
@Table(name="bio_relasi_perusahaan"
    ,catalog="hrm"
)
public class BioRelasiPerusahaan  implements java.io.Serializable {


     private long id;
     private Integer version;
     private BioData bioData;
     private City city;
     private String relasiName;
     private String relasiEmail;
     private String relasiMobilePhone;
     private Integer postalCode;
     private String relasiCompany;
     private byte[] relasiAttachment;
     private String relasiJabatan;
     private String relasiAddress;
     private String relasiPhoneNumber;
     private String relasiAttachmentName;
     private Date createdOn;
     private String createdBy;
     private Date updatedOn;
     private String updatedBy;

    public BioRelasiPerusahaan() {
    }

	
    public BioRelasiPerusahaan(long id) {
        this.id = id;
    }
    public BioRelasiPerusahaan(long id, BioData bioData, City city, String relasiName, String relasiEmail, String relasiMobilePhone, String relasiCompany, byte[] relasiAttachment, String relasiJabatan, String relasiAddress, String relasiPhoneNumber, Date createdOn, String createdBy, Date updatedOn, String updatedBy) {
       this.id = id;
       this.bioData = bioData;
       this.city = city;
       this.relasiName = relasiName;
       this.relasiEmail = relasiEmail;
       this.relasiMobilePhone = relasiMobilePhone;
       this.relasiCompany = relasiCompany;
       this.relasiAttachment = relasiAttachment;
       this.relasiJabatan = relasiJabatan;
       this.relasiAddress = relasiAddress;
       this.relasiPhoneNumber = relasiPhoneNumber;
       this.createdOn = createdOn;
       this.createdBy = createdBy;
       this.updatedOn = updatedOn;
       this.updatedBy = updatedBy;
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
    @JoinColumn(name="biodata_id")
    public BioData getBioData() {
        return this.bioData;
    }
    
    public void setBioData(BioData bioData) {
        this.bioData = bioData;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="city_id")
    public City getCity() {
        return this.city;
    }
    
    public void setCity(City city) {
        this.city = city;
    }

    
    @Column(name="relasi_name", length=45)
    public String getRelasiName() {
        return this.relasiName;
    }
    
    public void setRelasiName(String relasiName) {
        this.relasiName = relasiName;
    }

    
    @Column(name="relasi_email", length=45)
    public String getRelasiEmail() {
        return this.relasiEmail;
    }
    
    public void setRelasiEmail(String relasiEmail) {
        this.relasiEmail = relasiEmail;
    }

    
    @Column(name="relasi_mobile_phone", length=45)
    public String getRelasiMobilePhone() {
        return this.relasiMobilePhone;
    }
    
    public void setRelasiMobilePhone(String relasiMobilePhone) {
        this.relasiMobilePhone = relasiMobilePhone;
    }

    
    @Column(name="relasi_company", length=100)
    public String getRelasiCompany() {
        return this.relasiCompany;
    }
    
    public void setRelasiCompany(String relasiCompany) {
        this.relasiCompany = relasiCompany;
    }

    
    @Column(name="relasi_attachment")
    public byte[] getRelasiAttachment() {
        return this.relasiAttachment;
    }
    
    public void setRelasiAttachment(byte[] relasiAttachment) {
        this.relasiAttachment = relasiAttachment;
    }

    
    @Column(name="relasi_jabatan", length=105)
    public String getRelasiJabatan() {
        return this.relasiJabatan;
    }
    
    public void setRelasiJabatan(String relasiJabatan) {
        this.relasiJabatan = relasiJabatan;
    }

    
    @Column(name="relasi_address")
    public String getRelasiAddress() {
        return this.relasiAddress;
    }
    
    public void setRelasiAddress(String relasiAddress) {
        this.relasiAddress = relasiAddress;
    }

    
    @Column(name="relasi_phone_number", length=45)
    public String getRelasiPhoneNumber() {
        return this.relasiPhoneNumber;
    }
    
    public void setRelasiPhoneNumber(String relasiPhoneNumber) {
        this.relasiPhoneNumber = relasiPhoneNumber;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_on", length=19)
    public Date getCreatedOn() {
        return this.createdOn;
    }
    
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    
    @Column(name="created_by", length=45)
    public String getCreatedBy() {
        return this.createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_on", length=19)
    public Date getUpdatedOn() {
        return this.updatedOn;
    }
    
    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    
    @Column(name="updated_by", length=45)
    public String getUpdatedBy() {
        return this.updatedBy;
    }
    
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Column(name="relasi_attachment_name")
    public String getRelasiAttachmentName() {
        return relasiAttachmentName;
    }

    public void setRelasiAttachmentName(String relasiAttachmentName) {
        this.relasiAttachmentName = relasiAttachmentName;
    }

    @Column(name="postal_code")
    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }




}


