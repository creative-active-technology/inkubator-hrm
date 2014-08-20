package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author Taufik Hidayat
 */
public class AdmonitionTypeModel implements Serializable {

    private Long id;
    private String code;
    private String name;
    private String description;
    private Integer longTerm;
    private Integer suspend;
    private Double salaryCut;

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

    public Integer getLongTerm() {
        return longTerm;
    }

    public void setLongTerm(Integer longTerm) {
        this.longTerm = longTerm;
    }

    public Integer getSuspend() {
        return suspend;
    }

    public void setSuspend(Integer suspend) {
        this.suspend = suspend;
    }

    public Double getSalaryCut() {
        return salaryCut;
    }

    public void setSalaryCut(Double salaryCut) {
        this.salaryCut = salaryCut;
    }
    
    

}
