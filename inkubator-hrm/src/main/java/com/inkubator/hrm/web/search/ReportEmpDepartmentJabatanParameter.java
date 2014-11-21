/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.search;

import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.webcore.util.SearchParameter;
import java.util.List;

/**
 *
 * @author Taufik
 */
public class ReportEmpDepartmentJabatanParameter extends SearchParameter {

    private Long departmentId;
    private List<Long> golonganJabatanId;

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public List<Long> getGolonganJabatanId() {
        return golonganJabatanId;
    }

    public void setGolonganJabatanId(List<Long> golonganJabatanId) {
        this.golonganJabatanId = golonganJabatanId;
    }

    

}
