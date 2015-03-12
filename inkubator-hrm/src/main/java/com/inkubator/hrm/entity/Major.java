package com.inkubator.hrm.entity;
// Generated Jun 17, 2014 6:48:25 AM by Hibernate Tools 3.6.0

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

/**
 * Major generated by hbm2java
 */
@Entity
@Table(name = "major", catalog="hrm_payroll", uniqueConstraints = @UniqueConstraint(columnNames = "major_name")
)
public class Major implements java.io.Serializable {

    private long id;
    private Integer version;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;
    private String majorName;
    private String description;
    private Set<BioEducationHistory> educationHistorys = new HashSet<BioEducationHistory>(0);
    private List<Major> listMajors = new ArrayList<>(0);

    public Major() {
    }

    public Major(long id) {
        this.id = id;
    }

    public Major(long id, String createdBy, Date createdOn, String updatedBy, Date updatedOn, String majorName, String description, Set<BioEducationHistory> educationHistorys) {
        this.id = id;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.updatedBy = updatedBy;
        this.updatedOn = updatedOn;
        this.majorName = majorName;
        this.description = description;
        this.educationHistorys = educationHistorys;
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

    @Column(name = "major_name", unique = true, length = 60)
    public String getMajorName() {
        return this.majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    @Column(name = "description", length = 65535, columnDefinition="Text")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "major")
    public Set<BioEducationHistory> getEducationHistorys() {
        return educationHistorys;
    }

    public void setEducationHistorys(Set<BioEducationHistory> educationHistorys) {
        this.educationHistorys = educationHistorys;
    }

    @Transient
    public List<Major> getListMajors() {
        return listMajors;
    }

    public void setListMajors(List<Major> listMajors) {
        this.listMajors = listMajors;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 47 * hash + Objects.hashCode(this.version);
        hash = 47 * hash + Objects.hashCode(this.createdBy);
        hash = 47 * hash + Objects.hashCode(this.createdOn);
        hash = 47 * hash + Objects.hashCode(this.updatedBy);
        hash = 47 * hash + Objects.hashCode(this.updatedOn);
        hash = 47 * hash + Objects.hashCode(this.majorName);
        hash = 47 * hash + Objects.hashCode(this.description);
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
        final Major other = (Major) obj;
        if (this.id != other.id) {
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
        if (!Objects.equals(this.updatedBy, other.updatedBy)) {
            return false;
        }
        if (!Objects.equals(this.updatedOn, other.updatedOn)) {
            return false;
        }
        if (!Objects.equals(this.majorName, other.majorName)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }

    
}
