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
    
    
}
