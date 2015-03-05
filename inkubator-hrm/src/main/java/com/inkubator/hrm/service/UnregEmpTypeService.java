package com.inkubator.hrm.service;

import java.util.List;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.UnregEmpType;

/**
 *
 * @author rizkykojek
 */
public interface UnregEmpTypeService extends IService<UnregEmpType> {

	public List<UnregEmpType> getAllDataByUnregSalaryId(Long unregSalaryId);
}
