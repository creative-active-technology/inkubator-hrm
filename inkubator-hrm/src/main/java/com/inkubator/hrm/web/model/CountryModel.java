package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author Taufik Hidayat
 */
public class CountryModel implements Serializable {

    private Long id;
    private String countryCode;
    private String countryName;
    private byte[] flagIcon;
    private Integer phoneCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public byte[] getFlagIcon() {
        return flagIcon;
    }

    public void setFlagIcon(byte[] flagIcon) {
        this.flagIcon = flagIcon;
    }

    public Integer getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(Integer phoneCode) {
        this.phoneCode = phoneCode;
    }

    

}
