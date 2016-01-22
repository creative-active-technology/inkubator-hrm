/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.entity;

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
 *
 * @author Deni
 */
@Entity
@Table(name="system_scoring_index"
    ,catalog="hrm"
)
public class SystemScoringIndex  implements java.io.Serializable,Comparable<SystemScoringIndex> {


     private long id;
     private Integer version;
     private SystemScoring systemScoring;
     private Integer value;
     private String labelMask;
     private String description;
     private Integer orderScala;
     private String createdBy;
     private Date createdOn;
     private String updatedBy;
     private Date updatedOn;

    public SystemScoringIndex() {
    }

	
    public SystemScoringIndex(long id, SystemScoring systemScoring) {
        this.id = id;
        this.systemScoring = systemScoring;
    }
    public SystemScoringIndex(long id, SystemScoring systemScoring, Integer value, String labelMask, String description, Integer orderScala, String createdBy, Date createdOn, String updatedBy, Date updatedOn) {
       this.id = id;
       this.systemScoring = systemScoring;
       this.value = value;
       this.labelMask = labelMask;
       this.description = description;
       this.orderScala = orderScala;
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
    @JoinColumn(name="system_scoring_id", nullable=false)
    public SystemScoring getSystemScoring() {
        return this.systemScoring;
    }
    
    public void setSystemScoring(SystemScoring systemScoring) {
        this.systemScoring = systemScoring;
    }

    
    @Column(name="value")
    public Integer getValue() {
        return this.value;
    }
    
    public void setValue(Integer value) {
        this.value = value;
    }

    
    @Column(name="label_mask", length=60)
    public String getLabelMask() {
        return this.labelMask;
    }
    
    public void setLabelMask(String labelMask) {
        this.labelMask = labelMask;
    }

    
    @Column(name="description")
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    
    @Column(name="order_scala")
    public Integer getOrderScala() {
        return this.orderScala;
    }
    
    public void setOrderScala(Integer orderScala) {
        this.orderScala = orderScala;
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


	@Override
	public int compareTo(SystemScoringIndex o) {
		// TODO Auto-generated method stub
		return this.orderScala.compareTo(o.orderScala);
	}




}


