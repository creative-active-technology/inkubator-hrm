package com.inkubator.hrm.entity;
// Generated Sep 30, 2014 12:22:58 PM by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

/**
 * NeracaCuti generated by hbm2java
 */
@Entity
@Table(name="neraca_cuti"
    ,catalog="hrm_payroll_backup"
)
public class NeracaCuti  implements java.io.Serializable {


     private long id;
     private Integer version;
     private LeaveDistribution leaveDistribution;
     private Double debet;
     private Double kredit;
     private Double saldo;
     private Date createdOn;
     private String createdBy;
     private Date updatedOn;
     private String updatedBy;

    public NeracaCuti() {
    }

	
    public NeracaCuti(long id) {
        this.id = id;
    }
    public NeracaCuti(long id, LeaveDistribution leaveDistribution, Double debet, Double kredit, Date createdOn, String createdBy, Date updatedOn, String updatedBy) {
       this.id = id;
       this.leaveDistribution = leaveDistribution;
       this.debet = debet;
       this.kredit = kredit;
       this.createdOn = createdOn;
       this.createdBy = createdBy;
       this.updatedOn = updatedOn;
       this.updatedBy = updatedBy;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }

    @Version
    @Column(name="version")
    public Integer getVersion() {
        return this.version;
    }
    
    public void setVersion(Integer version) {
        this.version = version;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="leave_distribusi_id")
    public LeaveDistribution getLeaveDistribution() {
        return this.leaveDistribution;
    }
    
    public void setLeaveDistribution(LeaveDistribution leaveDistribution) {
        this.leaveDistribution = leaveDistribution;
    }

    
    @Column(name="debet", precision=22, scale=0)
    public Double getDebet() {
        return this.debet;
    }
    
    public void setDebet(Double debet) {
        this.debet = debet;
    }

    @Column(name="kredit", precision=22, scale=0)
    public Double getKredit() {
        return this.kredit;
    }
    
    public void setKredit(Double kredit) {
        this.kredit = kredit;
    }
    
    @Column(name="saldo", precision=22, scale=0)
    public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_on", length=19)
    public Date getCreatedOn() {
        return this.createdOn;
    }
    
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    
    @Column(name="created_by", length=45)
    public String getCreatedBy() {
        return this.createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_on", length=19)
    public Date getUpdatedOn() {
        return this.updatedOn;
    }
    
    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    
    @Column(name="updated_by", length=45)
    public String getUpdatedBy() {
        return this.updatedBy;
    }
    
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

}


