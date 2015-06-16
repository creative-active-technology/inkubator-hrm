/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.persistence.Version;

/**
 *
 * @author deni
 */
@Entity
@Table(name="tax_free"
    ,catalog="hrm_payroll_backup"
)
public class TaxFree  implements java.io.Serializable {

     private long id;
     private Integer version;
     private String tfStatus;
     private Integer incPerson;
     private Long freeNominal;
     private Date createdOn;
     private String createdBy;
     private String updatedBy;
     private Date updatedOn;
     private Set<EmpData> empDatas = new HashSet<EmpData>(0);
     
    public TaxFree() {
    }

 
    public TaxFree(long id) {
        this.id = id;
    }
    public TaxFree(long id, String tfStatus, Integer incPerson, Long freeNominal, Date createdOn, String createdBy, String updatedBy, Date updatedOn) {
       this.id = id;
       this.tfStatus = tfStatus;
       this.incPerson = incPerson;
       this.freeNominal = freeNominal;
       this.createdOn = createdOn;
       this.createdBy = createdBy;
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

    
    @Column(name="tf_status", length=3)
    public String getTfStatus() {
        return this.tfStatus;
    }
    
    public void setTfStatus(String tfStatus) {
        this.tfStatus = tfStatus;
    }

    
    @Column(name="inc_person")
    public Integer getIncPerson() {
        return this.incPerson;
    }
    
    public void setIncPerson(Integer incPerson) {
        this.incPerson = incPerson;
    }

    
    @Column(name="free_nominal", precision=10, scale=0)
    public Long getFreeNominal() {
        return this.freeNominal;
    }
    
    public void setFreeNominal(Long freeNominal) {
        this.freeNominal = freeNominal;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "taxFree")
    public Set<EmpData> getEmpDatas() {
        return empDatas;
    }

    public void setEmpDatas(Set<EmpData> empDatas) {
        this.empDatas = empDatas;
    }
    
    
}
