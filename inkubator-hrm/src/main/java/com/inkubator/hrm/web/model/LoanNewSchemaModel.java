/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.List;

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
    private Double totalPinjaman;
    private Double totalAllocation;
    private Double totalInstallment;
    private Integer maksimumHariTersedia;
    private Integer maxPeriode;
    private Double minimumAllocation;
    private Double minimumApproval;
    private List<LoanNewSchemaModel> listTotalModel;
    private LoanNewSchemaModel totalModel;
    private Boolean isActive;

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

    public Double getTotalPinjaman() {
        return totalPinjaman;
    }

    public void setTotalPinjaman(Double totalPinjaman) {
        this.totalPinjaman = totalPinjaman;
    }

    public Double getTotalAllocation() {
        return totalAllocation;
    }

    public void setTotalAllocation(Double totalAllocation) {
        this.totalAllocation = totalAllocation;
    }

    public Double getTotalInstallment() {
        return totalInstallment;
    }

    public void setTotalInstallment(Double totalInstallment) {
        this.totalInstallment = totalInstallment;
    }

    public Integer getMaksimumHariTersedia() {
        return maksimumHariTersedia;
    }

    public void setMaksimumHariTersedia(Integer maksimumHariTersedia) {
        this.maksimumHariTersedia = maksimumHariTersedia;
    }

    public Integer getMaxPeriode() {
        return maxPeriode;
    }

    public void setMaxPeriode(Integer maxPeriode) {
        this.maxPeriode = maxPeriode;
    }

    public Double getMinimumAllocation() {
        return minimumAllocation;
    }

    public void setMinimumAllocation(Double minimumAllocation) {
        this.minimumAllocation = minimumAllocation;
    }

    public Double getMinimumApproval() {
        return minimumApproval;
    }

    public void setMinimumApproval(Double minimumApproval) {
        this.minimumApproval = minimumApproval;
    }

    public List<LoanNewSchemaModel> getListTotalModel() {
        return listTotalModel;
    }

    public void setListTotalModel(List<LoanNewSchemaModel> listTotalModel) {
        this.listTotalModel = listTotalModel;
    }

    public LoanNewSchemaModel getTotalModel() {
        return totalModel;
    }

    public void setTotalModel(LoanNewSchemaModel totalModel) {
        this.totalModel = totalModel;
    }

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

    
}
