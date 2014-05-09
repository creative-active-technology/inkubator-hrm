/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author Deni Husni FR
 */
public class ApprovalDefinitionSearchParameter extends SearchParameter {

    private String processName;
    private String approverPosition;
    private String approverIndividual;
    private String onBehalfApproverPosition;
    private String onBehaltAppriverIndividual;

    public String getApproverPosition() {
        return approverPosition;
    }

    public void setApproverPosition(String approverPosition) {
        this.approverPosition = approverPosition;
    }

    public String getApproverIndividual() {
        return approverIndividual;
    }

    public void setApproverIndividual(String approverIndividual) {
        this.approverIndividual = approverIndividual;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getOnBehalfApproverPosition() {
        return onBehalfApproverPosition;
    }

    public void setOnBehalfApproverPosition(String onBehalfApproverPosition) {
        this.onBehalfApproverPosition = onBehalfApproverPosition;
    }

    public String getOnBehaltAppriverIndividual() {
        return onBehaltAppriverIndividual;
    }

    public void setOnBehaltAppriverIndividual(String onBehaltAppriverIndividual) {
        this.onBehaltAppriverIndividual = onBehaltAppriverIndividual;
    }

   

}
