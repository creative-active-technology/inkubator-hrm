package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.RecruitMppApplyDetailTime;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public interface RecruitMppApplyDetailTimeService extends IService<RecruitMppApplyDetailTime> {
	
	public void saveDataAndUpdateMppApplyDetail(RecruitMppApplyDetailTime entity) throws Exception;
	
}
