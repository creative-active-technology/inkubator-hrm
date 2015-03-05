package com.inkubator.hrm.service;

import java.util.List;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.UnregGoljab;

/**
 *
 * @author rizkykojek
 */
public interface UnregGoljabService extends IService<UnregGoljab> {
	public List<UnregGoljab> getAllDataByUnregSalaryId(Long unregSalaryId);
	
}
