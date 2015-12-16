package com.inkubator.hrm.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 *
 * @author rizkykojek
 */
@Entity
@Table(name = "appraisal_competency_group", catalog = "hrm"
)
public class AppraisalCompetencyGroup implements Serializable {

	private long id;
    private Integer version;
    private String code;
    private String name;
    private String description;
    private AppraisalCompetencyType competencyType;
    private String createdBy;
    private String updatedBy;
    private Date createdOn;
    private Date updatedOn;
    private Set<AppraisalCompetencyGroupKlasifikasiKerja> klasifikasiKerjas = new HashSet<AppraisalCompetencyGroupKlasifikasiKerja>(0);
    
    public AppraisalCompetencyGroup(){
    	
    }
    
    public AppraisalCompetencyGroup(Long id){
    	this.id = id;
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

    
    @Column(name="code", unique=true, nullable=false, length=12)
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    
    @Column(name="name", unique=true, nullable=false, length=100)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    @Column(name="description", length = 65535, columnDefinition = "Text")
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competency_type_id", nullable=false)
    public AppraisalCompetencyType getCompetencyType() {
		return competencyType;
	}

	public void setCompetencyType(AppraisalCompetencyType competencyType) {
		this.competencyType = competencyType;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "appraisalCompetencyGroup", cascade = CascadeType.REMOVE)
	public Set<AppraisalCompetencyGroupKlasifikasiKerja> getKlasifikasiKerjas() {
		return klasifikasiKerjas;
	}

	public void setKlasifikasiKerjas(Set<AppraisalCompetencyGroupKlasifikasiKerja> klasifikasiKerjas) {
		this.klasifikasiKerjas = klasifikasiKerjas;
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
    @Column(name="created_on", length=19)
    public Date getCreatedOn() {
        return this.createdOn;
    }
    
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
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
