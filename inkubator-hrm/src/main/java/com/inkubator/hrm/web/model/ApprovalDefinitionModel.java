/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;

import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.Jabatan;

/**
 *
 * @author Deni Husni FR
 */
public class ApprovalDefinitionModel implements Serializable {

    private Long id;
    
    private HrmUser hrmUserByApproverIndividual;
    private Long hrmUserByApproverIndividualId;
    private String hrmUserByApproverIndividualName;
    
    private HrmUser hrmUserByOnBehalfIndividual;
    private Long hrmUserByOnBehalfIndividualId;
    private String hrmUserByOnBehalfIndividualName;    
    
    private Jabatan jabatanByApproverPosition;
    private Long jabatanByApproverPositionId;
    private String jabatanByApproverPositionName;
    
    private Jabatan jabatanByOnBehalfPosition;
    private Long jabatanByOnBehalfPositionId;
    private String jabatanByOnBehalfPositionName;
    
    private String name;
    private String specificName;
    private Integer sequence = 1;
    private Integer minApprover;
    private Integer minRejector;
    private String processType;
    private String approverType;
    private Boolean allowOnBehalf;
    private String onBehalfType;
    private Boolean autoApproveOnDelay;
    private Boolean escalateOnDelay;
    private Integer delayTime=1;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHrmUserByOnBehalfIndividualId() {
        return hrmUserByOnBehalfIndividualId;
    }

    public void setHrmUserByOnBehalfIndividualId(Long hrmUserByOnBehalfIndividualId) {
        this.hrmUserByOnBehalfIndividualId = hrmUserByOnBehalfIndividualId;
    }

    public Long getJabatanByApproverPositionId() {
        return jabatanByApproverPositionId;
    }

    public void setJabatanByApproverPositionId(Long jabatanByApproverPositionId) {
        this.jabatanByApproverPositionId = jabatanByApproverPositionId;
    }

    public Long getHrmUserByApproverIndividualId() {
        return hrmUserByApproverIndividualId;
    }

    public void setHrmUserByApproverIndividualId(Long hrmUserByApproverIndividualId) {
        this.hrmUserByApproverIndividualId = hrmUserByApproverIndividualId;
    }

    public Long getJabatanByOnBehalfPositionId() {
        return jabatanByOnBehalfPositionId;
    }

    public void setJabatanByOnBehalfPositionId(Long jabatanByOnBehalfPositionId) {
        this.jabatanByOnBehalfPositionId = jabatanByOnBehalfPositionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getSpecificName() {
		return specificName;
	}

	public void setSpecificName(String specificName) {
		this.specificName = specificName;
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

    @Override
    public String toString() {
        return "ApprovalDefinitionModel{" + "id=" + id + ", hrmUserByOnBehalfIndividualId=" + hrmUserByOnBehalfIndividualId + ", hrmUserByOnBehalfIndividualName=" + hrmUserByOnBehalfIndividualName + ", jabatanByApproverPositionId=" + jabatanByApproverPositionId + ", jabatanByApproverPositionName=" + jabatanByApproverPositionName + ", hrmUserByApproverIndividualId=" + hrmUserByApproverIndividualId + ", hrmUserByApproverIndividualName=" + hrmUserByApproverIndividualName + ", jabatanByOnBehalfPositionId=" + jabatanByOnBehalfPositionId + ", jabatanByOnBehalfPositionName=" + jabatanByOnBehalfPositionName + ", name=" + name + ", sequence=" + sequence + ", minApprover=" + minApprover + ", minRejector=" + minRejector + ", processType=" + processType + ", approverType=" + approverType + ", allowOnBehalf=" + allowOnBehalf + ", onBehalfType=" + onBehalfType + '}';
    }

    public Boolean getAutoApproveOnDelay() {
        return autoApproveOnDelay;
    }

    public void setAutoApproveOnDelay(Boolean autoApproveOnDelay) {
        this.autoApproveOnDelay = autoApproveOnDelay;
    }

    public Boolean getEscalateOnDelay() {
        return escalateOnDelay;
    }

    public void setEscalateOnDelay(Boolean escalateOnDelay) {
        this.escalateOnDelay = escalateOnDelay;
    }

    public Integer getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(Integer delayTime) {
        this.delayTime = delayTime;
    }

	public HrmUser getHrmUserByApproverIndividual() {
		return hrmUserByApproverIndividual;
	}

	public void setHrmUserByApproverIndividual(HrmUser hrmUserByApproverIndividual) {
		this.hrmUserByApproverIndividual = hrmUserByApproverIndividual;
	}

	public HrmUser getHrmUserByOnBehalfIndividual() {
		return hrmUserByOnBehalfIndividual;
	}

	public void setHrmUserByOnBehalfIndividual(HrmUser hrmUserByOnBehalfIndividual) {
		this.hrmUserByOnBehalfIndividual = hrmUserByOnBehalfIndividual;
	}

	public Jabatan getJabatanByApproverPosition() {
		return jabatanByApproverPosition;
	}

	public void setJabatanByApproverPosition(Jabatan jabatanByApproverPosition) {
		this.jabatanByApproverPosition = jabatanByApproverPosition;
	}

	public Jabatan getJabatanByOnBehalfPosition() {
		return jabatanByOnBehalfPosition;
	}

	public void setJabatanByOnBehalfPosition(Jabatan jabatanByOnBehalfPosition) {
		this.jabatanByOnBehalfPosition = jabatanByOnBehalfPosition;
	}
    
    

    
}
