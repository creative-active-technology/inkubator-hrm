/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.entity;

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Deni
 */
@Entity
@Table(name="leave_distribution"
    ,catalog="hrm"
)
public class LeaveDistribution implements java.io.Serializable{
     private LeaveDistributionId id;
     private EmpData empData;
     private Leave leave;
     private Double balance;
     private Double saldo;
     private Double credit;
     private Date startDate;
     private Date endDate;

    public LeaveDistribution() {
    }

    public LeaveDistribution(LeaveDistributionId id, EmpData empData, Leave leave) {
        this.id = id;
        this.empData = empData;
        this.leave = leave;
    }

    public LeaveDistribution(LeaveDistributionId id, EmpData empData, Leave leave, Double balance, Double saldo, Double credit, Date startDate, Date endDate) {
        this.id = id;
        this.empData = empData;
        this.leave = leave;
        this.balance = balance;
        this.saldo = saldo;
        this.credit = credit;
        this.startDate = startDate;
        this.endDate = endDate;
    }

     @EmbeddedId
     
     @AttributeOverrides( {
        @AttributeOverride(name="empDataId", column=@Column(name="emp_data_id", nullable=false) ), 
        @AttributeOverride(name="leaveId", column=@Column(name="leave_id", nullable=false) ) } )
    public LeaveDistributionId getId() {
        return id;
    }

    public void setId(LeaveDistributionId id) {
        this.id = id;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="emp_data_id", nullable=false, insertable=false, updatable=false)
    public EmpData getEmpData() {
        return empData;
    }

    public void setEmpData(EmpData empData) {
        this.empData = empData;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="leave_id", nullable=false, insertable=false, updatable=false)
    public Leave getLeave() {
        return leave;
    }

    public void setLeave(Leave leave) {
        this.leave = leave;
    }

    @Column(name="balance", nullable = false)
    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Column(name="saldo", nullable = false)
    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    @Column(name="credit", nullable = false)
    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_date", length = 19)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_date", length = 19)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
     
     
}
