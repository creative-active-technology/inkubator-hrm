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
public class LoanNewSchemaListOfTypeModel implements Serializable {

    private Long id;
    private Long loanNewSchemaId;
    private Long loanNewTypeId;
    private Double maximumApproval;
    private Double maximumAllocation;
    private Double minimumApproval;
    private Double minimumAllocation;
    private Double minimumMonthlyInstallment;
    private Integer maxPeriode;
    private Integer maksimumHariTersedia;
    private Boolean isActive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLoanNewSchemaId() {
        return loanNewSchemaId;
    }

    public void setLoanNewSchemaId(Long loanNewSchemaId) {
        this.loanNewSchemaId = loanNewSchemaId;
    }

    public Long getLoanNewTypeId() {
        return loanNewTypeId;
    }

    public void setLoanNewTypeId(Long loanNewTypeId) {
        this.loanNewTypeId = loanNewTypeId;
    }

    public Double getMaximumApproval() {
        return maximumApproval;
    }

    public void setMaximumApproval(Double maximumApproval) {
        this.maximumApproval = maximumApproval;
    }

    public Double getMaximumAllocation() {
        return maximumAllocation;
    }

    public void setMaximumAllocation(Double maximumAllocation) {
        this.maximumAllocation = maximumAllocation;
    }

    public Double getMinimumApproval() {
        return minimumApproval;
    }

    public void setMinimumApproval(Double minimumApproval) {
        this.minimumApproval = minimumApproval;
    }

    public Double getMinimumAllocation() {
        return minimumAllocation;
    }

    public void setMinimumAllocation(Double minimumAllocation) {
        this.minimumAllocation = minimumAllocation;
    }

    public Double getMinimumMonthlyInstallment() {
        return minimumMonthlyInstallment;
    }

    public void setMinimumMonthlyInstallment(Double minimumMonthlyInstallment) {
        this.minimumMonthlyInstallment = minimumMonthlyInstallment;
    }

    public Integer getMaxPeriode() {
        return maxPeriode;
    }

    public void setMaxPeriode(Integer maxPeriode) {
        this.maxPeriode = maxPeriode;
    }

    public Integer getMaksimumHariTersedia() {
        return maksimumHariTersedia;
    }

    public void setMaksimumHariTersedia(Integer maksimumHariTersedia) {
        this.maksimumHariTersedia = maksimumHariTersedia;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    
}
