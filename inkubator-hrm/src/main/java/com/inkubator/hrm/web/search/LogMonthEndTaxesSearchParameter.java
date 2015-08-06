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
 * @author Deni
 */
public class LogMonthEndTaxesSearchParameter extends SearchParameter{
	private List<String> listGolJab =  new ArrayList<String>();
    private List<Long> listDepartment =  new ArrayList<Long>();
    private List<Long> listEmpType =  new ArrayList<Long>();
	
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
