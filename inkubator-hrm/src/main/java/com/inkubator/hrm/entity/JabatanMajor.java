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
 * JabatanMajor generated by hbm2java
 */
@Entity
@Table(name="jabatan_major"
    ,catalog="hrm"
)
public class JabatanMajor  implements java.io.Serializable {


     private JabatanMajorId id;
     private Jabatan jabatan;
     private Major major;
     private String description;

    public JabatanMajor() {
    }

	
    public JabatanMajor(JabatanMajorId id, Jabatan jabatan, Major major) {
        this.id = id;
        this.jabatan = jabatan;
        this.major = major;
    }
    public JabatanMajor(JabatanMajorId id, Jabatan jabatan, Major major, String description) {
       this.id = id;
       this.jabatan = jabatan;
       this.major = major;
       this.description = description;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="jabatanId", column=@Column(name="jabatan_id", nullable=false) ), 
        @AttributeOverride(name="majorId", column=@Column(name="major_id", nullable=false) ) } )
    public JabatanMajorId getId() {
        return this.id;
    }
    
    public void setId(JabatanMajorId id) {
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
    @JoinColumn(name="major_id", nullable=false, insertable=false, updatable=false)
    public Major getMajor() {
        return this.major;
    }
    
    public void setMajor(Major major) {
        this.major = major;
    }

    
    @Column(name="description", length=65535)
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }




}


