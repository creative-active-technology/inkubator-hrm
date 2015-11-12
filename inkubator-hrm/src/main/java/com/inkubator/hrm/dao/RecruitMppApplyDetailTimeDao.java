package com.inkubator.hrm.dao;

import java.util.Date;
import java.util.List;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.RecruitMppApplyDetailTime;


/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public interface RecruitMppApplyDetailTimeDao extends IDAO<RecruitMppApplyDetailTime> {
	
	public Boolean isMppDetailTimeAlreadyCreatedForMppDetailId(Long recruitMppApplyDetailId);
	
	public List<RecruitMppApplyDetailTime> getListByMppApplyDetailId(Long mppApplyDetailId);
	
	public void saveAndFlush(RecruitMppApplyDetailTime recruitMppApplyDetailTime);
	
	public List<RecruitMppApplyDetailTime> getListWithMppMonthApplyLaterThanParam(Long recruitMppApplyDetailId, Date mppMonthApply);
	
}
