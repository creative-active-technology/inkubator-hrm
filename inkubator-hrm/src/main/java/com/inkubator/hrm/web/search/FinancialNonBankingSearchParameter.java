/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author Deni
 */
public class FinancialNonBankingSearchParameter extends SearchParameter{
    private String financialService;
    private String code;
    private String name;
    private String address;

    public String getFinancialService() {
        return financialService;
    }

    public void setFinancialService(String financialService) {
        this.financialService = financialService;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    
}
