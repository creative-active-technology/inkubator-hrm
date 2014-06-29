package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author Taufik Hidayat
 */
public class InstitutionEducationModel implements Serializable {

    private Long id;
    private String institutionEducationCode;
    private String institutionEducationName;
    private Long cityId;
    private String address;
    private Integer postalCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInstitutionEducationCode() {
        return institutionEducationCode;
    }

    public void setInstitutionEducationCode(String institutionEducationCode) {
        this.institutionEducationCode = institutionEducationCode;
    }

    public String getInstitutionEducationName() {
        return institutionEducationName;
    }

    public void setInstitutionEducationName(String institutionEducationName) {
        this.institutionEducationName = institutionEducationName;
    }


    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    

    
}
