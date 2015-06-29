package com.inkubator.hrm.web.search;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.primefaces.model.DualListModel;

import ch.lambdaj.Lambda;

import com.inkubator.hrm.entity.Department;
import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author rizkykojek
 */
public class ReportPermitHistorySearchParameter extends SearchParameter {

    private Date startDate;
    private Date endDate;
    private List<String> listGolJab =  new ArrayList<String>();
    private List<Long> listDepartment =  new ArrayList<Long>();
    private DualListModel<String> golJabDualModel;
	private DualListModel<Department> departmentDualModel;
    
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
	public List<String> getListGolJab() {
		return golJabDualModel.getTarget();
	}
	public void setListGolJab(List<String> listGolJab) {
		this.listGolJab = listGolJab;
	}
	public List<Long> getListDepartment() {
		return Lambda.extract(departmentDualModel.getTarget(), Lambda.on(Department.class).getId());
	}
	public void setListDepartment(List<Long> listDepartment) {
		this.listDepartment = listDepartment;
	}
	public DualListModel<String> getGolJabDualModel() {
		return golJabDualModel;
	}
	public void setGolJabDualModel(DualListModel<String> golJabDualModel) {
		this.golJabDualModel = golJabDualModel;
	}
	public DualListModel<Department> getDepartmentDualModel() {
		return departmentDualModel;
	}
	public void setDepartmentDualModel(DualListModel<Department> departmentDualModel) {
		this.departmentDualModel = departmentDualModel;
	}
    
}
