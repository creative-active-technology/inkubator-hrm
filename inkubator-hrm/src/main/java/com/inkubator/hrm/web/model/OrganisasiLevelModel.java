/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import com.inkubator.hrm.entity.UnitKerja;
import java.io.Serializable;
import org.primefaces.model.DualListModel;

/**
 *
 * @author deni.fahri
 */
public class OrganisasiLevelModel implements Serializable  {
    private Long departemetId;
    private String code;
    private String name;
    private String description;
    private Long companyId;
    private Long parentId;
    private String orgLevel;
    private Boolean isNextHirarki;
    private Boolean isActive;
    private DualListModel<UnitKerja> dualListModel=new DualListModel<>();
    private String companyCode;

    public Long getDepartemetId() {
        return departemetId;
    }

    public void setDepartemetId(Long departemetId) {
        this.departemetId = departemetId;
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

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getOrgLevel() {
        return orgLevel;
    }

    public void setOrgLevel(String orgLevel) {
        this.orgLevel = orgLevel;
    }

    public Boolean getIsNextHirarki() {
        return isNextHirarki;
    }

    public void setIsNextHirarki(Boolean isNextHirarki) {
        this.isNextHirarki = isNextHirarki;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public DualListModel<UnitKerja> getDualListModel() {
        return dualListModel;
    }

    public void setDualListModel(DualListModel<UnitKerja> dualListModel) {
        this.dualListModel = dualListModel;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
    
    
}
