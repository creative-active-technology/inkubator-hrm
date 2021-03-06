package com.inkubator.hrm.entity;
// Generated Jun 16, 2014 11:34:46 AM by Hibernate Tools 3.6.0

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

/**
 * Department generated by hbm2java
 */
@Entity
@Table(name = "department", catalog = "hrm", uniqueConstraints = @UniqueConstraint(columnNames = "department_code")
)
public class Department implements java.io.Serializable {

    private static final long serialVersionUID = 5004048901073633038L;

    private long id;
    private Integer version;
    private String departmentCode;
    private String departmentName;
    private Company company;
    private CostCenterDept costCenterDept;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;
    private String description;
    private Set<Jabatan> jabatans = new HashSet<>(0);
    private Set<DepartementUploadCapture> departementUploadCaptures = new HashSet<>(0);
    private List<Department> listDepartments = new ArrayList<>(0);
    private Department department;
    private String orgLevel;
    private Boolean isNeckHierarki;
    private Boolean isActive;
    private Set<Department> departments = new HashSet<>(0);
    private Set<DepartementUnitLocation> departementUnitLocations = new HashSet<>(0);
    private List<UnitKerja> listUnit = new ArrayList<>(0);

    public Department() {
    }

    public Department(long id) {
        this.id = id;
    }

    public Department(long id, String departmentCode, String departmentName, String createdBy, Date createdOn, String updatedBy, Date updatedOn, String description) {
        this.id = id;
        this.departmentCode = departmentCode;
        this.departmentName = departmentName;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.updatedBy = updatedBy;
        this.updatedOn = updatedOn;
        this.description = description;
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
    @JoinColumn(name = "company_id")
    public Company getCompany() {
        return this.company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Column(name = "department_code", unique = true, length = 20)
    public String getDepartmentCode() {
        return this.departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    @Column(name = "department_name", length = 45)
    public String getDepartmentName() {
        return this.departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cost_center_id", nullable = false)
    public CostCenterDept getCostCenterDept() {
        return costCenterDept;
    }

    public void setCostCenterDept(CostCenterDept costCenterDept) {
        this.costCenterDept = costCenterDept;
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

    @Column(name = "description", length = 65535, columnDefinition = "Text")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_on", length = 19)
    public Date getUpdatedOn() {
        return this.updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
    public Set<Jabatan> getJabatans() {
        return this.jabatans;
    }

    public void setJabatans(Set<Jabatan> jabatans) {
        this.jabatans = jabatans;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
    public Set<DepartementUploadCapture> getDepartementUploadCaptures() {
        return this.departementUploadCaptures;
    }

    public void setDepartementUploadCaptures(Set<DepartementUploadCapture> departementUploadCaptures) {
        this.departementUploadCaptures = departementUploadCaptures;
    }

    @Transient
    public List<Department> getListDepartments() {
        return listDepartments;
    }

    public void setListDepartments(List<Department> listDepartments) {
        this.listDepartments = listDepartments;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 19 * hash + Objects.hashCode(this.version);
        hash = 19 * hash + Objects.hashCode(this.departmentCode);
        hash = 19 * hash + Objects.hashCode(this.departmentName);
        hash = 19 * hash + Objects.hashCode(this.createdBy);
        hash = 19 * hash + Objects.hashCode(this.createdOn);
        hash = 19 * hash + Objects.hashCode(this.updatedBy);
        hash = 19 * hash + Objects.hashCode(this.updatedOn);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() instanceof Object) {
        } else {
            return false;
        }
        final Department other = (Department) obj;
        if (this.id != other.getId()) {
            return false;
        }
        if (!Objects.equals(this.version, other.getVersion())) {
            return false;
        }
        if (!Objects.equals(this.departmentCode, other.getDepartmentCode())) {
            return false;
        }
        if (!Objects.equals(this.departmentName, other.getDepartmentName())) {
            return false;
        }
        if (!Objects.equals(this.createdBy, other.getCreatedBy())) {
            return false;
        }
        if (!Objects.equals(this.createdOn, other.getCreatedOn())) {
            return false;
        }
        if (!Objects.equals(this.updatedBy, other.getUpdatedBy())) {
            return false;
        }
        if (!Objects.equals(this.updatedOn, other.getUpdatedOn())) {
            return false;
        }
        return true;
    }

//    }
    @Override
    public String toString() {
        return "Department{" + "id=" + id + ", version=" + version + ", departmentCode=" + departmentCode + ", departmentName=" + departmentName + ", createdBy=" + createdBy + ", createdOn=" + createdOn + ", updatedBy=" + updatedBy + ", updatedOn=" + updatedOn + '}';
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Column(name = "org_level", length = 45)
    public String getOrgLevel() {
        return this.orgLevel;
    }

    public void setOrgLevel(String orgLevel) {
        this.orgLevel = orgLevel;
    }

    @Column(name = "is_neck_hierarki")
    public Boolean getIsNeckHierarki() {
        return this.isNeckHierarki;
    }

    public void setIsNeckHierarki(Boolean isNeckHierarki) {
        this.isNeckHierarki = isNeckHierarki;
    }

    @Column(name = "is_active")
    public Boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
    public Set<Department> getDepartments() {
        return this.departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "department", orphanRemoval = true)
    public Set<DepartementUnitLocation> getDepartementUnitLocations() {
        return this.departementUnitLocations;
    }

    public void setDepartementUnitLocations(Set<DepartementUnitLocation> departementUnitLocations) {
        this.departementUnitLocations = departementUnitLocations;
    }

    @Transient
    public List<UnitKerja> getListUnit() {
        return listUnit;
    }

    public void setListUnit(List<UnitKerja> listUnit) {
        this.listUnit = listUnit;
    }

}
