package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author rizkykojek
 */
public class PublicHolidayModel implements Serializable {

    private Long id;
    private Long leaveSchemeId;
    private Date startDate;
    private Date endDate;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLeaveSchemeId() {
        return leaveSchemeId;
    }

    public void setLeaveSchemeId(Long leaveSchemeId) {
        this.leaveSchemeId = leaveSchemeId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
