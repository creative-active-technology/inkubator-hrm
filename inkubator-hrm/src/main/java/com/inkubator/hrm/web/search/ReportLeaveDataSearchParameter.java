package com.inkubator.hrm.web.search;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.inkubator.hrm.entity.Department;
import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class ReportLeaveDataSearchParameter extends SearchParameter {

    private Date startDate;
    private Date endDate;
    private List<Department> listDepartment = new ArrayList<Department>();
    private List<String> listGolJab =  new ArrayList<String>();
    
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

	public List<Department> getListDepartment() {
		return listDepartment;
	}

	public void setListDepartment(List<Department> listDepartment) {
		this.listDepartment = listDepartment;
	}

	public List<String> getListGolJab() {
		return listGolJab;
	}

	public void setListGolJab(List<String> listGolJab) {
		this.listGolJab = listGolJab;
	}
	
}
