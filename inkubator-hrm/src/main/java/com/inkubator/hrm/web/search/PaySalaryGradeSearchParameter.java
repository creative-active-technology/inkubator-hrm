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
            }else{
                gradeSalary=null;
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
        if(StringUtils.equalsIgnoreCase(getKeyParam(), "minSalary")){
            if(getParameter() != null && !getParameter().equals("")){
                minSalary = new BigDecimal(getParameter());
            }else{
                minSalary = null;
            }
        } else {
            minSalary = null;
        }
        return minSalary;
    }

    public void setMinSalary(BigDecimal minSalary) {
        this.minSalary = minSalary;
    }

    public BigDecimal getMedSalary() {
        if(StringUtils.equalsIgnoreCase(getKeyParam(), "mediumSalary")){
            if(getParameter() != null && !getParameter().equals("")){
                medSalary = new BigDecimal(getParameter());
            }else{
                medSalary = null;
            }
        } else {
            medSalary = null;
        }
        return medSalary;
    }

    public void setMedSalary(BigDecimal medSalary) {
        this.medSalary = medSalary;
    }

    public BigDecimal getMaxSalary() {
        if(StringUtils.equalsIgnoreCase(getKeyParam(), "maxSalary")){
            if(getParameter() != null && !getParameter().equals("")){
                maxSalary = new BigDecimal(getParameter());
            }else{
                maxSalary = null;
            }
        } else {
            maxSalary = null;
        }
        return maxSalary;
    }

    public void setMaxSalary(BigDecimal maxSalary) {
        this.maxSalary = maxSalary;
    }
    
    
}
