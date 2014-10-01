/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author Deni
 */
public class NeracaCutiSearchParameter extends SearchParameter{

    private String leaveDistribution;
    private Double debet;
    private Double kredit;

    public String getLeaveDistribution() {
        return leaveDistribution;
    }

    public void setLeaveDistribution(String leaveDistribution) {
        this.leaveDistribution = leaveDistribution;
    }

    public Double getDebet() {
        return debet;
    }

    public void setDebet(Double debet) {
        this.debet = debet;
    }

    public Double getKredit() {
        return kredit;
    }

    public void setKredit(Double kredit) {
        this.kredit = kredit;
    }
    
    
}
