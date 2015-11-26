package com.inkubator.hrm.entity;
// Generated Aug 26, 2014 11:40:45 AM by Hibernate Tools 3.6.0

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
 * EmpCareerHistory generated by hbm2java
 */
@Entity
@Table(name = "emp_career_history", catalog = "hrm"
)
public class EmpCareerHistory implements java.io.Serializable {

    private long id;
    private Integer version;
    private Jabatan jabatan;
    private GolonganJabatan golonganJabatan;
    private BioData bioData;
//    private BigDecimal salary;
    private String salary;
    private String nik;
    private String noSk;
    private Date tglPenganngkatan;
    private String createdBy;
    private Date createdOn;
    private String updateBy;
    private Date updatedOn;
    private String status;
    private String approvalActivityNumber;
    private String salaryChangesType;
    private Double salaryChangesPercent;
    private String notes;
    private EmpData copyOfLetterTo;
    private EmployeeType employeeType;
     private String jabatanOldName;
     private String jabatanOldCode;

    public EmpCareerHistory() {
    }

    public EmpCareerHistory(long id) {
        this.id = id;
    }

    public EmpCareerHistory(long id, Jabatan jabatan, GolonganJabatan golonganJabatan, BioData bioData, String salary, String nik, String noSk, Date tglPenganngkatan, String createdBy, Date createdOn, String updateBy, Date updatedOn) {
        this.id = id;
        this.jabatan = jabatan;
        this.golonganJabatan = golonganJabatan;
        this.bioData = bioData;
        this.salary = salary;
        this.nik = nik;
        this.noSk = noSk;
        this.tglPenganngkatan = tglPenganngkatan;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.updateBy = updateBy;
        this.updatedOn = updatedOn;
    }

    @Id
    @Column(name = "id", unique = true, nullable = false)
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
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
    @JoinColumn(name = "jabatan_id")
    public Jabatan getJabatan() {
        return this.jabatan;
    }

    public void setJabatan(Jabatan jabatan) {
        this.jabatan = jabatan;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gol_jab_id")
    public GolonganJabatan getGolonganJabatan() {
        return this.golonganJabatan;
    }

    public void setGolonganJabatan(GolonganJabatan golonganJabatan) {
        this.golonganJabatan = golonganJabatan;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "biodata_id")
    public BioData getBioData() {
        return this.bioData;
    }

    public void setBioData(BioData bioData) {
        this.bioData = bioData;
    }

//    @Column(name = "salary", precision = 10, scale = 0)
//    public BigDecimal getSalary() {
//        return this.salary;
//    }
//
//    public void setSalary(BigDecimal salary) {
//        this.salary = salary;
//    }
    @Column(name = "NIK", length = 45)
    public String getNik() {
        return this.nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    @Column(name = "no_sk", length = 45)
    public String getNoSk() {
        return this.noSk;
    }

    public void setNoSk(String noSk) {
        this.noSk = noSk;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "tgl_penganngkatan", length = 19)
    public Date getTglPenganngkatan() {
        return this.tglPenganngkatan;
    }

    public void setTglPenganngkatan(Date tglPenganngkatan) {
        this.tglPenganngkatan = tglPenganngkatan;
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

    @Column(name = "update_by", length = 45)
    public String getUpdateBy() {
        return this.updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_on", length = 19)
    public Date getUpdatedOn() {
        return this.updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    @Column(name = "status", length = 45)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    @Column(name = "approval_activity_number", length = 45, unique = true)
    public String getApprovalActivityNumber() {
        return approvalActivityNumber;
    }

    public void setApprovalActivityNumber(String approvalActivityNumber) {
        this.approvalActivityNumber = approvalActivityNumber;
    }
    
    @Column(name = "salary_changes_type")
    public String getSalaryChangesType() {
		return salaryChangesType;
	}

	public void setSalaryChangesType(String salaryChangesType) {
		this.salaryChangesType = salaryChangesType;
	}

	@Column(name = "salary_changes_percent")
	public Double getSalaryChangesPercent() {
		return salaryChangesPercent;
	}

	public void setSalaryChangesPercent(Double salaryChangesPercent) {
		this.salaryChangesPercent = salaryChangesPercent;
	}
	
	@Column(name = "notes", length = 65535, columnDefinition = "Text")
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "copy_of_letter_to")
	public EmpData getCopyOfLetterTo() {
		return copyOfLetterTo;
	}

	public void setCopyOfLetterTo(EmpData copyOfLetterTo) {
		this.copyOfLetterTo = copyOfLetterTo;
	}

	@Column(name = "salary", length = 65535, columnDefinition="Text")
    public String getSalary() {
        return this.salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }   

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "karyawan_type_id")
    public EmployeeType getEmployeeType() {
        return this.employeeType;
    }
    
    @Transient
    public String getJabatanOldName() {
        return jabatanOldName;
    }

    public void setJabatanOldName(String jabatanOldName) {
        this.jabatanOldName = jabatanOldName;
    }
    
    @Transient
    public String getJabatanOldCode() {
        return jabatanOldCode;
    }

    public void setJabatanOldCode(String jabatanOldCode) {
        this.jabatanOldCode = jabatanOldCode;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }
}
