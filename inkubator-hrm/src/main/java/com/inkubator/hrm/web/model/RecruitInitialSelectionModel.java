package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class RecruitInitialSelectionModel implements Serializable {
	private Long recruitMppApplyId;
	private Long jabatanId;
	private Integer kandidatId;
    private List<Long> listRecruitApplicantId;
    private Map<Long, Boolean> selectedIds =  new HashMap<Long, Boolean>();

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

	public List<Long> getListRecruitApplicantId() {
		listRecruitApplicantId = new ArrayList<Long>();
		for(Map.Entry<Long, Boolean> selected : selectedIds.entrySet()){
			if(StringUtils.equals(String.valueOf(selected.getValue()), "true")){
				listRecruitApplicantId.add(selected.getKey());
			}
		}
		return listRecruitApplicantId;
	}

	public void setListRecruitApplicantId(List<Long> listRecruitApplicantId) {
		this.listRecruitApplicantId = listRecruitApplicantId;
	}

	public Map<Long, Boolean> getSelectedIds() {
		return selectedIds;
	}

	public void setSelectedIds(Map<Long, Boolean> selectedIds) {
		this.selectedIds = selectedIds;
	}
	
	
}