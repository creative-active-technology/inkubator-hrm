/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class ReportEmpMutationParameter extends SearchParameter{
    
    private Date startDate;
    private Date endDate;

    public Date getStartDate() {
//        if (StringUtils.equalsIgnoreCase(getKeyParam(), "startDate")) {
//			startDate = getParameter();
//        } else {
//        	startDate = null;
//        }
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
    
	
}
