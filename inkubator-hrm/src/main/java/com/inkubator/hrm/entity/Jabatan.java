package com.inkubator.hrm.entity;
// Generated Jun 17, 2014 4:36:40 PM by Hibernate Tools 3.6.0

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

/**
 * Jabatan generated by hbm2java
 */
@Entity
@Table(name = "jabatan", catalog = "hrm", uniqueConstraints = @UniqueConstraint(columnNames = "code")
)
public class Jabatan implements java.io.Serializable {

    private long id;
    private Integer version;
    private Jabatan jabatan;
    private CostCenter costCenter;
    private GolonganJabatan golonganJabatan;
    private Department department;
    private PaySalaryGrade paySalaryGrade;
    private UnitKerja unitKerja;
    private String code;
    private String name;
    private String tujuanJabatan;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;
    private Integer levelJabatan;
    private Integer totalPejabat;
    private Set<Jabatan> jabatans = new HashSet<>(0);
    private Set<JabatanDeskripsi> jabatanDeskripsis = new HashSet<>(0);
    private Set<JabatanSpesifikasi> jabatanSpesifikasis = new HashSet<>(0);
    private Set<KlasifikasiKerjaJabatan> klasifikasiKerjaJabatans = new HashSet<>(0);
    private List<KlasifikasiKerja> kerjaJabatans = new ArrayList<>();
    private Set<EmpRotasi> empRotasisForNewFunctionId = new HashSet<>(0);
    private Set<EmpRotasi> empRotasisForOldFunctionId = new HashSet<>(0);
    private Set<JabatanFakulty> jabatanFakulties = new HashSet<>(0);
    private Set<JabatanProfesi> jabatanProfesis = new HashSet<>(0);
    private Set<JabatanMajor> jabatanMajors = new HashSet<>(0);
    private Set<JabatanEdukasi> jabatanEdukasis = new HashSet<>(0);

    public Jabatan() {
    }

    public Jabatan(long id) {
        this.id = id;
    }

    public Jabatan(long id, Jabatan jabatan, CostCenter costCenter, GolonganJabatan golonganJabatan, Department department, UnitKerja unitKerja, String code, String name, String tujuanJabatan, String createdBy, Date createdOn, String updatedBy, Date updatedOn, Integer levelJabatan, Set<Jabatan> jabatans, Set<JabatanDeskripsi> jabatanDeskripsis, Set<JabatanSpesifikasi> jabatanSpesifikasis) {
        this.id = id;
        this.jabatan = jabatan;
        this.costCenter = costCenter;
        this.golonganJabatan = golonganJabatan;
        this.department = department;
        this.unitKerja = unitKerja;
        this.code = code;
        this.name = name;
        this.tujuanJabatan = tujuanJabatan;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.updatedBy = updatedBy;
        this.updatedOn = updatedOn;
        this.levelJabatan = levelJabatan;
        this.jabatans = jabatans;
        this.jabatanDeskripsis = jabatanDeskripsis;
        this.jabatanSpesifikasis = jabatanSpesifikasis;
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
    @JoinColumn(name = "parent_id")
    public Jabatan getJabatan() {
        return this.jabatan;
    }

    public void setJabatan(Jabatan jabatan) {
        this.jabatan = jabatan;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cost_center_id")
    public CostCenter getCostCenter() {
        return this.costCenter;
    }

    public void setCostCenter(CostCenter costCenter) {
        this.costCenter = costCenter;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gol_jabatan_id")
    public GolonganJabatan getGolonganJabatan() {
        return this.golonganJabatan;
    }

    public void setGolonganJabatan(GolonganJabatan golonganJabatan) {
        this.golonganJabatan = golonganJabatan;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departement_id")
    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_kerja_id")
    public UnitKerja getUnitKerja() {
        return this.unitKerja;
    }

    public void setUnitKerja(UnitKerja unitKerja) {
        this.unitKerja = unitKerja;
    }

    @Column(name = "code", unique = true, length = 12)
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "name", length = 45)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "tujuan_jabatan", length = 65535, columnDefinition = "Text")
    public String getTujuanJabatan() {
        return this.tujuanJabatan;
    }

    public void setTujuanJabatan(String tujuanJabatan) {
        this.tujuanJabatan = tujuanJabatan;
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

    @Column(name = "level_jabatan")
    public Integer getLevelJabatan() {
        return this.levelJabatan;
    }

    public void setLevelJabatan(Integer levelJabatan) {
        this.levelJabatan = levelJabatan;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "jabatan")
    public Set<Jabatan> getJabatans() {
        return this.jabatans;
    }

    public void setJabatans(Set<Jabatan> jabatans) {
        this.jabatans = jabatans;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "jabatan")
    public Set<JabatanDeskripsi> getJabatanDeskripsis() {
        return this.jabatanDeskripsis;
    }

    public void setJabatanDeskripsis(Set<JabatanDeskripsi> jabatanDeskripsis) {
        this.jabatanDeskripsis = jabatanDeskripsis;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "jabatan")
    @OrderBy(value = "createdBy")
    public Set<JabatanSpesifikasi> getJabatanSpesifikasis() {
        return this.jabatanSpesifikasis;
    }

    public void setJabatanSpesifikasis(Set<JabatanSpesifikasi> jabatanSpesifikasis) {
        this.jabatanSpesifikasis = jabatanSpesifikasis;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "jabatan", orphanRemoval = true)
    public Set<KlasifikasiKerjaJabatan> getKlasifikasiKerjaJabatans() {
        return this.klasifikasiKerjaJabatans;
    }

    public void setKlasifikasiKerjaJabatans(Set<KlasifikasiKerjaJabatan> klasifikasiKerjaJabatans) {
        this.klasifikasiKerjaJabatans = klasifikasiKerjaJabatans;
    }

    @Transient
    public List<KlasifikasiKerja> getKerjaJabatans() {
        return kerjaJabatans;
    }

    public void setKerjaJabatans(List<KlasifikasiKerja> kerjaJabatans) {
        this.kerjaJabatans = kerjaJabatans;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "salary_grade_id")
    public PaySalaryGrade getPaySalaryGrade() {
        return this.paySalaryGrade;
    }

    public void setPaySalaryGrade(PaySalaryGrade paySalaryGrade) {
        this.paySalaryGrade = paySalaryGrade;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "jabatanByNewFunctionId")
    public Set<EmpRotasi> getEmpRotasisForNewFunctionId() {
        return this.empRotasisForNewFunctionId;
    }

    public void setEmpRotasisForNewFunctionId(Set<EmpRotasi> empRotasisForNewFunctionId) {
        this.empRotasisForNewFunctionId = empRotasisForNewFunctionId;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "jabatanByOldFunctionId")
    public Set<EmpRotasi> getEmpRotasisForOldFunctionId() {
        return this.empRotasisForOldFunctionId;
    }

    public void setEmpRotasisForOldFunctionId(Set<EmpRotasi> empRotasisForOldFunctionId) {
        this.empRotasisForOldFunctionId = empRotasisForOldFunctionId;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "jabatan")
    public Set<JabatanFakulty> getJabatanFakulties() {
        return this.jabatanFakulties;
    }

    public void setJabatanFakulties(Set<JabatanFakulty> jabatanFakulties) {
        this.jabatanFakulties = jabatanFakulties;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "jabatan")
    public Set<JabatanProfesi> getJabatanProfesis() {
        return this.jabatanProfesis;
    }

    public void setJabatanProfesis(Set<JabatanProfesi> jabatanProfesis) {
        this.jabatanProfesis = jabatanProfesis;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "jabatan")
    public Set<JabatanMajor> getJabatanMajors() {
        return this.jabatanMajors;
    }

    public void setJabatanMajors(Set<JabatanMajor> jabatanMajors) {
        this.jabatanMajors = jabatanMajors;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "jabatan")
    public Set<JabatanEdukasi> getJabatanEdukasis() {
        return this.jabatanEdukasis;
    }

    public void setJabatanEdukasis(Set<JabatanEdukasi> jabatanEdukasis) {
        this.jabatanEdukasis = jabatanEdukasis;
    }
    
    @Transient
    public Integer getTotalPejabat() {
		return totalPejabat;
	}

	public void setTotalPejabat(Integer totalPejabat) {
		this.totalPejabat = totalPejabat;
	}

	@Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 83 * hash + Objects.hashCode(this.version);
        hash = 83 * hash + Objects.hashCode(this.code);
        hash = 83 * hash + Objects.hashCode(this.name);
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
        final Jabatan other = (Jabatan) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.version, other.version)) {
            return false;
        }
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
        
        
    }

  

}
