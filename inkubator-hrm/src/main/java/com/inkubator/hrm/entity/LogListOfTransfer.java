package com.inkubator.hrm.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name="log_list_of_transfer", catalog="hrm"
)
public class LogListOfTransfer implements Serializable {

	private Long id;
    private Integer version;
    
    private Long periodeId;
    private Long empDataId;
    private String empNik;
    private String empName;
    private String bankName;
    private String accountNumber;
    private String accountName;
    private BigDecimal transferNominal;
    private Double transferPercent;
    private Date payrollDate;
    
    private String createdBy;
    private Date createdOn;
    
    public LogListOfTransfer(){
    	
    }
    
    public LogListOfTransfer(Long id){
    	this.id = id;
    }
    
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "log_list_of_transfer_seq_gen")
    @SequenceGenerator(name = "log_list_of_transfer_seq_gen", sequenceName = "LOG_LIST_OF_TRANSFER_SEQ")
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
    
    @Column(name = "periode_id", nullable = false)
    public Long getPeriodeId() {
		return periodeId;
	}

	public void setPeriodeId(Long periodeId) {
		this.periodeId = periodeId;
	}

	@Column(name = "emp_data_id", nullable = false)
	public Long getEmpDataId() {
		return empDataId;
	}

	public void setEmpDataId(Long empDataId) {
		this.empDataId = empDataId;
	}

	@Column(name = "emp_nik", length = 45, nullable = true)
	public String getEmpNik() {
		return empNik;
	}

	public void setEmpNik(String empNik) {
		this.empNik = empNik;
	}
	
	@Column(name = "emp_name", length = 200, nullable = false)
	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	@Column(name="bank_name", length=60, nullable=false)
	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	@Column(name="account_number", length=60, nullable=true)
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Column(name="account_name", length=60, nullable=false)
	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	@Column(name = "tansfer_nominal", precision = 10, scale = 0, nullable=false)
	public BigDecimal getTransferNominal() {
		return transferNominal;
	}

	public void setTransferNominal(BigDecimal transferNominal) {
		this.transferNominal = transferNominal;
	}

	@Column(name="transfer_percent", precision=10, scale=0, nullable=false)
	public Double getTransferPercent() {
		return transferPercent;
	}

	public void setTransferPercent(Double transferPercent) {
		this.transferPercent = transferPercent;
	}

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "payroll_date", length = 19)
	public Date getPayrollDate() {
		return payrollDate;
	}

	public void setPayrollDate(Date payrollDate) {
		this.payrollDate = payrollDate;
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
