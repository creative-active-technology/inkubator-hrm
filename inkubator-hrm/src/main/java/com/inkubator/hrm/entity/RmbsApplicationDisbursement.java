package com.inkubator.hrm.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rmbs_application_disbursement", catalog = "hrm")
public class RmbsApplicationDisbursement implements java.io.Serializable {

    private RmbsApplicationDisbursementId id;
    private RmbsApplication rmbsApplication;
    private RmbsDisbursement rmbsDisbursement;
    private String description;

    public RmbsApplicationDisbursement() {
    }

    public RmbsApplicationDisbursement(RmbsApplicationDisbursementId id, RmbsApplication rmbsApplication, RmbsDisbursement rmbsDisbursement) {
        this.id = id;
        this.rmbsApplication = rmbsApplication;
        this.rmbsDisbursement = rmbsDisbursement;
    }

    public RmbsApplicationDisbursement(RmbsApplicationDisbursementId id, RmbsApplication rmbsApplication, RmbsDisbursement rmbsDisbursement, String description) {
    	this.id = id;
        this.rmbsApplication = rmbsApplication;
        this.rmbsDisbursement = rmbsDisbursement;
        this.description = description;
    }

    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "rmbsApplicationId", column = @Column(name = "rmbs_application_id", nullable = false)),
        @AttributeOverride(name = "rmbsDisbursementId", column = @Column(name = "rmbs_disbursement_id", nullable = false))})
    public RmbsApplicationDisbursementId getId() {
		return id;
	}

	public void setId(RmbsApplicationDisbursementId id) {
		this.id = id;
	}
		
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rmbs_application_id", nullable = false, insertable = false, updatable = false)
    public RmbsApplication getRmbsApplication() {
		return rmbsApplication;
	}

	public void setRmbsApplication(RmbsApplication rmbsApplication) {
		this.rmbsApplication = rmbsApplication;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rmbs_disbursement_id", nullable = false, insertable = false, updatable = false)
	public RmbsDisbursement getRmbsDisbursement() {
		return rmbsDisbursement;
	}

	public void setRmbsDisbursement(RmbsDisbursement rmbsDisbursement) {
		this.rmbsDisbursement = rmbsDisbursement;
	}

    @Column(name = "description", length = 45)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
