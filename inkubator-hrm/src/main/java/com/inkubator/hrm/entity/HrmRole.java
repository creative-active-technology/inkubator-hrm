package com.inkubator.hrm.entity;
// Generated May 9, 2014 9:50:44 AM by Hibernate Tools 3.6.0

import java.util.Date;
import java.util.HashSet;
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
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

/**
 * HrmRole generated by hbm2java
 */
@Entity
@Table(name = "hrm_role", catalog = "hrm", uniqueConstraints = @UniqueConstraint(columnNames = "role_name")
)
public class HrmRole implements java.io.Serializable {

    private long id;
    private Integer version;
    private String roleName;
    private String description;
    private String createdBy;
    private String updatedBy;
    private Date createdOn;
    private Date updatedOn;
    private Set<HrmUserRole> hrmUserRoles = new HashSet<>(0);

    public HrmRole() {
    }

    public HrmRole(long id) {
        this.id = id;
    }

    public HrmRole(long id, String roleName, String description, String createdBy, String updatedBy, Date createdOn, Date updatedOn, Set<HrmUserRole> hrmUserRoles) {
        this.id = id;
        this.roleName = roleName;
        this.description = description;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.hrmUserRoles = hrmUserRoles;
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

    @Column(name = "role_name", unique = true, length = 45)
    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Column(name = "description", length = 65535)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hrmRole")
    public Set<HrmUserRole> getHrmUserRoles() {
        return this.hrmUserRoles;
    }

    public void setHrmUserRoles(Set<HrmUserRole> hrmUserRoles) {
        this.hrmUserRoles = hrmUserRoles;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 29 * hash + Objects.hashCode(this.version);
        hash = 29 * hash + Objects.hashCode(this.roleName);
        hash = 29 * hash + Objects.hashCode(this.description);
        hash = 29 * hash + Objects.hashCode(this.createdBy);
        hash = 29 * hash + Objects.hashCode(this.updatedBy);
        hash = 29 * hash + Objects.hashCode(this.createdOn);
        hash = 29 * hash + Objects.hashCode(this.updatedOn);
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
        final HrmRole other = (HrmRole) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.version, other.version)) {
            return false;
        }
        if (!Objects.equals(this.roleName, other.roleName)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
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