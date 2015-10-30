package com.inkubator.hrm.web.search;


import java.util.ArrayList;
import java.util.List;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class RecruitmentScheduleSettingSearchParameter extends SearchParameter {

	private Long recruitMppApplyId;
	private Integer candidateStatusId = null;
	private List<Long> listJabatanOnSelectedMppApply = new ArrayList<Long>();

	public Long getRecruitMppApplyId() {
		return recruitMppApplyId;
	}

	public void setRecruitMppApplyId(Long recruitMppApplyId) {
		this.recruitMppApplyId = recruitMppApplyId;
	}

	public Integer getCandidateStatusId() {
		return candidateStatusId;
	}

	public void setCandidateStatusId(Integer candidateStatusId) {
		this.candidateStatusId = candidateStatusId;
	}

	public List<Long> getListJabatanOnSelectedMppApply() {
		return listJabatanOnSelectedMppApply;
	}

	public void setListJabatanOnSelectedMppApply(
			List<Long> listJabatanOnSelectedMppApply) {
		this.listJabatanOnSelectedMppApply = listJabatanOnSelectedMppApply;
	}

	

	
	
}
