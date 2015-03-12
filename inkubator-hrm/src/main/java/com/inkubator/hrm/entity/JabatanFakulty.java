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
 * JabatanFakulty generated by hbm2java
 */
@Entity
@Table(name="jabatan_fakulty"
    ,catalog="hrm_payroll"
)
public class JabatanFakulty  implements java.io.Serializable {


     private JabatanFakultyId id;
     private Faculty faculty;
     private Jabatan jabatan;
     private String description;

    public JabatanFakulty() {
    }

	
    public JabatanFakulty(JabatanFakultyId id, Faculty faculty, Jabatan jabatan) {
        this.id = id;
        this.faculty = faculty;
        this.jabatan = jabatan;
    }
    public JabatanFakulty(JabatanFakultyId id, Faculty faculty, Jabatan jabatan, String description) {
       this.id = id;
       this.faculty = faculty;
       this.jabatan = jabatan;
       this.description = description;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="jabatanId", column=@Column(name="jabatan_id", nullable=false) ), 
        @AttributeOverride(name="fakultyId", column=@Column(name="fakulty_id", nullable=false) ) } )
    public JabatanFakultyId getId() {
        return this.id;
    }
    
    public void setId(JabatanFakultyId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="fakulty_id", nullable=false, insertable=false, updatable=false)
    public Faculty getFaculty() {
        return this.faculty;
    }
    
    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="jabatan_id", nullable=false, insertable=false, updatable=false)
    public Jabatan getJabatan() {
        return this.jabatan;
    }
    
    public void setJabatan(Jabatan jabatan) {
        this.jabatan = jabatan;
    }

    @Column(name="description", length=65535, columnDefinition="Text")
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }




}


