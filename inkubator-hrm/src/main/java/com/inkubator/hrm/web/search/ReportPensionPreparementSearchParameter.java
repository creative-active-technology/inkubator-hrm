/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class ReportPensionPreparementSearchParameter extends SearchParameter {

    private List<Long> listDepartmentId;
    private List<Long> listEmployeeTypeId;
    private List<Integer> listEmployeeAges;

    public List<Long> getListDepartmentId() {
        return listDepartmentId;
    }

    public void setListDepartmentId(List<Long> listDepartmentId) {
        this.listDepartmentId = listDepartmentId;
    }

    public List<Long> getListEmployeeTypeId() {
        return listEmployeeTypeId;
    }

    public void setListEmployeeTypeId(List<Long> listEmployeeTypeId) {
        this.listEmployeeTypeId = listEmployeeTypeId;
    }

    public List<Integer> getListEmployeeAges() {
        return listEmployeeAges;
    }

    public void setListEmployeeAges(List<Integer> listEmployeeAges) {
        this.listEmployeeAges = listEmployeeAges;
    }
    
    
    
}
