package com.inkubator.hrm.web.search;

import java.util.ArrayList;
import java.util.List;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author rizkykojek
 */
public class ReportSalaryNoteSearchParameter extends SearchParameter {

    private Long periodeId;
    private List<String> listGolJab =  new ArrayList<String>();
    private List<Long> listDepartment =  new ArrayList<Long>();
    private List<Long> listEmpType =  new ArrayList<Long>();

    public Long getPeriodeId() {
		return periodeId;
	}

	public void setPeriodeId(Long periodeId) {
		this.periodeId = periodeId;
	}

	public List<String> getListGolJab() {
		return listGolJab;
	}

	public void setListGolJab(List<String> listGolJab) {
		this.listGolJab = listGolJab;
	}

	public List<Long> getListDepartment() {
		return listDepartment;
	}

	public void setListDepartment(List<Long> listDepartment) {
		this.listDepartment = listDepartment;
	}

	public List<Long> getListEmpType() {
		return listEmpType;
	}

	public void setListEmpType(List<Long> listEmpType) {
		this.listEmpType = listEmpType;
	}
	
}
