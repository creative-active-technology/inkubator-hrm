/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Deni
 */
public class LoanSchemaModel implements Serializable{
    private Long id;
    private String code;
    private String name;
    private Long costCenter;
    private Integer typeOfInterest;
    private Integer maxPeriode;
    private Integer basicValue;
    private BigDecimal maxNominal;
    private BigDecimal minPaymment;
    private Double interestRate;;
    private Double maxPaymentOfSalary;
    private Double penaltyOfNonComplance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(Long costCenter) {
        this.costCenter = costCenter;
    }

    public Integer getTypeOfInterest() {
        return typeOfInterest;
    }

    public void setTypeOfInterest(Integer typeOfInterest) {
        this.typeOfInterest = typeOfInterest;
    }

    public Integer getMaxPeriode() {
        return maxPeriode;
    }

    public void setMaxPeriode(Integer maxPeriode) {
        this.maxPeriode = maxPeriode;
    }

    public Integer getBasicValue() {
        return basicValue;
    }

    public void setBasicValue(Integer basicValue) {
        this.basicValue = basicValue;
    }

    public BigDecimal getMaxNominal() {
        return maxNominal;
    }

    public void setMaxNominal(BigDecimal maxNominal) {
        this.maxNominal = maxNominal;
    }

    public Double getMaxPaymentOfSalary() {
        return maxPaymentOfSalary;
    }

    public void setMaxPaymentOfSalary(Double maxPaymentOfSalary) {
        this.maxPaymentOfSalary = maxPaymentOfSalary;
    }

    public Double getPenaltyOfNonComplance() {
        return penaltyOfNonComplance;
    }

    public void setPenaltyOfNonComplance(Double penaltyOfNonComplance) {
        this.penaltyOfNonComplance = penaltyOfNonComplance;
    }

    public BigDecimal getMinPaymment() {
        return minPaymment;
    }

    public void setMinPaymment(BigDecimal minPaymment) {
        this.minPaymment = minPaymment;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }
}
