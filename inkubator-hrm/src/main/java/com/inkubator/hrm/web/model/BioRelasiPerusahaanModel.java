/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.entity.City;
import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.Pattern;

/**
 *
 * @author Deni
 */
public class BioRelasiPerusahaanModel implements Serializable {

    private Long id;
    private Long bioData;
    private Long city;
    private String relasiName;
    private String relasiEmail;
    private String relasiMobilePhone;
    private String relasiCompany;
    private byte[] relasiAttachment;
    private String relasiJabatan;
    private String relasiAddress;
    private String relasiPhoneNumber;
    private Integer postCode;
    private Long countryId;
    private Long provinceId;
    private Long cityId;
    private String attachmentFileName;
    private String relasiAttachmentName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBioData() {
        return bioData;
    }

    public void setBioData(Long bioData) {
        this.bioData = bioData;
    }

    public Long getCity() {
        return city;
    }

    public void setCity(Long city) {
        this.city = city;
    }

    public String getRelasiName() {
        return relasiName;
    }

    public void setRelasiName(String relasiName) {
        this.relasiName = relasiName;
    }

    public String getRelasiEmail() {
        return relasiEmail;
    }

    public void setRelasiEmail(String relasiEmail) {
        this.relasiEmail = relasiEmail;
    }

    @Pattern(regexp = "^[+][\\d() -]+", message = "{errorr_phone}")
    public String getRelasiMobilePhone() {
        return relasiMobilePhone;
    }

    public void setRelasiMobilePhone(String relasiMobilePhone) {
        this.relasiMobilePhone = relasiMobilePhone;
    }

    public String getRelasiCompany() {
        return relasiCompany;
    }

    public void setRelasiCompany(String relasiCompany) {
        this.relasiCompany = relasiCompany;
    }

    public byte[] getRelasiAttachment() {
        return relasiAttachment;
    }

    public void setRelasiAttachment(byte[] relasiAttachment) {
        this.relasiAttachment = relasiAttachment;
    }

    public String getRelasiJabatan() {
        return relasiJabatan;
    }

    public void setRelasiJabatan(String relasiJabatan) {
        this.relasiJabatan = relasiJabatan;
    }

    public String getRelasiAddress() {
        return relasiAddress;
    }

    public void setRelasiAddress(String relasiAddress) {
        this.relasiAddress = relasiAddress;
    }

    @Pattern(regexp = "^[+][\\d() -]+", message = "{errorr_phone}")
    public String getRelasiPhoneNumber() {
        return relasiPhoneNumber;
    }

    public void setRelasiPhoneNumber(String relasiPhoneNumber) {
        this.relasiPhoneNumber = relasiPhoneNumber;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Integer getPostCode() {
        return postCode;
    }

    public void setPostCode(Integer postCode) {
        this.postCode = postCode;
    }

    public String getAttachmentFileName() {
        return attachmentFileName;
    }

    public void setAttachmentFileName(String attachmentFileName) {
        this.attachmentFileName = attachmentFileName;
    }

    public String getRelasiAttachmentName() {
        return relasiAttachmentName;
    }

    public void setRelasiAttachmentName(String relasiAttachmentName) {
        this.relasiAttachmentName = relasiAttachmentName;
    }

    
}
