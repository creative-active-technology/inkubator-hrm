/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author Deni
 */
public class LoanNewSchemaModel implements Serializable {

    private Long id;
    private String loanSchemaCode;
    private String loanSchemaName;
    private String description;
    private Long totalMaximumLoan;
    private Long totalMaximumInstallment;
    private String nomorSk;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoanSchemaCode() {
        return loanSchemaCode;
    }

    public void setLoanSchemaCode(String loanSchemaCode) {
        this.loanSchemaCode = loanSchemaCode;
    }

    public String getLoanSchemaName() {
        return loanSchemaName;
    }

    public void setLoanSchemaName(String loanSchemaName) {
        this.loanSchemaName = loanSchemaName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getTotalMaximumLoan() {
        return totalMaximumLoan;
    }

    public void setTotalMaximumLoan(Long totalMaximumLoan) {
        this.totalMaximumLoan = totalMaximumLoan;
    }

    public Long getTotalMaximumInstallment() {
        return totalMaximumInstallment;
    }

    public void setTotalMaximumInstallment(Long totalMaximumInstallment) {
        this.totalMaximumInstallment = totalMaximumInstallment;
    }

    public String getNomorSk() {
        return nomorSk;
    }

    public void setNomorSk(String nomorSk) {
        this.nomorSk = nomorSk;
    }
    
    
}
