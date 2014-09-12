/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.ReimbursmentSchema;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Deni
 */
public class ReimbursmentModel implements Serializable{
    private Long id;
    private Integer version;
    private ReimbursmentSchema reimbursmentSchema;
    private Long reimbursmentSchemaId;
    private EmpData empData;
    private String code;
    private Date claimDate;
    private Integer quantity;
    private BigDecimal nominal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public ReimbursmentSchema getReimbursmentSchema() {
        return reimbursmentSchema;
    }

    public void setReimbursmentSchema(ReimbursmentSchema reimbursmentSchema) {
        this.reimbursmentSchema = reimbursmentSchema;
    }

    public Long getReimbursmentSchemaId() {
        return reimbursmentSchemaId;
    }

    public void setReimbursmentSchemaId(Long reimbursmentSchemaId) {
        this.reimbursmentSchemaId = reimbursmentSchemaId;
    }

    public EmpData getEmpData() {
        return empData;
    }

    public void setEmpData(EmpData empData) {
        this.empData = empData;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(Date claimDate) {
        this.claimDate = claimDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getNominal() {
        return nominal;
    }

    public void setNominal(BigDecimal nominal) {
        this.nominal = nominal;
    }
    
    
}
