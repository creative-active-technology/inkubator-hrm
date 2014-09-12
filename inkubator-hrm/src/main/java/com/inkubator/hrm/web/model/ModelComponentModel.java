package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author Taufik Hidayat
 */
public class ModelComponentModel implements Serializable {

    private Long id;
    private String code;
    private String name;
    private String description;
    private Integer spesific;
    private Long benefitGroupId;

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

    public Integer getSpesific() {
        return spesific;
    }

    public void setSpesific(Integer spesific) {
        this.spesific = spesific;
    }
        

    public Long getBenefitGroupId() {
        return benefitGroupId;
    }

    public void setBenefitGroupId(Long benefitGroupId) {
        this.benefitGroupId = benefitGroupId;
    }

}
