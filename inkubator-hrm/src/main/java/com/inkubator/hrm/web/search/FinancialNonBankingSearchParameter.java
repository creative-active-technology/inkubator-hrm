/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Deni
 */
public class FinancialNonBankingSearchParameter extends SearchParameter{
    private String financialService;
    private String code;
    private String name;

    public String getFinancialService() {
        if(StringUtils.equalsIgnoreCase(getKeyParam(), "financialService")){
            financialService = getParameter();
        } else {
            financialService = null;
        }
        return financialService;
    }

    public void setFinancialService(String financialService) {
        this.financialService = financialService;
    }

    public String getCode() {
        if(StringUtils.equalsIgnoreCase(getKeyParam(), "code")){
            code = getParameter();
        } else {
            code = null;
        }
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        if(StringUtils.equalsIgnoreCase(getKeyParam(), "name")){
            name = getParameter();
        } else {
            name = null;
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    
}
