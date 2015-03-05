package com.inkubator.hrm.service;

import java.util.List;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.UnregEmpReligion;

/**
 *
 * @author rizkykojek
 */
public interface UnregEmpReligionService extends IService<UnregEmpReligion> {
	public List<UnregEmpReligion> getAllDataByUnregSalaryId(Long unregSalaryId) throws Exception;

}
