package com.inkubator.hrm.entity;
// Generated Dec 30, 2014 11:22:24 AM by Hibernate Tools 4.3.1


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
 * UnregPayComponentsException generated by hbm2java
 */
@Entity
@Table(name="unreg_pay_components_exception"
    ,catalog="hrm_payroll_backup"
)
public class UnregPayComponentsException  implements java.io.Serializable {


     private UnregPayComponentsExceptionId id;
     private Integer version;
     private EmpData empData;
     private UnregPayComponents unregPayComponents;
     private Double nominal;
     private String descriptions;
     private String createdBy;
     private Date createdOn;
     private String updatedBy;
     private Date updatedOn;

    public UnregPayComponentsException() {
    	
    }

	
    public UnregPayComponentsException(UnregPayComponentsExceptionId id, EmpData empData, UnregPayComponents unregPayComponents) {
        this.id = id;
        this.empData = empData;
        this.unregPayComponents = unregPayComponents;
    }
    
    public UnregPayComponentsException(UnregPayComponentsExceptionId id, EmpData empData, UnregPayComponents unregPayComponents, String descriptions, String createdBy, Date createdOn, String updatedBy, Date updatedOn) {
       this.id = id;
       this.empData = empData;
       this.unregPayComponents = unregPayComponents;
       this.descriptions = descriptions;
       this.createdBy = createdBy;
       this.createdOn = createdOn;
       this.updatedBy = updatedBy;
       this.updatedOn = updatedOn;
    }
   
    @EmbeddedId
    @AttributeOverrides( {
        @AttributeOverride(name="unregPayComponentsId", column=@Column(name="unreg_pay_components_id", nullable=false) ), 
        @AttributeOverride(name="empDataId", column=@Column(name="emp_data_id", nullable=false) ) } )
    public UnregPayComponentsExceptionId getId() {
        return this.id;
    }
    
    public void setId(UnregPayComponentsExceptionId id) {
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
    @JoinColumn(name="emp_data_id", nullable=false, insertable=false, updatable=false)
    public EmpData getEmpData() {
        return this.empData;
    }
    
    public void setEmpData(EmpData empData) {
        this.empData = empData;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="unreg_pay_components_id", nullable=false, insertable=false, updatable=false)
    public UnregPayComponents getUnregPayComponents() {
		return unregPayComponents;
	}

	public void setUnregPayComponents(UnregPayComponents unregPayComponents) {
		this.unregPayComponents = unregPayComponents;
	}

	@Column(name="nominal", nullable = false)
	public Double getNominal() {
		return nominal;
	}

	public void setNominal(Double nominal) {
		this.nominal = nominal;
	}

	@Column(name="descriptions", length=65535, columnDefinition = "Text")
    public String getDescriptions() {
        return this.descriptions;
    }
    
    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
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


