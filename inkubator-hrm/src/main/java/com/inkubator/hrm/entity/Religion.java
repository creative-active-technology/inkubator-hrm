package com.inkubator.hrm.entity;

import java.util.Date;
import java.util.HashSet;
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

    public Religion() {
    }

    public Religion(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Religion(long id) {
        this.id = id;
    }

    public Religion(long id, String createdBy, Date createdOn, String name, String updatedBy, Date updatedOn, Set<WtHoliday> wtHolidays) {
        this.id = id;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.name = name;
        this.updatedBy = updatedBy;
        this.updatedOn = updatedOn;
        this.wtHolidays = wtHolidays;
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
}
