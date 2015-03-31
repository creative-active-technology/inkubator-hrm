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
public class PaySalaryGradeModel implements Serializable{
    private Long id;
    private Long currencyid;
    private Integer gradeSalary;
    private String gradeSalaryRomanov;
    private BigDecimal minSalary;
    private BigDecimal mediumSalary;
    private BigDecimal maxSalary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCurrencyid() {
        return currencyid;
    }

    public void setCurrencyid(Long currencyid) {
        this.currencyid = currencyid;
    }


    public Integer getGradeSalary() {
        return gradeSalary;
    }

    public void setGradeSalary(Integer gradeSalary) {
        this.gradeSalary = gradeSalary;
    }

    public BigDecimal getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(BigDecimal minSalary) {
        this.minSalary = minSalary;
    }

    public BigDecimal getMediumSalary() {
        return mediumSalary;
    }

    public void setMediumSalary(BigDecimal mediumSalary) {
        this.mediumSalary = mediumSalary;
    }

    public BigDecimal getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(BigDecimal maxSalary) {
        this.maxSalary = maxSalary;
    }

    public String getGradeSalaryRomanov() {
        return gradeSalaryRomanov;
    }

    public void setGradeSalaryRomanov(String gradeSalaryRomanov) {
        this.gradeSalaryRomanov = gradeSalaryRomanov;
    }
}
