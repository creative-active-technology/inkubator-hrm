package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class WtPeriodEmpViewModel implements Serializable {

    private BigInteger wtPeriodId;
    private BigInteger totalEmpData;
    private BigInteger totalWorkingGroup;
    private Date fromPeriode;
    private Date untilPeriode;
    private String status;

    public BigInteger getWtPeriodId() {
        return wtPeriodId;
    }

    public void setWtPeriodId(BigInteger wtPeriodId) {
        this.wtPeriodId = wtPeriodId;
    }

    public BigInteger getTotalEmpData() {
        return totalEmpData;
    }

    public void setTotalEmpData(BigInteger totalEmpData) {
        this.totalEmpData = totalEmpData;
    }

    public BigInteger getTotalWorkingGroup() {
        return totalWorkingGroup;
    }

    public void setTotalWorkingGroup(BigInteger totalWorkingGroup) {
        this.totalWorkingGroup = totalWorkingGroup;
    }

    public Date getFromPeriode() {
        return fromPeriode;
    }

    public void setFromPeriode(Date fromPeriode) {
        this.fromPeriode = fromPeriode;
    }

    public Date getUntilPeriode() {
        return untilPeriode;
    }

    public void setUntilPeriode(Date untilPeriode) {
        this.untilPeriode = untilPeriode;
    }

    

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
}
