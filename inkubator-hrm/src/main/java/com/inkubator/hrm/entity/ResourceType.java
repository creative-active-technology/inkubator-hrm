/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

/**
 *
 * @author Deni
 */
@Entity
@Table(name = "resource_type", catalog = "hrm_personalia", uniqueConstraints = @UniqueConstraint(columnNames = "code")
)
public class ResourceType implements java.io.Serializable {
    private long id;
    private Integer version;
    private String code;
    private String resourceType;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;
    private Set<ResourceName> resourceNames = new HashSet<>(0);

    public ResourceType() {
    }

    public ResourceType(long id) {
        this.id = id;
    }
    
    public ResourceType(long id, Integer version, String code, String resourceType, String createdBy, Date createdOn, String updatedBy, Date updatedOn) {
        this.id = id;
        this.version = version;
        this.code = code;
        this.resourceType = resourceType;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.updatedBy = updatedBy;
        this.updatedOn = updatedOn;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator = "resource_type_seq_gen")
    @Column(name = "id", unique = true, nullable = false)
    @SequenceGenerator(name = "resource_type_seq_gen", sequenceName = "RESOURCE_TYPE_SEQ")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Version
    @Column(name = "version")
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Column(name = "code", unique = true, nullable = false, length = 10)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "resource_type", length = 45)
    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    @Column(name = "created_by", length = 45)
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on", length = 19)
    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Column(name = "updated_by", length = 45)
    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_on", length = 19)
    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "resourceType")
    public Set<ResourceName> getResourceNames() {
        return resourceNames;
    }

    public void setResourceNames(Set<ResourceName> resourceNames) {
        this.resourceNames = resourceNames;
    }
    
    
}
