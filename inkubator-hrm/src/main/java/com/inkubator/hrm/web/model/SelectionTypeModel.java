/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import com.inkubator.hrm.entity.Jabatan;
import java.io.Serializable;
import org.primefaces.model.DualListModel;

/**
 *
 * @author deni.fahri
 */
public class SelectionTypeModel implements Serializable {

    private Long selectionTemplateId;
    private Long parentId;
    private Long systemScoringId;
    private String code;
    private String name;
    private Boolean isCategory;
    private Double targetNilai;
    private String description;
    private DualListModel<Jabatan> dualListModel = new DualListModel<>();
    private Boolean isActive;

    public Long getSelectionTemplateId() {
        return selectionTemplateId;
    }

    public void setSelectionTemplateId(Long selectionTemplateId) {
        this.selectionTemplateId = selectionTemplateId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getSystemScoringId() {
        return systemScoringId;
    }

    public void setSystemScoringId(Long systemScoringId) {
        this.systemScoringId = systemScoringId;
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

    public Boolean getIsCategory() {
        return isCategory;
    }

    public void setIsCategory(Boolean isCategory) {
        this.isCategory = isCategory;
    }

    public Double getTargetNilai() {
        return targetNilai;
    }

    public void setTargetNilai(Double targetNilai) {
        this.targetNilai = targetNilai;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DualListModel<Jabatan> getDualListModel() {
        return dualListModel;
    }

    public void setDualListModel(DualListModel<Jabatan> dualListModel) {
        this.dualListModel = dualListModel;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    
    
    

}
