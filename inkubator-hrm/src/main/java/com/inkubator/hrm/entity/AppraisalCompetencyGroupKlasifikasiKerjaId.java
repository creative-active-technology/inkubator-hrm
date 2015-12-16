package com.inkubator.hrm.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author rizkykojek
 */
@Embeddable
public class AppraisalCompetencyGroupKlasifikasiKerjaId implements Serializable {

	private long appraisalCompetencyGroupId;
	private long klasifikasiKerjaId;

	public AppraisalCompetencyGroupKlasifikasiKerjaId() {
	}

	public AppraisalCompetencyGroupKlasifikasiKerjaId(long appraisalCompetencyGroupId, long klasifikasiKerjaId) {
		this.appraisalCompetencyGroupId = appraisalCompetencyGroupId;
		this.klasifikasiKerjaId = klasifikasiKerjaId;
	}

	@Column(name = "appraisal_competency_group_id", nullable = false)
	public long getAppraisalCompetencyGroupId() {
		return appraisalCompetencyGroupId;
	}

	public void setAppraisalCompetencyGroupId(long appraisalCompetencyGroupId) {
		this.appraisalCompetencyGroupId = appraisalCompetencyGroupId;
	}
	
	@Column(name = "klasifikasi_kerja_id", nullable = false)
	public long getKlasifikasiKerjaId() {
		return klasifikasiKerjaId;
	}

	public void setKlasifikasiKerjaId(long klasifikasiKerjaId) {
		this.klasifikasiKerjaId = klasifikasiKerjaId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AppraisalCompetencyGroupKlasifikasiKerjaId))
			return false;
		AppraisalCompetencyGroupKlasifikasiKerjaId castOther = (AppraisalCompetencyGroupKlasifikasiKerjaId) other;

		return (this.getAppraisalCompetencyGroupId() == castOther.getAppraisalCompetencyGroupId())
				&& (this.getKlasifikasiKerjaId() == castOther.getKlasifikasiKerjaId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getAppraisalCompetencyGroupId();
		result = 37 * result + (int) this.getKlasifikasiKerjaId();
		return result;
	}
}
