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
public class LeaveDistributionId implements java.io.Serializable{
     private long empDataId;
     private long leaveId;

    public LeaveDistributionId() {
    }

    public LeaveDistributionId(long empDataId, long leaveId) {
        this.empDataId = empDataId;
        this.leaveId = leaveId;
    }

    @Column(name="emp_data_id", nullable=false)
    public long getEmpDataId() {
        return empDataId;
    }

    public void setEmpDataId(long empDataId) {
        this.empDataId = empDataId;
    }

    @Column(name="leave_id", nullable=false)
    public long getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(long leaveId) {
        this.leaveId = leaveId;
    }

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (int) (this.empDataId ^ (this.empDataId >>> 32));
        hash = 97 * hash + (int) (this.leaveId ^ (this.leaveId >>> 32));
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
        final LeaveDistributionId other = (LeaveDistributionId) obj;
        if (this.empDataId != other.empDataId) {
            return false;
        }
        if (this.leaveId != other.leaveId) {
            return false;
        }
        return true;
    }
     
     
}
