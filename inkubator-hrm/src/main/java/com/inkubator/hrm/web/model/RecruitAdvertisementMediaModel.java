/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;
import javax.validation.constraints.Pattern;

/**
 *
 * @author Deni
 */
public class RecruitAdvertisementMediaModel implements Serializable {
    private Long id;
    private Long recruitAdvertisementCategoryId;
    private String code;
    private String name;
    private String mediaAddress;
    private String phone;
    private String address;
    private String contactPerson;
    private String description;
    private Integer typeOfMedia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRecruitAdvertisementCategoryId() {
        return recruitAdvertisementCategoryId;
    }

    public void setRecruitAdvertisementCategoryId(Long recruitAdvertisementCategoryId) {
        this.recruitAdvertisementCategoryId = recruitAdvertisementCategoryId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMediaAddress() {
        return mediaAddress;
    }

    public void setMediaAddress(String mediaAddress) {
        this.mediaAddress = mediaAddress;
    }

    @Pattern(regexp = "^[+][\\d() -]+", message = "{errorr_phone}")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTypeOfMedia() {
        return typeOfMedia;
    }

    public void setTypeOfMedia(Integer typeOfMedia) {
        this.typeOfMedia = typeOfMedia;
    }
}
