/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.sms.gateway.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Pattern;

/**
 *
 * @author Deni Husni FR
 */
public class SmsSendModel implements Serializable {

    private Long id;
    private String phoneNumber;
    private List<String> listPhone = new ArrayList<>();
    private Long modemId;
    private String modemIdName;
    private String smsContent;
    private String listPhoneAsString;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Pattern(regexp = "^[+][\\d() -]+", message = "Bukan Format No HP")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<String> getListPhone() {
        return listPhone;
    }

    public void setListPhone(List<String> listPhone) {
        this.listPhone = listPhone;
    }

    public Long getModemId() {
        return modemId;
    }

    public void setModemId(Long modemId) {
        this.modemId = modemId;
    }

    public String getModemIdName() {
        return modemIdName;
    }

    public void setModemIdName(String modemIdName) {
        this.modemIdName = modemIdName;
    }

    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }

    public String getListPhoneAsString() {

        return listPhoneAsString;
    }

    public void setListPhoneAsString(String listPhoneAsString) {
        this.listPhoneAsString = listPhoneAsString;
    }

  

}
