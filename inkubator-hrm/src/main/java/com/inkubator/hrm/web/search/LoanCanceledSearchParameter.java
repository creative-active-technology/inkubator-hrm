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
 * @author deni
 */
public class LoanCanceledSearchParameter extends SearchParameter {

    private String name;
    private String nim;
    private String employee;
    private String loanSchema;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

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

    public String getLoanSchema() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "loanSchema")) {
            loanSchema = getParameter();
        } else {
            loanSchema = null;
        }
        return loanSchema;
    }

    public void setLoanSchema(String loanSchema) {
        this.loanSchema = loanSchema;
    }

}
