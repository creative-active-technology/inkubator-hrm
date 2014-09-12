package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author Taufik Hidayat
 */
public class AppraisalDetailModel implements Serializable {

    private Long id;
    private String name;
    private String description;
    private Long appraisalElementId;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAppraisalElementId() {
        return appraisalElementId;
    }

    public void setAppraisalElementId(Long appraisalElementId) {
        this.appraisalElementId = appraisalElementId;
    }

    
    
    

}
