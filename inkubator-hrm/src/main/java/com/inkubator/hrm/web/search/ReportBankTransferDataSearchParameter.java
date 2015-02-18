package com.inkubator.hrm.web.search;

import java.util.ArrayList;
import java.util.List;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author rizkykojek
 */
public class ReportBankTransferDataSearchParameter extends SearchParameter {

    private Long periodeId;
    private Long departmentId;
    private List<String> listGolJab =  new ArrayList<String>();
    private Long bankId;

    public Long getPeriodeId() {
		return periodeId;
	}

	public void setPeriodeId(Long periodeId) {
		this.periodeId = periodeId;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public List<String> getListGolJab() {
		return listGolJab;
	}

	public void setListGolJab(List<String> listGolJab) {
		this.listGolJab = listGolJab;
	}

	public Long getBankId() {
		return bankId;
	}

	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}
	
}
