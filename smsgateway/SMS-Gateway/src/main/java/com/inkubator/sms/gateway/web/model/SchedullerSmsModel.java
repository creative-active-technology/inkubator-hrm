/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.sms.gateway.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.Pattern;

/**
 *
 * @author denifahri
 */
public class SchedullerSmsModel implements Serializable {

    private Long id;
    private Long modemId;
    private String name;
    private String scheduleType;
    private Date sendDate;
    private Date sendTime;
    private String destination;
    private List<String> listPhone = new ArrayList<>();
    private String fromSending;
    private String smsContent;
    private String isRepeatOnCondition;// 
    private Integer repeatTime;
    private String listPhoneAsString;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getModemId() {
        return modemId;
    }

    public void setModemId(Long modemId) {
        this.modemId = modemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(String scheduleType) {
        this.scheduleType = scheduleType;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    @Pattern(regexp = "^[+][\\d() -]+", message = "Bukan Format No HP")
    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getFromSending() {
        return fromSending;
    }

    public void setFromSending(String fromSending) {
        this.fromSending = fromSending;
    }

    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }

    public String getIsRepeatOnCondition() {
        return isRepeatOnCondition;
    }

    public void setIsRepeatOnCondition(String isRepeatOnCondition) {
        this.isRepeatOnCondition = isRepeatOnCondition;
    }

    public Integer getRepeatTime() {
        return repeatTime;
    }

    public void setRepeatTime(Integer repeatTime) {
        this.repeatTime = repeatTime;
    }

    public List<String> getListPhone() {
        return listPhone;
    }

    public void setListPhone(List<String> listPhone) {
        this.listPhone = listPhone;
    }

    public String getListPhoneAsString() {
        return listPhoneAsString;
    }

    public void setListPhoneAsString(String listPhoneAsString) {
        this.listPhoneAsString = listPhoneAsString;
    }

}
