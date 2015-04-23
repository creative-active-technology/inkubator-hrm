/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author Deni
 */
public class RecruitSelectionTypeModel implements Serializable {

    private Long id;
    private String code;
    private String name;
    private Long cost;
    private Boolean useLibrary;
    private String newLabelName;
    private String[] newLabel;
    private String[] labelNames;

    public String getNewLabelName() {
        return newLabelName;
    }

    public void setNewLabelName(String newLabelName) {
        this.newLabelName = newLabelName;
    }
    
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

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public Boolean getUseLibrary() {
        return useLibrary;
    }

    public void setUseLibrary(Boolean useLibrary) {
        this.useLibrary = useLibrary;
    }

    public String[] getNewLabel() {
        return newLabel;
    }

    public void setNewLabel(String[] newLabel) {
        this.newLabel = newLabel;
    }

    public String[] getLabelNames() {
        return labelNames;
    }

    public void setLabelNames(String[] labelNames) {
        this.labelNames = labelNames;
    }
    
    
}
