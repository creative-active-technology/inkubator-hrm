package com.inkubator.hrm.entity;
// Generated Oct 14, 2014 10:35:26 AM by Hibernate Tools 4.3.1


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * JabatanProfesi generated by hbm2java
 */
@Entity
@Table(name="jabatan_profesi"
    ,catalog="hrm"
)
public class JabatanProfesi  implements java.io.Serializable {


     private JabatanProfesiId id;
     private Jabatan jabatan;
     private OccupationType occupationType;
     private String description;

    public JabatanProfesi() {
    }

	
    public JabatanProfesi(JabatanProfesiId id, Jabatan jabatan, OccupationType occupationType) {
        this.id = id;
        this.jabatan = jabatan;
        this.occupationType = occupationType;
    }
    public JabatanProfesi(JabatanProfesiId id, Jabatan jabatan, OccupationType occupationType, String description) {
       this.id = id;
       this.jabatan = jabatan;
       this.occupationType = occupationType;
       this.description = description;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="jabatanId", column=@Column(name="jabatan_id", nullable=false) ), 
        @AttributeOverride(name="profesiId", column=@Column(name="profesi_id", nullable=false) ) } )
    public JabatanProfesiId getId() {
        return this.id;
    }
    
    public void setId(JabatanProfesiId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="jabatan_id", nullable=false, insertable=false, updatable=false)
    public Jabatan getJabatan() {
        return this.jabatan;
    }
    
    public void setJabatan(Jabatan jabatan) {
        this.jabatan = jabatan;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="profesi_id", nullable=false, insertable=false, updatable=false)
    public OccupationType getOccupationType() {
        return this.occupationType;
    }
    
    public void setOccupationType(OccupationType occupationType) {
        this.occupationType = occupationType;
    }

    
        @Column(name="description", length=65535, columnDefinition="Text")
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }




}


