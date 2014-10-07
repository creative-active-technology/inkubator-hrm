/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Deni
 */
@Embeddable
public class ApprovalDefinitionOTId implements java.io.Serializable {

    private long overTimeId;
    private long approvalDefinitionId;

    public ApprovalDefinitionOTId() {
    }

    public ApprovalDefinitionOTId(long overTimeId, long approvalDefinitionId) {
        this.overTimeId = overTimeId;
        this.approvalDefinitionId = approvalDefinitionId;
    }

    @Column(name = "over_time_id", nullable = false)
    public long getOverTimeId() {
        return overTimeId;
    }

    public void setOverTimeId(long overTimeId) {
        this.overTimeId = overTimeId;
    }

    @Column(name = "approval_definition_id", nullable = false)
    public long getApprovalDefinitionId() {
        return approvalDefinitionId;
    }

    public void setApprovalDefinitionId(long approvalDefinitionId) {
        this.approvalDefinitionId = approvalDefinitionId;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + (int) (this.overTimeId ^ (this.overTimeId >>> 32));
        hash = 19 * hash + (int) (this.approvalDefinitionId ^ (this.approvalDefinitionId >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ApprovalDefinitionOTId other = (ApprovalDefinitionOTId) obj;
        if (this.overTimeId != other.overTimeId) {
            return false;
        }
        if (this.approvalDefinitionId != other.approvalDefinitionId) {
            return false;
        }
        return true;
    }
}
