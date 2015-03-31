/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author Deni
 */
public class SpecificationAbilityModelView implements Serializable{
    private Long id;
    private String name;
    private String optionAbility;
    private String scaleValue;
    private Boolean isEdit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOptionAbility() {
        return optionAbility;
    }

    public void setOptionAbility(String optionAbility) {
        this.optionAbility = optionAbility;
    }

    public String getScaleValue() {
        return scaleValue;
    }

    public void setScaleValue(String scaleValue) {
        this.scaleValue = scaleValue;
    }

    public Boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }
    
    
}
