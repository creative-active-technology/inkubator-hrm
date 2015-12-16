package com.inkubator.hrm.entity;

import java.io.Serializable;
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

/**
 *
 * @author rizkykojek
 */
@Entity
@Table(name = "appraisal_competency_group_klasifikasi_kerja", catalog = "hrm"
)
public class AppraisalCompetencyGroupKlasifikasiKerja implements Serializable {

	private AppraisalCompetencyGroupKlasifikasiKerjaId id;
	private AppraisalCompetencyGroup appraisalCompetencyGroup;
	private KlasifikasiKerja klasifikasiKerja;
	private Date createdOn;
	private String createdBy;

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "appraisalCompetencyGroupId", column = @Column(name = "appraisal_competency_group_id", nullable = false) ),
			@AttributeOverride(name = "klasifikasiKerjaId", column = @Column(name = "klasifikasi_kerja_id", nullable = false) ) })
	public AppraisalCompetencyGroupKlasifikasiKerjaId getId() {
		return id;
	}

	public void setId(AppraisalCompetencyGroupKlasifikasiKerjaId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appraisal_competency_group_id", nullable = false, insertable = false, updatable = false)
	public AppraisalCompetencyGroup getAppraisalCompetencyGroup() {
		return appraisalCompetencyGroup;
	}

	public void setAppraisalCompetencyGroup(AppraisalCompetencyGroup appraisalCompetencyGroup) {
		this.appraisalCompetencyGroup = appraisalCompetencyGroup;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "klasifikasi_kerja_id", nullable = false, insertable = false, updatable = false)
	public KlasifikasiKerja getKlasifikasiKerja() {
		return klasifikasiKerja;
	}

	public void setKlasifikasiKerja(KlasifikasiKerja klasifikasiKerja) {
		this.klasifikasiKerja = klasifikasiKerja;
	}

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on", length = 19)
	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Column(name = "created_by", length = 45)
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

}
