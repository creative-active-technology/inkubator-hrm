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
public class LeaveDistributionSearchParameter extends SearchParameter{

    private String empData;
    private String leave;
    private Double balance;
    private Double saldo;
    private Double credit;

    public String getEmpData() {
        return empData;
    }

    public void setEmpData(String empData) {
        this.empData = empData;
    }

    public String getLeave() {
        return leave;
    }

    public void setLeave(String leave) {
        this.leave = leave;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }
    
    
}
