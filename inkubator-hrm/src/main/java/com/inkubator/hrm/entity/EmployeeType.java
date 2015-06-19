package com.inkubator.hrm.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
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
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

@Entity
@Table(name = "employee_type", catalog="hrm_payroll_backup", uniqueConstraints = @UniqueConstraint(columnNames = "name")
)
public class EmployeeType implements java.io.Serializable {

    private Long id;
    private Integer version;
    private String createdBy;
    private Date createdOn;
    private String name;
    private String updatedBy;
    private Date updatedOn;
    private Set<ReimbursmentSchemaEmployeeType> reimbursmentSchemaEmployeeTypes = new HashSet<ReimbursmentSchemaEmployeeType>(0);
    private Set<EmpData> empDatas = new HashSet<EmpData>(0);
    private Set<PaySalaryEmpType> paySalaryEmpTypes = new HashSet<PaySalaryEmpType>(0);
    private List<EmployeeType> listEmployeeTypes = new ArrayList<>(0);
    
    public EmployeeType() {
    }

    public EmployeeType(Long id) {
        this.id = id;
    }

    public EmployeeType(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public EmployeeType(Long id, String createdBy, Date createdOn, String name, String updatedBy, Date updatedOn) {
        this.id = id;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.name = name;
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

    @Column(name = "name", unique = true, nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employeeType")
    public Set<ReimbursmentSchemaEmployeeType> getReimbursmentSchemaEmployeeTypes() {
        return reimbursmentSchemaEmployeeTypes;
    }

    public void setReimbursmentSchemaEmployeeTypes(Set<ReimbursmentSchemaEmployeeType> reimbursmentSchemaEmployeeTypes) {
        this.reimbursmentSchemaEmployeeTypes = reimbursmentSchemaEmployeeTypes;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employeeType")
    public Set<EmpData> getEmpDatas() {
        return empDatas;
    }

    public void setEmpDatas(Set<EmpData> empDatas) {
        this.empDatas = empDatas;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "paySalaryComponent")
    public Set<PaySalaryEmpType> getPaySalaryEmpTypes() {
        return paySalaryEmpTypes;
    }

    public void setPaySalaryEmpTypes(Set<PaySalaryEmpType> paySalaryEmpTypes) {
        this.paySalaryEmpTypes = paySalaryEmpTypes;
    }

    @Transient
    public List<EmployeeType> getListEmployeeTypes() {
        return listEmployeeTypes;
    }

    public void setListEmployeeTypes(List<EmployeeType> listEmployeeTypes) {
        this.listEmployeeTypes = listEmployeeTypes;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.version);
        hash = 37 * hash + Objects.hashCode(this.createdBy);
        hash = 37 * hash + Objects.hashCode(this.createdOn);
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + Objects.hashCode(this.updatedBy);
        hash = 37 * hash + Objects.hashCode(this.updatedOn);
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
        final EmployeeType other = (EmployeeType) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.version, other.version)) {
            return false;
        }
        if (!Objects.equals(this.createdBy, other.createdBy)) {
            return false;
        }
        if (!Objects.equals(this.createdOn, other.createdOn)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.updatedBy, other.updatedBy)) {
            return false;
        }
        if (!Objects.equals(this.updatedOn, other.updatedOn)) {
            return false;
        }
        return true;
    }

}
