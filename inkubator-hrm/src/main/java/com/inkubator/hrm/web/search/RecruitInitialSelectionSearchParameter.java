package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;

public class RecruitInitialSelectionSearchParameter extends SearchParameter{
	private Long recruitMppApplyId;
	private Long jabatanId;
	private Integer kandidatId;

	public Long getRecruitMppApplyId() {
		return recruitMppApplyId;
	}

	public void setRecruitMppApplyId(Long recruitMppApplyId) {
		this.recruitMppApplyId = recruitMppApplyId;
	}

	public Long getJabatanId() {
		return jabatanId;
	}

	public void setJabatanId(Long jabatanId) {
		this.jabatanId = jabatanId;
	}

	public Integer getKandidatId() {
		return kandidatId;
	}

	public void setKandidatId(Integer kandidatId) {
		this.kandidatId = kandidatId;
	}
}
