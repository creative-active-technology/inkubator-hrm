/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.search;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author arsyad_
 */
public class ReportSickDataSearchParameter extends SearchParameter{
    
    private Date startDate;
    private Date endDate;
    private List<Long> listGolJab = new ArrayList<Long>();
    private List<Long> listDepartment = new ArrayList<Long>();

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

	public List<Long> getListGolJab() {
        return listGolJab;
    }

    public void setListGolJab(List<Long> listGolJab) {
        this.listGolJab = listGolJab;
    }

    public List<Long> getListDepartment() {
        return listDepartment;
    }

    public void setListDepartment(List<Long> listDepartment) {
        this.listDepartment = listDepartment;
    }
    
    
}
