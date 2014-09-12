/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;
import java.math.BigDecimal;

/**
 *
 * @author Deni
 */
public class ReimbursmentSearchParameter extends SearchParameter{
    private String empData;
    private String code;
    private Integer quantity;
    private BigDecimal nominal;

    public String getEmpData() {
        return empData;
    }

    public void setEmpData(String empData) {
        this.empData = empData;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
    
    
}
