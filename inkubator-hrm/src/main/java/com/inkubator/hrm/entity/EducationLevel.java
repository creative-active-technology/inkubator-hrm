package com.inkubator.hrm.entity;

import java.io.Serializable;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

@Entity
@Table(name = "education_level", catalog = "hrm")
public class EducationLevel implements Serializable {

    private Long id;
    private Integer version;
    private String name;
    private Integer level;
    private String createdBy;
    private String updatedBy;
    private Date createdOn;
    private Date updatedOn;
    private Set<BioEducationHistory> educationHistorys = new HashSet<BioEducationHistory>(0);
    private Set<BioFamilyRelationship> bioFamilyRelationships = new HashSet<BioFamilyRelationship>(0);
    private Set<JabatanEdukasi> jabatanEdukasis = new HashSet<JabatanEdukasi>(0);
    private List<EducationLevel> listEducationLevels = new ArrayList<>(0);

    public EducationLevel() {

    }

    public EducationLevel(Long id) {
        this.id = id;
    }

    public EducationLevel(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    @Column(name = "id", unique = true, nullable = false)
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

    @Column(name = "name", unique = true, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "level", nullable = false)
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Column(name = "created_by", length = 45)
    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Column(name = "updated_by", length = 45)
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on", length = 19)
    public Date getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_on", length = 19)
    public Date getUpdatedOn() {
        return this.updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "educationLevel")
    public Set<BioEducationHistory> getEducationHistorys() {
        return educationHistorys;
    }

    public void setEducationHistorys(Set<BioEducationHistory> educationHistorys) {
        this.educationHistorys = educationHistorys;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "educationLevel")
    public Set<BioFamilyRelationship> getBioFamilyRelationships() {
        return bioFamilyRelationships;
    }

    public void setBioFamilyRelationships(Set<BioFamilyRelationship> bioFamilyRelationships) {
        this.bioFamilyRelationships = bioFamilyRelationships;
    }

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="educationLevel")
    public Set<JabatanEdukasi> getJabatanEdukasis() {
        return jabatanEdukasis;
    }

    public void setJabatanEdukasis(Set<JabatanEdukasi> jabatanEdukasis) {
        this.jabatanEdukasis = jabatanEdukasis;
    }

    @Transient
    public List<EducationLevel> getListEducationLevels() {
        return listEducationLevels;
    }

    public void setListEducationLevels(List<EducationLevel> listEducationLevels) {
        this.listEducationLevels = listEducationLevels;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.version);
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + Objects.hashCode(this.level);
        hash = 53 * hash + Objects.hashCode(this.createdBy);
        hash = 53 * hash + Objects.hashCode(this.updatedBy);
        hash = 53 * hash + Objects.hashCode(this.createdOn);
        hash = 53 * hash + Objects.hashCode(this.updatedOn);
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
        final EducationLevel other = (EducationLevel) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.version, other.version)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.level, other.level)) {
            return false;
        }
        if (!Objects.equals(this.createdBy, other.createdBy)) {
            return false;
        }
        if (!Objects.equals(this.updatedBy, other.updatedBy)) {
            return false;
        }
        if (!Objects.equals(this.createdOn, other.createdOn)) {
            return false;
        }
        if (!Objects.equals(this.updatedOn, other.updatedOn)) {
            return false;
        }
        return true;
    }

    
    
    

    
    

}
