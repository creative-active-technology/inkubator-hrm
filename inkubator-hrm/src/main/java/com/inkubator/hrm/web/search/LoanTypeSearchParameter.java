package com.inkubator.hrm.web.search;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author Taufik Hidayat
 */
public class LoanTypeSearchParameter extends SearchParameter {

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
