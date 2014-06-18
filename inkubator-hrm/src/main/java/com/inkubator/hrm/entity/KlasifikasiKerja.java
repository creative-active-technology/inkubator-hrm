package com.inkubator.hrm.entity;
// Generated Jun 17, 2014 6:48:25 AM by Hibernate Tools 3.6.0

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

/**
 * KlasifikasiKerja generated by hbm2java
 */
@Entity
@Table(name = "klasifikasi_kerja", catalog = "hrm", uniqueConstraints = @UniqueConstraint(columnNames = "klasifikasi_kerja_code")
)
public class KlasifikasiKerja implements java.io.Serializable {

    private long id;
    private Integer version;
    private String klasifikasiKerjaCode;
    private String klasifikasiKerjaName;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;
    private String description;

    public KlasifikasiKerja() {
    }

    public KlasifikasiKerja(long id) {
        this.id = id;
    }

    public KlasifikasiKerja(long id, String klasifikasiKerjaCode, String klasifikasiKerjaName, String createdBy, Date createdOn, String updatedBy, Date updatedOn, String description) {
        this.id = id;
        this.klasifikasiKerjaCode = klasifikasiKerjaCode;
        this.klasifikasiKerjaName = klasifikasiKerjaName;
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

    @Column(name = "klasifikasi_kerja_code", unique = true, length = 8)
    public String getKlasifikasiKerjaCode() {
        return this.klasifikasiKerjaCode;
    }

    public void setKlasifikasiKerjaCode(String klasifikasiKerjaCode) {
        this.klasifikasiKerjaCode = klasifikasiKerjaCode;
    }

    @Column(name = "klasifikasi_kerja_name", length = 60)
    public String getKlasifikasiKerjaName() {
        return this.klasifikasiKerjaName;
    }

    public void setKlasifikasiKerjaName(String klasifikasiKerjaName) {
        this.klasifikasiKerjaName = klasifikasiKerjaName;
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

    @Column(name = "description", length = 65535)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
