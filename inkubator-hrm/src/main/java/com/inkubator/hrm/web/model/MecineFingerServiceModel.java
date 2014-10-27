/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

/**
 *
 * @author Deni Husni FR
 */
public class MecineFingerServiceModel {

    private Long id;
    private Integer mecineMethode;
    private String name;
    private String code;
    private String description;
    private String hostIp;
    private Integer serviceData;
    private Integer protocolData;
    private String employeeBaseId;
    private String openProtocolPassword;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMecineMethode() {
        return mecineMethode;
    }

    public void setMecineMethode(Integer mecineMethode) {
        this.mecineMethode = mecineMethode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public Integer getServiceData() {
        return serviceData;
    }

    public void setServiceData(Integer serviceData) {
        this.serviceData = serviceData;
    }

    public Integer getProtocolData() {
        return protocolData;
    }

    public void setProtocolData(Integer protocolData) {
        this.protocolData = protocolData;
    }

    public String getEmployeeBaseId() {
        return employeeBaseId;
    }

    public void setEmployeeBaseId(String employeeBaseId) {
        this.employeeBaseId = employeeBaseId;
    }

    public String getOpenProtocolPassword() {
        return openProtocolPassword;
    }

    public void setOpenProtocolPassword(String openProtocolPassword) {
        this.openProtocolPassword = openProtocolPassword;
    }
    
    

}
