package com.inkubator.hrm.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 *
 * @author rizkykojek
 */
@Entity
@Table(name="log_salary_journal", catalog="hrm_payroll_backup"
)
public class LogSalaryJournal implements Serializable {

	private Long id;
    private Integer version;
    
    private WtPeriode wtPeriode;
    private Long journalId;
    private String journalCode;
    private String journalName;
    private Long costCenterId;
    private String costCenterCode;
    private String costCenterName;
    private Double debit;
    private Double credit;
    
    private String createdBy;
    private Date createdOn;
    
    public LogSalaryJournal(){
    	
    }
    
    public LogSalaryJournal(Long id){
    	this.id = id;
    }
    
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "log_salary_journal_seq_gen")
    @SequenceGenerator(name = "log_salary_journal_seq_gen", sequenceName = "LOG_SALARY_JOURNAL_SEQ")
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Version
    @Column(name = "version")
    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "periode_id", nullable = false)
	public WtPeriode getWtPeriode() {
		return wtPeriode;
	}

	public void setWtPeriode(WtPeriode wtPeriode) {
		this.wtPeriode = wtPeriode;
	}

	@Column(name = "journal_id")
	public Long getJournalId() {
		return journalId;
	}

	public void setJournalId(Long journalId) {
		this.journalId = journalId;
	}

	@Column(name = "journal_code", length = 8, nullable = false)
	public String getJournalCode() {
		return journalCode;
	}

	public void setJournalCode(String journalCode) {
		this.journalCode = journalCode;
	}

	@Column(name = "journal_name", length = 60, nullable = false)
	public String getJournalName() {
		return journalName;
	}

	public void setJournalName(String journalName) {
		this.journalName = journalName;
	}
	
	@Column(name = "cost_center_id")
	public Long getCostCenterId() {
		return costCenterId;
	}

	public void setCostCenterId(Long costCenterId) {
		this.costCenterId = costCenterId;
	}

	@Column(name = "cost_center_code", length = 6, nullable = false)
	public String getCostCenterCode() {
		return costCenterCode;
	}

	public void setCostCenterCode(String costCenterCode) {
		this.costCenterCode = costCenterCode;
	}
	
	@Column(name = "cost_center_name", length = 100, nullable = false)
	public String getCostCenterName() {
		return costCenterName;
	}

	public void setCostCenterName(String costCenterName) {
		this.costCenterName = costCenterName;
	}
	
	@Column(name = "debit", nullable = false)
	public Double getDebit() {
		return debit;
	}

	public void setDebit(Double debit) {
		this.debit = debit;
	}

	@Column(name = "credit", nullable = false)
	public Double getCredit() {
		return credit;
	}

	public void setCredit(Double credit) {
		this.credit = credit;
	}

	@Column(name = "created_by", length = 45)
    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on", length = 19)
    public Date getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
