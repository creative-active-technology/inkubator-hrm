/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author deni
 */
public class LoanPaymentDetailModel implements Serializable{
    private Date startDatePeriod;
    private Date endDataPeriod;
    private Date startDateAbsen;
    private Date endDateAbsen;

    public Date getStartDatePeriod() {
        return startDatePeriod;
    }

    public void setStartDatePeriod(Date startDatePeriod) {
        this.startDatePeriod = startDatePeriod;
    }
    
    public Date getEndDataPeriod() {
        return endDataPeriod;
    }

    public void setEndDataPeriod(Date endDataPeriod) {
        this.endDataPeriod = endDataPeriod;
    }

    public Date getStartDateAbsen() {
        return startDateAbsen;
    }

    public void setStartDateAbsen(Date startDateAbsen) {
        this.startDateAbsen = startDateAbsen;
    }

    public Date getEndDateAbsen() {
        return endDateAbsen;
    }

    public void setEndDateAbsen(Date endDateAbsen) {
        this.endDateAbsen = endDateAbsen;
    }
    
    
}
