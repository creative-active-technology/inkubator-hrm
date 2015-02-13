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
@Table(name = "religion", catalog = "hrm", uniqueConstraints = @UniqueConstraint(columnNames = "name")
)
public class Religion implements java.io.Serializable {

    private long id;
    private Integer version;
    private String createdBy;
    private Date createdOn;
    private String name;
    private String updatedBy;
    private Date updatedOn;
    private Set<WtHoliday> wtHolidays = new HashSet<WtHoliday>(0);
    private Set<BioData> bioDatas = new HashSet<BioData>(0);
    private List<Religion> listReligion = new ArrayList<>(0);
    private String code;
    private String description;

    public Religion() {
    }

    public Religion(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Religion(long id) {
        this.id = id;
    }

    public Religion(long id, String createdBy, Date createdOn, String name, String updatedBy, Date updatedOn, Set<WtHoliday> wtHolidays, String code, String description) {
        this.id = id;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.name = name;
        this.updatedBy = updatedBy;
        this.updatedOn = updatedOn;
        this.wtHolidays = wtHolidays;
        this.code = code;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "religion")
    public Set<WtHoliday> getWtHolidays() {
        return this.wtHolidays;
    }

    public void setWtHolidays(Set<WtHoliday> wtHolidays) {
        this.wtHolidays = wtHolidays;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "religion")
    public Set<BioData> getBioDatas() {
        return this.bioDatas;
    }

    public void setBioDatas(Set<BioData> bioDatas) {
        this.bioDatas = bioDatas;
    }

    @Transient
    public List<Religion> getListReligion() {
        return listReligion;
    }

    public void setListReligion(List<Religion> listReligion) {
        this.listReligion = listReligion;
    }
    
    @Column(name = "code", unique = true, nullable = false)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "description", length = 225)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 41 * hash + Objects.hashCode(this.version);
        hash = 41 * hash + Objects.hashCode(this.createdBy);
        hash = 41 * hash + Objects.hashCode(this.createdOn);
        hash = 41 * hash + Objects.hashCode(this.name);
        hash = 41 * hash + Objects.hashCode(this.updatedBy);
        hash = 41 * hash + Objects.hashCode(this.updatedOn);
        hash = 41 * hash + Objects.hashCode(this.code);
        hash = 41 * hash + Objects.hashCode(this.description);
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
        final Religion other = (Religion) obj;
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
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.updatedBy, other.updatedBy)) {
            return false;
        }
        if (!Objects.equals(this.updatedOn, other.updatedOn)) {
            return false;
        }
        if (!Objects.equals(this.updatedOn, other.code)) {
            return false;
        }
        if (!Objects.equals(this.updatedOn, other.description)) {
            return false;
        }
        return true;
    }

}
