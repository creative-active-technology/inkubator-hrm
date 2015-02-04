package com.inkubator.hrm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

/**
 *
 * @author rizkykojek
 */
@Entity
@Table(name = "travel_component_cost_rate", catalog = "hrm_personalia", uniqueConstraints = @UniqueConstraint(columnNames="code") )
public class TravelComponentCostRate implements java.io.Serializable {

	private Long id;
    private Integer version;
    private String code;
    private CostCenter costCenter;
    private GolonganJabatan golonganJabatan;
    private TravelComponent travelComponent;
    private TravelZone travelZone;
    private Double defaultRate;
    private String description;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;
    
    public TravelComponentCostRate(){
    	
    }
    
    public TravelComponentCostRate(long id){
    	this.id = id;
    }
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator = "travel_component_cost_rate_seq_gen")
    @Column(name = "id", unique = true, nullable = false)
    @SequenceGenerator(name = "travel_component_cost_rate_seq_gen", sequenceName = "TRAVEL_COMPONENT_COST_RATE_SEQ")
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
    
    @Column(name = "code", unique = true, nullable = false, length = 10)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cost_center_id", nullable = false)
    public CostCenter getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(CostCenter costCenter) {
		this.costCenter = costCenter;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "golongan_jabatan_id", nullable = false)
	public GolonganJabatan getGolonganJabatan() {
		return golonganJabatan;
	}

	public void setGolonganJabatan(GolonganJabatan golonganJabatan) {
		this.golonganJabatan = golonganJabatan;
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
    @JoinColumn(name = "travel_zone_id", nullable = false)
	public TravelZone getTravelZone() {
		return travelZone;
	}

	public void setTravelZone(TravelZone travelZone) {
		this.travelZone = travelZone;
	}

	@Column(name="default_rate", nullable = false, length=30)
	public Double getDefaultRate() {
		return defaultRate;
	}

	public void setDefaultRate(Double defaultRate) {
		this.defaultRate = defaultRate;
	}

	@Column(name="description", length=65535, columnDefinition="Text")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
