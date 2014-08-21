/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author Deni Husni FR
 */
public class ApprovalDefinitionModel implements Serializable {

    private Long id;
    private long hrmUserByOnBehalfIndividualId;
    private String hrmUserByOnBehalfIndividualName;
    private long jabatanByApproverPositionId;
    private String jabatanByApproverPositionName;
    private long hrmUserByApproverIndividualId;
    private String hrmUserByApproverIndividualName;
    private long jabatanByOnBehalfPositionId;
    private String jabatanByOnBehalfPositionName;
    private String name;
    private Integer sequence=1;
    private Integer minApprover=1;
    private Integer minRejector=1;
    private String processType;
    private String approverType;
    private Boolean allowOnBehalf;
    private String onBehalfType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getHrmUserByOnBehalfIndividualId() {
        return hrmUserByOnBehalfIndividualId;
    }

    public void setHrmUserByOnBehalfIndividualId(long hrmUserByOnBehalfIndividualId) {
        this.hrmUserByOnBehalfIndividualId = hrmUserByOnBehalfIndividualId;
    }

    public long getJabatanByApproverPositionId() {
        return jabatanByApproverPositionId;
    }

    public void setJabatanByApproverPositionId(long jabatanByApproverPositionId) {
        this.jabatanByApproverPositionId = jabatanByApproverPositionId;
    }

    public long getHrmUserByApproverIndividualId() {
        return hrmUserByApproverIndividualId;
    }

    public void setHrmUserByApproverIndividualId(long hrmUserByApproverIndividualId) {
        this.hrmUserByApproverIndividualId = hrmUserByApproverIndividualId;
    }

    public long getJabatanByOnBehalfPositionId() {
        return jabatanByOnBehalfPositionId;
    }

    public void setJabatanByOnBehalfPositionId(long jabatanByOnBehalfPositionId) {
        this.jabatanByOnBehalfPositionId = jabatanByOnBehalfPositionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Integer getMinApprover() {
        return minApprover;
    }

    public void setMinApprover(Integer minApprover) {
        this.minApprover = minApprover;
    }

    public Integer getMinRejector() {
        return minRejector;
    }

    public void setMinRejector(Integer minRejector) {
        this.minRejector = minRejector;
    }

    public String getProcessType() {
        return processType;
    }

    public void setProcessType(String processType) {
        this.processType = processType;
    }

    public String getApproverType() {
        return approverType;
    }

    public void setApproverType(String approverType) {
        this.approverType = approverType;
    }

    public Boolean getAllowOnBehalf() {
        return allowOnBehalf;
    }

    public void setAllowOnBehalf(Boolean allowOnBehalf) {
        this.allowOnBehalf = allowOnBehalf;
    }

    public String getOnBehalfType() {
        return onBehalfType;
    }

    public void setOnBehalfType(String onBehalfType) {
        this.onBehalfType = onBehalfType;
    }

    public String getHrmUserByOnBehalfIndividualName() {
        return hrmUserByOnBehalfIndividualName;
    }

    public void setHrmUserByOnBehalfIndividualName(String hrmUserByOnBehalfIndividualName) {
        this.hrmUserByOnBehalfIndividualName = hrmUserByOnBehalfIndividualName;
    }

    public String getJabatanByApproverPositionName() {
        return jabatanByApproverPositionName;
    }

    public void setJabatanByApproverPositionName(String jabatanByApproverPositionName) {
        this.jabatanByApproverPositionName = jabatanByApproverPositionName;
    }

    public String getHrmUserByApproverIndividualName() {
        return hrmUserByApproverIndividualName;
    }

    public void setHrmUserByApproverIndividualName(String hrmUserByApproverIndividualName) {
        this.hrmUserByApproverIndividualName = hrmUserByApproverIndividualName;
    }

    public String getJabatanByOnBehalfPositionName() {
        return jabatanByOnBehalfPositionName;
    }

    public void setJabatanByOnBehalfPositionName(String jabatanByOnBehalfPositionName) {
        this.jabatanByOnBehalfPositionName = jabatanByOnBehalfPositionName;
    }
    
    

}
