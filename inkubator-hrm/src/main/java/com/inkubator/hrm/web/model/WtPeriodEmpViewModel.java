package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class WtPeriodEmpViewModel implements Serializable {

    private Long wtPeriodId;
    private Long totalEmpData;
    private Long totalWorkingGroup;
    private Date startPeriod;
    private Date endPeriod;
    private String status;

    public Long getWtPeriodId() {
        return wtPeriodId;
    }

    public void setWtPeriodId(Long wtPeriodId) {
        this.wtPeriodId = wtPeriodId;
    }

    public Long getTotalEmpData() {
        return totalEmpData;
    }

    public void setTotalEmpData(Long totalEmpData) {
        this.totalEmpData = totalEmpData;
    }

    public Long getTotalWorkingGroup() {
        return totalWorkingGroup;
    }

    public void setTotalWorkingGroup(Long totalWorkingGroup) {
        this.totalWorkingGroup = totalWorkingGroup;
    }

    public Date getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(Date startPeriod) {
        this.startPeriod = startPeriod;
    }

    public Date getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(Date endPeriod) {
        this.endPeriod = endPeriod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
}
