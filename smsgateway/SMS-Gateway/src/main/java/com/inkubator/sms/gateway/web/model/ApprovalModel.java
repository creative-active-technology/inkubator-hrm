/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.sms.gateway.web.model;

import java.io.Serializable;

/**
 *
 * @author Deni Husni FR
 */
public class ApprovalModel implements Serializable{
    private String approverNumberHp;
    private String approvalActivityNumber;
    private String approveCondition;
    private String comentar;

    public String getApproverNumberHp() {
        return approverNumberHp;
    }

    public void setApproverNumberHp(String approverNumberHp) {
        this.approverNumberHp = approverNumberHp;
    }

    public String getApprovalActivityNumber() {
        return approvalActivityNumber;
    }

    public void setApprovalActivityNumber(String approvalActivityNumber) {
        this.approvalActivityNumber = approvalActivityNumber;
    }

    public String getApproveCondition() {
        return approveCondition;
    }

    public void setApproveCondition(String approveCondition) {
        this.approveCondition = approveCondition;
    }

    public String getComentar() {
        return comentar;
    }

    public void setComentar(String comentar) {
        this.comentar = comentar;
    }

    @Override
    public String toString() {
        return "ApprovalModel{" + "approverNumberHp=" + approverNumberHp + ", approvalActivityNumber=" + approvalActivityNumber + ", approveCondition=" + approveCondition + ", comentar=" + comentar + '}';
    }
    
    
}
