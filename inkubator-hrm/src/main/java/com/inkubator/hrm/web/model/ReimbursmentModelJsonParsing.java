/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import com.inkubator.hrm.entity.EmpData;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Deni
 */
public class ReimbursmentModelJsonParsing implements Serializable{
    private Long id;
    private Long reimbursmentSchemaId;
    private Long empDataId;
    private String code;
    private Date claimDate;
    private Integer quantity;
    private BigDecimal nominal;
    private String createBy;
    private Date createDate;
    private byte[] reimbursmentDocument;
    private String reimbursmentFileName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReimbursmentSchemaId() {
        return reimbursmentSchemaId;
    }

    public void setReimbursmentSchemaId(Long reimbursmentSchemaId) {
        this.reimbursmentSchemaId = reimbursmentSchemaId;
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

    public Long getEmpDataId() {
        return empDataId;
    }

    public void setEmpDataId(Long empDataId) {
        this.empDataId = empDataId;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public byte[] getReimbursmentDocument() {
        return reimbursmentDocument;
    }

    public void setReimbursmentDocument(byte[] reimbursmentDocument) {
        this.reimbursmentDocument = reimbursmentDocument;
    }

    public String getReimbursmentFileName() {
        return reimbursmentFileName;
    }

    public void setReimbursmentFileName(String reimbursmentFileName) {
        this.reimbursmentFileName = reimbursmentFileName;
    }
    
    
}
