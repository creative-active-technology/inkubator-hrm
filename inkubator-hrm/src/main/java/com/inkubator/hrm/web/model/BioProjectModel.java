package com.inkubator.hrm.web.model;

import com.inkubator.hrm.entity.BioProject;
import com.inkubator.hrm.entity.City;
import com.inkubator.hrm.entity.OccupationType;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Taufik Hidayat
 */
public class BioProjectModel implements Serializable {

    private Long id;
    private Long bioDataId;
    private Date startDate;
    private Date endDate;
    private String code;
    private String name;
    private String description;
    private String position;
    private String companyName;

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    
    
}
