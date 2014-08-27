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
 * @author Deni Husni FR
 */
public class ApprovalDefinitionSearchParameter extends SearchParameter {

    private String approvalName;
    private String processName;
    private String approverPosition;
    private String approverIndividual;
    private String onBehalfApproverPosition;
    private String onBehaltAppriverIndividual;
    private String approverType;

    public String getApproverPosition() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "approverPosition")) {
            approverPosition = getParameter();
        } else {
            approverPosition = null;
        }
        return approverPosition;
    }

    public void setApproverPosition(String approverPosition) {
        this.approverPosition = approverPosition;
    }

    public String getApproverIndividual() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "approverIndividual")) {
            approverIndividual = getParameter();
        } else {
            approverIndividual = null;
        }
        return approverIndividual;
    }

    public void setApproverIndividual(String approverIndividual) {
        this.approverIndividual = approverIndividual;
    }

    public String getProcessName() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "processName")) {
            processName = getParameter();
        } else {
            processName = null;
        }
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getOnBehalfApproverPosition() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "onBehalfApproverPosition")) {
            onBehalfApproverPosition = getParameter();
        } else {
            onBehalfApproverPosition = null;
        }
        return onBehalfApproverPosition;
    }

    public void setOnBehalfApproverPosition(String onBehalfApproverPosition) {
        this.onBehalfApproverPosition = onBehalfApproverPosition;
    }

    public String getOnBehaltAppriverIndividual() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "onBehaltAppriverIndividual")) {
            onBehaltAppriverIndividual = getParameter();
        } else {
            onBehaltAppriverIndividual = null;
        }
        return onBehaltAppriverIndividual;
    }

    public void setOnBehaltAppriverIndividual(String onBehaltAppriverIndividual) {
        this.onBehaltAppriverIndividual = onBehaltAppriverIndividual;
    }

    public String getApproverType() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "approverType")) {
            approverType = getParameter();
        } else {
            approverType = null;
        }
        return approverType;
    }

    public void setApproverType(String approverType) {
        this.approverType = approverType;
    }

    public String getApprovalName() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "approvalName")) {
            approvalName = getParameter();
        } else {
            approvalName = null;
        }
        return approvalName;
    }

    public void setApprovalName(String approvalName) {
        this.approvalName = approvalName;
    }

}
