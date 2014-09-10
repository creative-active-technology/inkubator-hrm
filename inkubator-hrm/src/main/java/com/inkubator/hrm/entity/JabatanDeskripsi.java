package com.inkubator.hrm.entity;
// Generated Jun 17, 2014 4:36:40 PM by Hibernate Tools 3.6.0


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * JabatanDeskripsi generated by hbm2java
 */
@Entity
@Table(name="jabatan_deskripsi"
    ,catalog="hrm"
)
public class JabatanDeskripsi  implements java.io.Serializable {


     private long id;
     private Integer version;
     private Jabatan jabatan;
     private Integer kategoryTugas;
     private Integer typeWaktu;
     private String description;
     private String createdBy;
     private Date createdOn;
     private String updatedBy;
     private Date updatedOn;

    public JabatanDeskripsi() {
    }

	
    public JabatanDeskripsi(long id) {
        this.id = id;
    }
    public JabatanDeskripsi(long id, Jabatan jabatan, Integer kategoryTugas, Integer typeWaktu, String description, String createdBy, Date createdOn, String updatedBy, Date updatedOn) {
       this.id = id;
       this.jabatan = jabatan;
       this.kategoryTugas = kategoryTugas;
       this.typeWaktu = typeWaktu;
       this.description = description;
       this.createdBy = createdBy;
       this.createdOn = createdOn;
       this.updatedBy = updatedBy;
       this.updatedOn = updatedOn;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }

    @Version
    @Column(name="version")
    public Integer getVersion() {
        return this.version;
    }
    
    public void setVersion(Integer version) {
        this.version = version;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="jabatan_id")
    public Jabatan getJabatan() {
        return this.jabatan;
    }
    
    public void setJabatan(Jabatan jabatan) {
        this.jabatan = jabatan;
    }

    
    @Column(name="kategory_tugas")
    public Integer getKategoryTugas() {
        return this.kategoryTugas;
    }
    
    public void setKategoryTugas(Integer kategoryTugas) {
        this.kategoryTugas = kategoryTugas;
    }

    
    @Column(name="type_waktu")
    public Integer getTypeWaktu() {
        return this.typeWaktu;
    }
    
    public void setTypeWaktu(Integer typeWaktu) {
        this.typeWaktu = typeWaktu;
    }

    
    @Column(name="description", length=65535, columnDefinition="Text")
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    
    @Column(name="created_by", length=45)
    public String getCreatedBy() {
        return this.createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_on", length=19)
    public Date getCreatedOn() {
        return this.createdOn;
    }
    
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    
    @Column(name="updated_by", length=45)
    public String getUpdatedBy() {
        return this.updatedBy;
    }
    
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_on", length=19)
    public Date getUpdatedOn() {
        return this.updatedOn;
    }
    
    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }




}


