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
public class LoanNewTypeModel implements Serializable {

    private Long id;
    private String loanTypeCode;
    private String loanTypeName;
    private Integer interestMethod;
    private String currencyName;
    private Long currencyCode;
    private Double interest;
    private Integer roundingStatus;
    private String description;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(Long currencyCode) {
        this.currencyCode = currencyCode;
    }
    
    
    public String getLoanTypeCode() {
        return loanTypeCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }
    
    

    public void setLoanTypeCode(String loanTypeCode) {
        this.loanTypeCode = loanTypeCode;
    }

    public String getLoanTypeName() {
        return loanTypeName;
    }

    public void setLoanTypeName(String loanTypeName) {
        this.loanTypeName = loanTypeName;
    }

    public Integer getInterestMethod() {
        return interestMethod;
    }

    public void setInterestMethod(Integer interestMethod) {
        this.interestMethod = interestMethod;
    }

    public Double getInterest() {
        return interest;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }

    public Integer getRoundingStatus() {
        return roundingStatus;
    }

    public void setRoundingStatus(Integer roundingStatus) {
        this.roundingStatus = roundingStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    

}
