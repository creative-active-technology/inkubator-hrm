/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.entity;

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
 *
 * @author Deni
 */
@Entity
@Table(name = "approval_definition_over_time", catalog = "hrm"
)
public class ApprovalDefinitionOT implements java.io.Serializable{
    private ApprovalDefinitionOTId id;
    private WtOverTime wtOverTime;
    private ApprovalDefinition approvalDefinition;
    private String description;

    public ApprovalDefinitionOT() {
    }

    public ApprovalDefinitionOT(ApprovalDefinitionOTId id, WtOverTime wtOverTime, ApprovalDefinition approvalDefinition) {
        this.id = id;
        this.wtOverTime = wtOverTime;
        this.approvalDefinition = approvalDefinition;
    }

    public ApprovalDefinitionOT(ApprovalDefinitionOTId id, WtOverTime wtOverTime, ApprovalDefinition approvalDefinition, String description) {
        this.id = id;
        this.wtOverTime = wtOverTime;
        this.approvalDefinition = approvalDefinition;
        this.description = description;
    }

    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "overTimeId", column = @Column(name = "over_time_id", nullable = false)),
        @AttributeOverride(name = "approvalDefinitionId", column = @Column(name = "approval_definition_id", nullable = false))})
    public ApprovalDefinitionOTId getId() {
        return id;
    }

    public void setId(ApprovalDefinitionOTId id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "over_time_id", nullable = false, insertable = false, updatable = false)
    public WtOverTime getWtOverTime() {
        return wtOverTime;
    }

    public void setWtOverTime(WtOverTime wtOverTime) {
        this.wtOverTime = wtOverTime;
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
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
