package com.inkubator.hrm.entity;

import java.io.Serializable;
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
 * @author rizkykojek
 */
@Entity
@Table(name = "business_travel_component", catalog = "hrm")
public class BusinessTravelComponent implements Serializable {
	private Long id;
    private Integer version;
    private BusinessTravel businessTravel;
    private TravelComponent travelComponent;
    private CostCenter costCenter;    
    private Integer quantity;
    private Double earnedPerQuantity;    
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;
    
    public BusinessTravelComponent(){
    	
    }
    
    public BusinessTravelComponent(long id){
    	this.id=id;
    }
    
    @Id
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
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
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_travel_id", nullable = false)
    public BusinessTravel getBusinessTravel() {
		return businessTravel;
	}

	public void setBusinessTravel(BusinessTravel businessTravel) {
		this.businessTravel = businessTravel;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "travel_component_id", nullable = false)
	public TravelComponent getTravelComponent() {
		return travelComponent;
	}

	public void setTravelComponent(TravelComponent travelComponent) {
		this.travelComponent = travelComponent;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cost_center_id", nullable = false)
	public CostCenter getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(CostCenter costCenter) {
		this.costCenter = costCenter;
	}

	@Column(name = "quantity", nullable = false)
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Column(name="earned_per_quantity", nullable = false)
	public Double getEarnedPerQuantity() {
		return earnedPerQuantity;
	}

	public void setEarnedPerQuantity(Double earnedPerQuantity) {
		this.earnedPerQuantity = earnedPerQuantity;
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
}
