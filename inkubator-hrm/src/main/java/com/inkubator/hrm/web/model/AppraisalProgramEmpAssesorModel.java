/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import com.inkubator.hrm.entity.EmpData;
import java.io.Serializable;

/**
 *
 * @author denifahri
 */
public class AppraisalProgramEmpAssesorModel implements Serializable {
    
    private Long id;
    private Long appraisalProgramId;
    private Long empId;
    private Long jabatanId;
    private String jabatanName;
    private EmpData empData;
    private Integer scala;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAppraisalProgramId() {
        return appraisalProgramId;
    }

    public void setAppraisalProgramId(Long appraisalProgramId) {
        this.appraisalProgramId = appraisalProgramId;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

  
    public Integer getScala() {
        return scala;
    }

    public void setScala(Integer scala) {
        this.scala = scala;
    }

    public EmpData getEmpData() {
        return empData;
    }

    public void setEmpData(EmpData empData) {
        this.empData = empData;
    }

    public Long getJabatanId() {
        return jabatanId;
    }

    public void setJabatanId(Long jabatanId) {
        this.jabatanId = jabatanId;
    }

    public String getJabatanName() {
        return jabatanName;
    }

    public void setJabatanName(String jabatanName) {
        this.jabatanName = jabatanName;
    }

    
    
    
    
}
