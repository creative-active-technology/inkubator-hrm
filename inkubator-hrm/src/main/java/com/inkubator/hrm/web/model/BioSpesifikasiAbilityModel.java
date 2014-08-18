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
public class BioSpesifikasiAbilityModel implements Serializable{
    private Long id;
    private Long specId;
    private Long bioDataId;
    private String score;
    private String optionAbility;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSpecId() {
        return specId;
    }

    public void setSpecId(Long specId) {
        this.specId = specId;
    }

    public Long getBioDataId() {
        return bioDataId;
    }

    public void setBioDataId(Long bioDataId) {
        this.bioDataId = bioDataId;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getOptionAbility() {
        return optionAbility;
    }

    public void setOptionAbility(String optionAbility) {
        this.optionAbility = optionAbility;
    }
    
    
}
