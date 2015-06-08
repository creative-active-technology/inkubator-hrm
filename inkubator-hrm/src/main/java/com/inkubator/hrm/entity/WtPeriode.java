package com.inkubator.hrm.entity;
// Generated May 21, 2014 4:59:40 PM by Hibernate Tools 3.6.0

import com.inkubator.hrm.HRMConstant;
import com.inkubator.webcore.util.FacesUtil;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;
import org.joda.time.DateTime;

/**
 * WtPeriode generated by hbm2java
 */
@Entity
@Table(name = "wt_periode", catalog="hrm_payroll"
)
public class WtPeriode implements java.io.Serializable {

    private long id;
    private Integer version;
    private String tahun;
    private Integer bulan;
    private Date fromPeriode;
    private Date untilPeriode;
    private String absen;
    private Integer workingDays;
    private String payrollType;
    private Date payrollDate;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;
    private Set<TempAttendanceRealization> tempAttendanceRealizations = new HashSet<TempAttendanceRealization>(0);

    public WtPeriode() {
    }

    public WtPeriode(long id) {
        this.id = id;
    }

    public WtPeriode(long id, String tahun, Integer bulan, Date fromPeriode, Date untilPeriode, String absen, String payrollType, String createdBy, Date createdOn, String updatedBy, Date updatedOn) {
        this.id = id;
        this.tahun = tahun;
        this.bulan = bulan;
        this.fromPeriode = fromPeriode;
        this.untilPeriode = untilPeriode;
        this.absen = absen;
        this.payrollType = payrollType;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.updatedBy = updatedBy;
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

    @Column(name = "tahun", length = 10)
    public String getTahun() {
        return this.tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    @Column(name = "bulan", length = 20)
    public Integer getBulan() {
        return this.bulan;
    }

    public void setBulan(Integer bulan) {
        this.bulan = bulan;
    }

    @Transient
    public String getBulanAsLabel() {
//        String language= FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE);
       String monthName = DateTime.now().withMonthOfYear(bulan).toString("MMMM", Locale.forLanguageTag((String) FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE)));
       return monthName;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "from_periode", length = 10)
    public Date getFromPeriode() {
        return this.fromPeriode;
    }

    public void setFromPeriode(Date fromPeriode) {
        this.fromPeriode = fromPeriode;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "until_periode", length = 10)
    public Date getUntilPeriode() {
        return this.untilPeriode;
    }

    public void setUntilPeriode(Date untilPeriode) {
        this.untilPeriode = untilPeriode;
    }

    @Column(name = "absen", length = 20)
    public String getAbsen() {
        return this.absen;
    }

    public void setAbsen(String absen) {
        this.absen = absen;
    }
    
    @Column(name = "working_days", length = 4)
    public Integer getWorkingDays() {
        return this.workingDays;
    }

    public void setWorkingDays(Integer workingDays) {
        this.workingDays = workingDays;
    }

    @Column(name = "payroll_type", length = 20)
    public String getPayrollType() {
        return this.payrollType;
    }

    public void setPayrollType(String payrollType) {
        this.payrollType = payrollType;
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

    @Column(name = "updated_by", length = 45)
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_on", length = 19)
    public Date getUpdatedOn() {
        return this.updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="wtPeriod")
    public Set<TempAttendanceRealization> getTempAttendanceRealizations() {
        return this.tempAttendanceRealizations;
    }
    
    public void setTempAttendanceRealizations(Set<TempAttendanceRealization> tempAttendanceRealizations) {
        this.tempAttendanceRealizations = tempAttendanceRealizations;
    }

}
