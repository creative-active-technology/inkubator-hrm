/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author Deni Husni FR
 */
public class MecineFingerServiceModel implements Serializable{

    private Long id;
    private Integer mecineMethode;
    private String name;
    private String code;
    private String description;
    private String hostIp;
    private Integer host1;
    private Integer host2;
    private Integer host3;
    private Integer host4;
    private Integer port;
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

    public Integer getHost1() {
        return host1;
    }

    public void setHost1(Integer host1) {
        this.host1 = host1;
    }

    public Integer getHost2() {
        return host2;
    }

    public void setHost2(Integer host2) {
        this.host2 = host2;
    }

    public Integer getHost3() {
        return host3;
    }

    public void setHost3(Integer host3) {
        this.host3 = host3;
    }

    public Integer getHost4() {
        return host4;
    }

    public void setHost4(Integer host4) {
        this.host4 = host4;
    }

    public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
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

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }
    
    

}
