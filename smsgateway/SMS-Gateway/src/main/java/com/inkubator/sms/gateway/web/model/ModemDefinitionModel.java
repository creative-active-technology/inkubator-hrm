/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.sms.gateway.web.model;

import java.io.Serializable;
import javax.validation.constraints.Pattern;

/**
 *
 * @author Deni Husni FR
 */
public class ModemDefinitionModel implements Serializable {

    private Long id;
    private String modemId;
    private String model;
    private String manufacetur;
    private String comId;
    private Integer pinNumber;
    private String smscNumber;
    private Double pricePerSms;
    private Double currentValue;
    private Integer baudRate;
    private String phoneNumber;
    private String checkPulsa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModemId() {
        return modemId;
    }

    public void setModemId(String modemId) {
        this.modemId = modemId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacetur() {
        return manufacetur;
    }

    public void setManufacetur(String manufacetur) {
        this.manufacetur = manufacetur;
    }

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId;
    }

    public Integer getPinNumber() {
        return pinNumber;
    }

    public void setPinNumber(Integer pinNumber) {
        this.pinNumber = pinNumber;
    }

    @Pattern(regexp = "^[+][\\d() -]+", message = "SMSC Number : bukan format no telepon yang benar")
    public String getSmscNumber() {
        return smscNumber;
    }

    public void setSmscNumber(String smscNumber) {
        this.smscNumber = smscNumber;
    }

    public Double getPricePerSms() {
        return pricePerSms;
    }

    public void setPricePerSms(Double pricePerSms) {
        this.pricePerSms = pricePerSms;
    }

    public Double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(Double currentValue) {
        this.currentValue = currentValue;
    }

    public Integer getBaudRate() {
        return baudRate;
    }

    public void setBaudRate(Integer baudRate) {
        this.baudRate = baudRate;
    }

    @Pattern(regexp = "^[+][\\d() -]+", message = "Nomor Telepon : bukan format no telepon yang benar")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCheckPulsa() {
        return checkPulsa;
    }

    public void setCheckPulsa(String checkPulsa) {
        this.checkPulsa = checkPulsa;
    }

}
