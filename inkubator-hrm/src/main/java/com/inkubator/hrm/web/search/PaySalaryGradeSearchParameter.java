/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;
import java.math.BigDecimal;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Deni
 */
public class PaySalaryGradeSearchParameter extends SearchParameter{
    private Integer gradeSalary;
    private String currency;
    private BigDecimal minSalary;
    private BigDecimal medSalary;
    private BigDecimal maxSalary;

    public Integer getGradeSalary() {
        if (getKeyParam() != null) {
            if (getKeyParam().equalsIgnoreCase("gradeSalary")&& getParameter()!=null) {
                gradeSalary = Integer.parseInt(getParameter());
            }
        }
        return gradeSalary;
    }

    public void setGradeSalary(Integer gradeSalary) {
        this.gradeSalary = gradeSalary;
    }

    public String getCurrency() {
        if(StringUtils.equalsIgnoreCase(getKeyParam(), "currency")){
			currency = getParameter();
		} else {
			currency = null;
		}
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getMinSalary() {
        if (getKeyParam() != null) {
            if (getKeyParam().equalsIgnoreCase("minSalary")&& getParameter()!=null) {
                minSalary = new BigDecimal(getParameter());
            }
        }
        return minSalary;
    }

    public void setMinSalary(BigDecimal minSalary) {
        this.minSalary = minSalary;
    }

    public BigDecimal getMedSalary() {
        if (getKeyParam() != null) {
            if (getKeyParam().equalsIgnoreCase("mediumSalary")&& getParameter()!=null) {
                medSalary = new BigDecimal(getParameter());
            }
        }
        return medSalary;
    }

    public void setMedSalary(BigDecimal medSalary) {
        this.medSalary = medSalary;
    }

    public BigDecimal getMaxSalary() {
        if (getKeyParam() != null) {
            if (getKeyParam().equalsIgnoreCase("maxSalary")&& getParameter()!=null) {
                maxSalary = new BigDecimal(getParameter());
            }
        }
        return maxSalary;
    }

    public void setMaxSalary(BigDecimal maxSalary) {
        this.maxSalary = maxSalary;
    }
    
    
}
