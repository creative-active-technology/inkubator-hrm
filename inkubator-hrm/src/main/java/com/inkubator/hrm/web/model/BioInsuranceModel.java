package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author Taufik Hidayat
 */
public class BioInsuranceModel implements Serializable {

    private Long id;
    private Long bioDataId;
    private String noPolicy;
    private String instance;
    private String facility;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBioDataId() {
        return bioDataId;
    }

    public void setBioDataId(Long bioDataId) {
        this.bioDataId = bioDataId;
    }
    
    

    public String getNoPolicy() {
        return noPolicy;
    }

    public void setNoPolicy(String noPolicy) {
        this.noPolicy = noPolicy;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
