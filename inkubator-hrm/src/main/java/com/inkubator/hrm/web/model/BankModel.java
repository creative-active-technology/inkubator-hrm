package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author Taufik Hidayat
 */
public class BankModel implements Serializable {

    private Long id;
    private String bankCode;
    private String bankName;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
