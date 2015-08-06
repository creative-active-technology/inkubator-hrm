/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Deni
 */
@Entity
@Table(name = "recruit_advertisement_media", catalog = "hrm"
)
public class RecruitAdvertisementMedia implements java.io.Serializable {

    private long id;
    private RecruitAdvertisementCategory recruitAdvertisementCategory;
    private String code;
    private String name;
    private String mediaAddress;
    private String phone;
    private String address;
    private String contactPerson;
    private String description;
    private Integer typeOfMedia;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;
    private Set<RecruitVacancyAdvertisement> recruitVacancyAdvertisements = new HashSet<RecruitVacancyAdvertisement>(0);

    public RecruitAdvertisementMedia() {
    }

    public RecruitAdvertisementMedia(long id, RecruitAdvertisementCategory recruitAdvertisementCategory) {
        this.id = id;
        this.recruitAdvertisementCategory = recruitAdvertisementCategory;
    }

    public RecruitAdvertisementMedia(long id, RecruitAdvertisementCategory recruitAdvertisementCategory, String code, String name, String mediaAddress, String phone, String address, String contactPerson, String description, Integer typeOfMedia) {
        this.id = id;
        this.recruitAdvertisementCategory = recruitAdvertisementCategory;
        this.code = code;
        this.name = name;
        this.mediaAddress = mediaAddress;
        this.phone = phone;
        this.address = address;
        this.contactPerson = contactPerson;
        this.description = description;
        this.typeOfMedia = typeOfMedia;
    }

    @Id

    @Column(name = "id", unique = true, nullable = false)
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advertisement_category_id", nullable = false)
    public RecruitAdvertisementCategory getRecruitAdvertisementCategory() {
        return this.recruitAdvertisementCategory;
    }

    public void setRecruitAdvertisementCategory(RecruitAdvertisementCategory recruitAdvertisementCategory) {
        this.recruitAdvertisementCategory = recruitAdvertisementCategory;
    }

    @Column(name = "media_code", length = 45)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "media_name", length = 60)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "media_address", length = 95)
    public String getMediaAddress() {
        return this.mediaAddress;
    }

    public void setMediaAddress(String mediaAddress) {
        this.mediaAddress = mediaAddress;
    }

    @Column(name = "phone", length = 45)
    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "address")
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "contact_person", length = 60)
    public String getContactPerson() {
        return this.contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    @Column(name = "description")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "type_of_media", length = 1)
    public Integer getTypeOfMedia() {
        return typeOfMedia;
    }

    public void setTypeOfMedia(Integer typeOfMedia) {
        this.typeOfMedia = typeOfMedia;
    }

    @Column(name = "created_by", length = 45)
    public String getCreatedBy() {
        return this.createdBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on", length = 19)
    public Date getCreatedOn() {
        return this.createdOn;
    }

    @Column(name = "updated_by", length = 45)
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_on", length = 19)
    public Date getUpdatedOn() {
        return this.updatedOn;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "advertisementMedia")
    public Set<RecruitVacancyAdvertisement> getRecruitVacancyAdvertisements() {
		return recruitVacancyAdvertisements;
	}

	public void setRecruitVacancyAdvertisements(Set<RecruitVacancyAdvertisement> recruitVacancyAdvertisements) {
		this.recruitVacancyAdvertisements = recruitVacancyAdvertisements;
	}

	@Transient
    public String getTipeMediaAsString(){
        String tipeMedia = "";
        if(typeOfMedia == 1){
            tipeMedia = "Internal";
        }else{
            tipeMedia = "Eksternal";
        }
        
        return tipeMedia;
    }
}
