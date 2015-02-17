package com.inkubator.hrm.web.model;

import java.io.Serializable;
import javax.validation.constraints.Pattern;

/**
 *
 * @author Taufik Hidayat
 */
public class ProvinceModel implements Serializable {

    private Long id;
    private String provinceCode;
    private String provinceName;
    private Long countryId;
    private String description;
    private String provincePhoneCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Pattern(regexp = "^[+][\\d() -]+", message = "{errorr_phone}")
    public String getProvincePhoneCode() {
        return provincePhoneCode;
    }

    public void setProvincePhoneCode(String provincePhoneCode) {
        this.provincePhoneCode = provincePhoneCode;
    }

}
