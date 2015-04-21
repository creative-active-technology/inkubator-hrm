package com.inkubator.hrm.web.model;


import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author EKA
 */
public class OrgTypeOfSpecListModel implements Serializable{
    private Long id;
    private String code;
    private String name;
    private String description;
    private Long orgTypeOfSpecId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getOrgTypeOfSpecId() {
        return orgTypeOfSpecId;
    }

    public void setOrgTypeOfSpecId(Long orgTypeOfSpecId) {
        this.orgTypeOfSpecId = orgTypeOfSpecId;
    }
    
    
}
