package com.inkubator.hrm.entity;
// Generated Aug 11, 2014 12:16:39 PM by Hibernate Tools 3.6.0


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

/**
 * TravelComponent generated by hbm2java
 */
@Entity
@Table(name="travel_component"
    ,catalog="hrm_payroll"
    , uniqueConstraints = @UniqueConstraint(columnNames="code") 
)
public class TravelComponent  implements java.io.Serializable {


     private long id;
     private Integer version;
     private String createdBy;
     private Date createdOn;
     private String updatedBy;
     private Date updatedOn;
     private String code;
     private String name;
     private String description;
     private Integer measurement;
     private Set<TravelComponentCostRate> travelComponentCostRates  = new HashSet<TravelComponentCostRate>(0);
     private Set<BusinessTravelComponent> businessTravelComponents = new HashSet<BusinessTravelComponent>(0);

    public TravelComponent() {
    }

	
    public TravelComponent(long id) {
        this.id = id;
    }
    public TravelComponent(long id, String createdBy, Date createdOn, String updatedBy, Date updatedOn, String code, String name, String description, Integer measurement, Set<TravelComponentCostRate> travelComponentCostRates, Set<BusinessTravelComponent> businessTravelComponents) {
       this.id = id;
       this.createdBy = createdBy;
       this.createdOn = createdOn;
       this.updatedBy = updatedBy;
       this.updatedOn = updatedOn;
       this.code = code;
       this.name = name;
       this.description = description;
       this.measurement = measurement;
       this.travelComponentCostRates = travelComponentCostRates;
       this.businessTravelComponents = businessTravelComponents;
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

    
    @Column(name="code", unique=true, length=8)
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    
    @Column(name="name", length=60)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="description", length=65535, columnDefinition="Text")
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    
    @Column(name="measurement")
    public Integer getMeasurement() {
        return this.measurement;
    }
    
    public void setMeasurement(Integer measurement) {
        this.measurement = measurement;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy="travelComponent")
	public Set<TravelComponentCostRate> getTravelComponentCostRates() {
		return travelComponentCostRates;
	}

	public void setTravelComponentCostRates(Set<TravelComponentCostRate> travelComponentCostRates) {
		this.travelComponentCostRates = travelComponentCostRates;
	}
    
	@OneToMany(fetch=FetchType.LAZY, mappedBy="travelComponent")
	public Set<BusinessTravelComponent> getBusinessTravelComponents() {
		return businessTravelComponents;
	}

	public void setBusinessTravelComponents(Set<BusinessTravelComponent> businessTravelComponents) {
		this.businessTravelComponents = businessTravelComponents;
	}



}


