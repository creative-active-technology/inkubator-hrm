package com.inkubator.hrm.web.model;

import java.io.Serializable;


/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class BankTransferDistributionReportModel implements Serializable {    
    private String  bankName;
    private Long totalAccountNumber;  

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Long getTotalAccountNumber() {
        return totalAccountNumber;
    }

    public void setTotalAccountNumber(Long totalAccountNumber) {
        this.totalAccountNumber = totalAccountNumber;
    }

   
    
}
