/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Deni
 */
@Entity
@Table(name="recruit_advertisement_media"
    ,catalog="hrm"
)
public class RecruitAdvertisementMedia  implements java.io.Serializable {


     private long id;
     private RecruitAdvertisementCategory recruitAdvertisementCategory;
     private String mediaCode;
     private String mediaName;
     private String mediaAddress;
     private String phone;
     private String address;
     private String contactPerson;
     private String description;
     private String typeOfMedia;

    public RecruitAdvertisementMedia() {
    }

	
    public RecruitAdvertisementMedia(long id, RecruitAdvertisementCategory recruitAdvertisementCategory) {
        this.id = id;
        this.recruitAdvertisementCategory = recruitAdvertisementCategory;
    }
    public RecruitAdvertisementMedia(long id, RecruitAdvertisementCategory recruitAdvertisementCategory, String mediaCode, String mediaName, String mediaAddress, String phone, String address, String contactPerson, String description, String typeOfMedia) {
       this.id = id;
       this.recruitAdvertisementCategory = recruitAdvertisementCategory;
       this.mediaCode = mediaCode;
       this.mediaName = mediaName;
       this.mediaAddress = mediaAddress;
       this.phone = phone;
       this.address = address;
       this.contactPerson = contactPerson;
       this.description = description;
       this.typeOfMedia = typeOfMedia;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="advertisement_category_id", nullable=false)
    public RecruitAdvertisementCategory getRecruitAdvertisementCategory() {
        return this.recruitAdvertisementCategory;
    }
    
    public void setRecruitAdvertisementCategory(RecruitAdvertisementCategory recruitAdvertisementCategory) {
        this.recruitAdvertisementCategory = recruitAdvertisementCategory;
    }

    
    @Column(name="media_code", length=45)
    public String getMediaCode() {
        return this.mediaCode;
    }
    
    public void setMediaCode(String mediaCode) {
        this.mediaCode = mediaCode;
    }

    
    @Column(name="media_name", length=60)
    public String getMediaName() {
        return this.mediaName;
    }
    
    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    
    @Column(name="media_address", length=95)
    public String getMediaAddress() {
        return this.mediaAddress;
    }
    
    public void setMediaAddress(String mediaAddress) {
        this.mediaAddress = mediaAddress;
    }

    
    @Column(name="phone", length=45)
    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }

    
    @Column(name="address")
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }

    
    @Column(name="contact_person", length=60)
    public String getContactPerson() {
        return this.contactPerson;
    }
    
    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    
    @Column(name="description")
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    
    @Column(name="type_of_media", length=9)
    public String getTypeOfMedia() {
        return this.typeOfMedia;
    }
    
    public void setTypeOfMedia(String typeOfMedia) {
        this.typeOfMedia = typeOfMedia;
    }




}

