package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.hrm.entity.RecruitMppApplyDetail;
import com.inkubator.hrm.entity.RecruitSelectionApplicantSchedulle;

/**
 *
 * @author rizkykojek
 */
public class SelectionApplicantPassedModel implements Serializable {

	private Long scheduleId;
	private RecruitSelectionApplicantSchedulle selectionApplicantSchedulle;
	private RecruitMppApplyDetail mppApplyDetail;
	private List<Long> listApplicantId;
    private Map<BigInteger, Boolean> selectedIds =  new HashMap<BigInteger, Boolean>();
    
    public Long getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}
	public RecruitSelectionApplicantSchedulle getSelectionApplicantSchedulle() {
		return selectionApplicantSchedulle;
	}
	public void setSelectionApplicantSchedulle(RecruitSelectionApplicantSchedulle selectionApplicantSchedulle) {
		this.selectionApplicantSchedulle = selectionApplicantSchedulle;
	}
	public RecruitMppApplyDetail getMppApplyDetail() {
		return mppApplyDetail;
	}
	public void setMppApplyDetail(RecruitMppApplyDetail mppApplyDetail) {
		this.mppApplyDetail = mppApplyDetail;
	}
	public Map<BigInteger, Boolean> getSelectedIds() {
		return selectedIds;
	}
	public void setSelectedIds(Map<BigInteger, Boolean> selectedIds) {
		this.selectedIds = selectedIds;
	} 
	public List<Long> getListApplicantId() {
		listApplicantId = new ArrayList<Long>();
		for(Map.Entry<BigInteger, Boolean> selected : selectedIds.entrySet()){
			if(StringUtils.equals(String.valueOf(selected.getValue()), "true")){
				listApplicantId.add(selected.getKey().longValue());
			}
		}
		return listApplicantId;
	}
	public void setListApplicantId(List<Long> listApplicantId) {
		//do nothing
	}
}
