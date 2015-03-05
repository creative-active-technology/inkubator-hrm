package com.inkubator.hrm.service;

import java.util.List;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.UnregDepartement;

/**
 *
 * @author rizkykojek
 */
public interface UnregDepartementService extends IService<UnregDepartement> {
	public List<UnregDepartement> getAllDataByUnregSalaryId(Long unregSalaryId) throws Exception;

}
