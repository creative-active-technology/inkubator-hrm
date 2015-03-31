package com.inkubator.hrm.web.model;

import java.io.Serializable;
import javax.validation.constraints.Pattern;

/**
 *
 * @author rizkykojek
 */
public class CompanyModel implements Serializable{

    private Long id;
    private String code;
    private byte[] companyLogo;
    private String companyLogoName;
    private String name;
    private String officialName;
    private String legalNo;
    private String level;
    private Long taxCountryId;
    private String taxAccountNumber;
    private String address;
    private Long countryId;
    private Long provinceId;
    private Long cityId;
    private Long businessTypeId;
    private Integer postalCode;
    private String phone;
    private String fax;
    private String vision;
    private String mision;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public byte[] getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(byte[] companyLogo) {
        this.companyLogo = companyLogo;
    }

    public String getCompanyLogoName() {
        return companyLogoName;
    }

    public void setCompanyLogoName(String companyLogoName) {
        this.companyLogoName = companyLogoName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOfficialName() {
        return officialName;
    }

    public void setOfficialName(String officialName) {
        this.officialName = officialName;
    }

    public String getLegalNo() {
        return legalNo;
    }

    public void setLegalNo(String legalNo) {
        this.legalNo = legalNo;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Long getTaxCountryId() {
        return taxCountryId;
    }

    public void setTaxCountryId(Long taxCountryId) {
        this.taxCountryId = taxCountryId;
    }

    public String getTaxAccountNumber() {
        return taxAccountNumber;
    }

    public void setTaxAccountNumber(String taxAccountNumber) {
        this.taxAccountNumber = taxAccountNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Long getBusinessTypeId() {
        return businessTypeId;
    }

    public void setBusinessTypeId(Long businessTypeId) {
        this.businessTypeId = businessTypeId;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    @Pattern(regexp = "^[+][\\d() -]+", message = "{errorr_phone}")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getVision() {
        return vision;
    }

    public void setVision(String vision) {
        this.vision = vision;
    }

    public String getMision() {
        return mision;
    }

    public void setMision(String mision) {
        this.mision = mision;
    }

}
