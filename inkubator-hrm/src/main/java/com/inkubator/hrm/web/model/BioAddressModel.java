package com.inkubator.hrm.web.model;

import java.io.Serializable;
import javax.validation.constraints.Pattern;


/**
 *
 * @author rizkykojek
 */
public class BioAddressModel implements Serializable{

	private Long id;
	private Long bioDataId;
    private Integer statusAddress;
    private Integer type;
    private String phoneNumber;
    private String addressDetail;
    private Long countryId;
    private Long provinceId;
    private Long cityId;
    private String subDistrict;
    private String village;
    private String notes;
    private Double latitude;
    private Double longitude;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getBioDataId() {
		return bioDataId;
	}
	public void setBioDataId(Long bioDataId) {
		this.bioDataId = bioDataId;
	}
	public Integer getStatusAddress() {
		return statusAddress;
	}
	public void setStatusAddress(Integer statusAddress) {
		this.statusAddress = statusAddress;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	@Pattern(regexp = "^[+][\\d() -]+", message = "{errorr_phone}")
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddressDetail() {
		return addressDetail;
	}
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
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
	public String getSubDistrict() {
		return subDistrict;
	}
	public void setSubDistrict(String subDistrict) {
		this.subDistrict = subDistrict;
	}
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}    
    
}
