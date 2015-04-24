package com.inkubator.hrm.web.search;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class LoanNewApplicationBoxSearchParameter extends SearchParameter {

    private String userId;
    private String empNik;
    private String empName;
    private String loanTypeName;
    private String loanNumber;
    
    

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmpName() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "empName")) {
            empName = getParameter();
        } else {
            empName = null;
        }
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpNik() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "empNik")) {
            empNik = getParameter();
        } else {
            empNik = null;
        }
        return empNik;
    }

    public void setEmpNik(String empNik) {
        this.empNik = empNik;
    }

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

    public String getLoanNumber() {
         if (StringUtils.equalsIgnoreCase(getKeyParam(), "loanNumber")) {
            loanNumber = getParameter();
        } else {
            loanNumber = null;
        }
        return loanNumber;
    }

    public void setLoanNumber(String loanNumber) {
        this.loanNumber = loanNumber;
    }
    
    

}
