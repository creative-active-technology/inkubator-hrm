/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class ReportDataComponentSearchParameter extends SearchParameter {

    private List<Long> listDepartmentId;
    private List<Long> listPaySalaryCompId;
    private List<Long> listGolJabatanId;
    private List<Long> listEmployeeTypeId;
    private Date startDate;
    private Date endDate;

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

    public List<Long> getListPaySalaryCompId() {
        return listPaySalaryCompId;
    }

    public void setListPaySalaryCompId(List<Long> listPaySalaryCompId) {
        this.listPaySalaryCompId = listPaySalaryCompId;
    }

    public List<Long> getListGolJabatanId() {
        return listGolJabatanId;
    }

    public void setListGolJabatanId(List<Long> listGolJabatanId) {
        this.listGolJabatanId = listGolJabatanId;
    }

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
    
    
}
