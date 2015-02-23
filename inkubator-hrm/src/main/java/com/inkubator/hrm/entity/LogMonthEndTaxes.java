package com.inkubator.hrm.entity;

import java.io.Serializable;
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
@Table(name="log_month_end_taxes", catalog="hrm"
)
public class LogMonthEndTaxes implements Serializable {

	private Long id;
    private Integer version;
    
    private Long periodeId;
    private Date periodeStart;
    private Date periodeEnd;
    private Long empDataId;
    private String empNik;
    private String empName;
    private Long empJabatanId;
    private String empJabatanCode;
    private String empJabatanName;
    private String empGolJabatan;
    private Long departmentId;
    private String departmentName;
    private String empStatus;
    private Long taxCompId;
    private String taxCompName;
    private Double nominal;
    private String createdBy;
    private Date createdOn;
    
    public LogMonthEndTaxes(){
    	
    }
    
    public LogMonthEndTaxes(Long id){
    	this.id = id;
    }
    
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "log_month_end_taxes_seq_gen")
    @SequenceGenerator(name = "log_month_end_taxes_seq_gen", sequenceName = "LOG_MONTH_END_TAXES_SEQ")
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

	@Temporal(TemporalType.DATE)
    @Column(name = "periode_start", length = 10, nullable = false)
	public Date getPeriodeStart() {
		return periodeStart;
	}

	public void setPeriodeStart(Date periodeStart) {
		this.periodeStart = periodeStart;
	}

	@Temporal(TemporalType.DATE)
    @Column(name = "periode_end", length = 10, nullable = false)
	public Date getPeriodeEnd() {
		return periodeEnd;
	}

	public void setPeriodeEnd(Date periodeEnd) {
		this.periodeEnd = periodeEnd;
	}

	@Column(name = "emp_data_id", nullable = false)
	public Long getEmpDataId() {
		return empDataId;
	}

	public void setEmpDataId(Long empDataId) {
		this.empDataId = empDataId;
	}

	@Column(name = "emp_nik", length = 45, nullable = false)
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
	
	@Column(name = "emp_jabatan_id", nullable = false)
	public Long getEmpJabatanId() {
		return empJabatanId;
	}

	public void setEmpJabatanId(Long empJabatanId) {
		this.empJabatanId = empJabatanId;
	}
	
	@Column(name = "emp_jabatan_code", length = 12, nullable = false)
	public String getEmpJabatanCode() {
		return empJabatanCode;
	}

	public void setEmpJabatanCode(String empJabatanCode) {
		this.empJabatanCode = empJabatanCode;
	}

	@Column(name = "emp_jabatan_name", length = 45, nullable = false)
	public String getEmpJabatanName() {
		return empJabatanName;
	}

	public void setEmpJabatanName(String empJabatanName) {
		this.empJabatanName = empJabatanName;
	}

	@Column(name="emp_gol_jabatan", length=4, nullable=false)
	public String getEmpGolJabatan() {
		return empGolJabatan;
	}

	public void setEmpGolJabatan(String empGolJabatan) {
		this.empGolJabatan = empGolJabatan;
	}

	@Column(name = "department_id", nullable = false)
	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	@Column(name="department_name", length=60, nullable=false)
	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	@Column(name = "emp_status", nullable = false)
	public String getEmpStatus() {
		return empStatus;
	}

	public void setEmpStatus(String empStatus) {
		this.empStatus = empStatus;
	}		
	
	@Column(name = "tax_comp_id", nullable = false)
	public Long getTaxCompId() {
		return taxCompId;
	}

	public void setTaxCompId(Long taxCompId) {
		this.taxCompId = taxCompId;
	}

	@Column(name="tax_comp_name", nullable=false, length=255)
	public String getTaxCompName() {
		return taxCompName;
	}

	public void setTaxCompName(String taxCompName) {
		this.taxCompName = taxCompName;
	}

	@Column(name="nominal", precision=22, scale=0, nullable = false)
    public Double getNominal() {
        return this.nominal;
    }
    
    public void setNominal(Double nominal) {
        this.nominal = nominal;
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
