package com.inkubator.hrm.web.search;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class LoanNewCancelationBoxSearchParameter extends SearchParameter {

	private String userId;
    private String codeApplication;
    private String codeCancelation;
    private String loanNewTypeName;
    private String empName;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCodeApplication() {
		if (StringUtils.equalsIgnoreCase(getKeyParam(), "codeApplication")) {
			codeApplication = getParameter();
        } else {
        	codeApplication = null;
        }
		return codeApplication;
	}

	public void setCodeApplication(String codeApplication) {
		this.codeApplication = codeApplication;
	}

	public String getCodeCancelation() {
		if (StringUtils.equalsIgnoreCase(getKeyParam(), "codeCancelation")) {
			codeCancelation = getParameter();
        } else {
        	codeCancelation = null;
        }
		return codeCancelation;
	}

	public void setCodeCancelation(String codeCancelation) {
		this.codeCancelation = codeCancelation;
	}

    public String getLoanNewTypeName() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "loanNewTypeName")) {
			loanNewTypeName = getParameter();
        } else {
        	loanNewTypeName = null;
        }
        return loanNewTypeName;
    }

    public void setLoanNewTypeName(String loanNewTypeName) {
        this.loanNewTypeName = loanNewTypeName;
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

	

	
    
}
