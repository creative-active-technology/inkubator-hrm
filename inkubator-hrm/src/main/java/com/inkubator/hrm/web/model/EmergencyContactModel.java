package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author Deni Husni FR
 */
public class EmergencyContactModel implements Serializable {

    private Long id;
    private String name;
    private long bioDataId;
    private long familyRelationId;
    private long cityId;
    private String phoneNumber;
    private Boolean isSameHouse;
    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getBioDataId() {
        return bioDataId;
    }

    public void setBioDataId(long bioDataId) {
        this.bioDataId = bioDataId;
    }

    public long getFamilyRelationId() {
        return familyRelationId;
    }

    public void setFamilyRelationId(long familyRelationId) {
        this.familyRelationId = familyRelationId;
    }

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getIsSameHouse() {
        return isSameHouse;
    }

    public void setIsSameHouse(Boolean isSameHouse) {
        this.isSameHouse = isSameHouse;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    

}
