package com.inkubator.hrm.web.search;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class LoanNewSearchParameter extends SearchParameter {

    private String nik;
    private String employee;
    private String loanType;

    public String getEmployee() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "employee")) {
            employee = getParameter();
        } else {
            employee = null;
        }
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getLoanType() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "loanType")) {
            loanType = getParameter();
        } else {
            loanType = null;
        }
        return loanType;      
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public String getNik() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "nik")) {
            nik = getParameter();
        } else {
            nik = null;
        }
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

}
