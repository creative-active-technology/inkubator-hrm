/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import com.inkubator.hrm.entity.SpecificationAbility;
import java.io.Serializable;

/**
 *
 * @author Deni
 */
public class JabatanSpesifikasiModel implements Serializable{
    private Long id;
    private Long specId;
    private Long jabatanId;
    private String specAbility;
    private String value;
    private String optionAbility;
    private String jabatan;
    private SpecificationAbility ability;

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }
    
    public SpecificationAbility getAbility() {
        return ability;
    }

    public void setAbility(SpecificationAbility ability) {
        this.ability = ability;
    }

    
    public Long getJabatanId() {
        return jabatanId;
    }

    public void setJabatanId(Long jabatanId) {
        this.jabatanId = jabatanId;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOptionAbility() {
        return optionAbility;
    }

    public void setOptionAbility(String optionAbility) {
        this.optionAbility = optionAbility;
    }
    
    public Long getSpecId() {
        return specId;
    }

    public void setSpecId(Long specId) {
        this.specId = specId;
    }
    
    public String getSpecAbility() {
        return specAbility;
    }

    public void setSpecAbility(String specAbility) {
        this.specAbility = specAbility;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    
}
