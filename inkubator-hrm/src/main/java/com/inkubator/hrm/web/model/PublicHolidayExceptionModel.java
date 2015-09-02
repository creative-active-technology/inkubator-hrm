package com.inkubator.hrm.web.model;

import com.inkubator.hrm.entity.EmpData;
import java.io.Serializable;

/**
 *
 * @author Taufik Hidayat
 */
public class PublicHolidayExceptionModel implements Serializable {

    private Long id;
    private String description;
    private Long publicHolidayId;
    private EmpData empData;
    private Long leavSchemaId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPublicHolidayId() {
        return publicHolidayId;
    }

    public void setPublicHolidayId(Long publicHolidayId) {
        this.publicHolidayId = publicHolidayId;
    }

    public EmpData getEmpData() {
        return empData;
    }

    public void setEmpData(EmpData empData) {
        this.empData = empData;
    }

    public Long getLeavSchemaId() {
        return leavSchemaId;
    }

    public void setLeavSchemaId(Long leavSchemaId) {
        this.leavSchemaId = leavSchemaId;
    }

    

    
}
