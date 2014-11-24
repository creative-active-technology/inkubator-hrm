package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author Taufik Hidayat
 */
public class PaySalaryJurnalModel implements Serializable {

    private Long id;
    private String code;
    private String name;
    private String description;
    private Long coa;
    private Integer typeJurnal;
    private Integer modelJurnal;

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

    public Long getCoa() {
        return coa;
    }

    public void setCoa(Long coa) {
        this.coa = coa;
    }

    public Integer getTypeJurnal() {
        return typeJurnal;
    }

    public void setTypeJurnal(Integer typeJurnal) {
        this.typeJurnal = typeJurnal;
    }

    public Integer getModelJurnal() {
        return modelJurnal;
    }

    public void setModelJurnal(Integer modelJurnal) {
        this.modelJurnal = modelJurnal;
    }
    
    
}
