package com.inkubator.hrm.web.model;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

/**
 *
 * @author rizkykojek
 */
public class EducationNonFormalModel implements Serializable {

	private Long id;
	private String code;
    private String name;
    private String description;
    private String address;
    private Long countryId;
    private Long provinceId;
    private Long cityId;
    private String postalCode;
    private String officialPhoneNo;
    private String officialEmail;
    
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	@Pattern(regexp = "^([+][\\d() -]+)?", message = "{errorr_phone}")
	public String getOfficialPhoneNo() {
		return officialPhoneNo;
	}
	public void setOfficialPhoneNo(String officialPhoneNo) {
		this.officialPhoneNo = officialPhoneNo;
	}
	public String getOfficialEmail() {
		return officialEmail;
	}
	public void setOfficialEmail(String officialEmail) {
		this.officialEmail = officialEmail;
	}
	
	
}
