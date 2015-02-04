package com.inkubator.hrm.entity;
// Generated Jun 16, 2014 8:44:01 PM by Hibernate Tools 3.6.0

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * HrmMenuRole generated by hbm2java
 */
@Entity
@Table(name = "approval_definition_leave", catalog = "hrm_personalia"
)
public class ApprovalDefinitionLeave implements java.io.Serializable {

    private ApprovalDefinitionLeaveId id;
    private Leave leave;
    private ApprovalDefinition approvalDefinition;
    private String description;

    public ApprovalDefinitionLeave() {
    }

    public ApprovalDefinitionLeave(ApprovalDefinitionLeaveId id, ApprovalDefinition approvalDefinition, Leave leave) {
        this.id = id;
        this.leave = leave;
        this.approvalDefinition = approvalDefinition;
    }

    public ApprovalDefinitionLeave(ApprovalDefinitionLeaveId id, ApprovalDefinition approvalDefinition, Leave leave, String description) {
    	this.id = id;
        this.leave = leave;
        this.approvalDefinition = approvalDefinition;
        this.description = description;
    }

    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "leaveId", column = @Column(name = "leave_id", nullable = false)),
        @AttributeOverride(name = "approvalDefinitionId", column = @Column(name = "approval_definition_id", nullable = false))})
    public ApprovalDefinitionLeaveId getId() {
        return this.id;
    }

    public void setId(ApprovalDefinitionLeaveId id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leave_id", nullable = false, insertable = false, updatable = false)
    public Leave getLeave() {
		return leave;
	}

	public void setLeave(Leave leave) {
		this.leave = leave;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approval_definition_id", nullable = false, insertable = false, updatable = false)
	public ApprovalDefinition getApprovalDefinition() {
		return approvalDefinition;
	}

	public void setApprovalDefinition(ApprovalDefinition approvalDefinition) {
		this.approvalDefinition = approvalDefinition;
	}

    @Column(name = "description", length = 45)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
