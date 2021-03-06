package com.inkubator.hrm.entity;
// Generated Apr 9, 2015 1:19:50 PM by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * DepartementUnitLocation generated by hbm2java
 */
@Entity
@Table(name="departement_unit_location"
    ,catalog="hrm"
)
public class DepartementUnitLocation  implements java.io.Serializable {


     private DepartementUnitLocationId id;
     private Integer version;
     private Department department;
     private UnitKerja unitKerja;
     private Date createdOn;
     private String createdBy;

    public DepartementUnitLocation() {
    }

	
    public DepartementUnitLocation(DepartementUnitLocationId id, Department department, UnitKerja unitKerja) {
        this.id = id;
        this.department = department;
        this.unitKerja = unitKerja;
    }
    public DepartementUnitLocation(DepartementUnitLocationId id, Department department, UnitKerja unitKerja, Date createdOn, String createdBy) {
       this.id = id;
       this.department = department;
       this.unitKerja = unitKerja;
       this.createdOn = createdOn;
       this.createdBy = createdBy;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="departemenId", column=@Column(name="departemen_id", nullable=false) ), 
        @AttributeOverride(name="unitId", column=@Column(name="unit_id", nullable=false) ) } )
    public DepartementUnitLocationId getId() {
        return this.id;
    }
    
    public void setId(DepartementUnitLocationId id) {
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
    @JoinColumn(name="departemen_id", nullable=false, insertable=false, updatable=false)
    public Department getDepartment() {
        return this.department;
    }
    
    public void setDepartment(Department department) {
        this.department = department;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="unit_id", nullable=false, insertable=false, updatable=false)
    public UnitKerja getUnitKerja() {
        return this.unitKerja;
    }
    
    public void setUnitKerja(UnitKerja unitKerja) {
        this.unitKerja = unitKerja;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_on", length=19)
    public Date getCreatedOn() {
        return this.createdOn;
    }
    
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    
    @Column(name="created_by", length=45)
    public String getCreatedBy() {
        return this.createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }




}


