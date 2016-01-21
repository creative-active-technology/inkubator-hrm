/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.search;

import java.util.ArrayList;
import java.util.List;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author rizkykojek
 */
public class AppraisalProgramEmployeeSearchParameter extends SearchParameter{
	
	private Long programId;
	private List<String> listGolJab =  new ArrayList<String>();
    private List<Long> listDepartment =  new ArrayList<Long>();
    private List<Long> listUnitKerja =  new ArrayList<Long>();
    
	public Long getProgramId() {
		return programId;
	}
	public void setProgramId(Long programId) {
		this.programId = programId;
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
	public List<Long> getListUnitKerja() {
		return listUnitKerja;
	}
	public void setListUnitKerja(List<Long> listUnitKerja) {
		this.listUnitKerja = listUnitKerja;
	}
    
    
    
}
