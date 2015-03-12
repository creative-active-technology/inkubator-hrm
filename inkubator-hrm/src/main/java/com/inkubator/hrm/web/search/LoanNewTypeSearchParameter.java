/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Deni
 */
public class LoanNewTypeSearchParameter extends SearchParameter {

    private String loanTypeName;
    private String loanTypeCode;

    public String getLoanTypeName() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "loanTypeName")) {
            loanTypeName = getParameter();
        } else {
            loanTypeName = null;
        }
        return loanTypeName;
    }

    public void setLoanTypeName(String loanTypeName) {
        this.loanTypeName = loanTypeName;
    }

    public String getLoanTypeCode() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "loanTypeCode")) {
            loanTypeCode = getParameter();
        } else {
            loanTypeCode = null;
        }
        return loanTypeCode;
    }

    public void setLoanTypeCode(String loanTypeCode) {
        this.loanTypeCode = loanTypeCode;
    }

}
