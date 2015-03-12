package com.inkubator.hrm.service;

import java.util.Date;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.LogUnregListOfTransfer;
import com.inkubator.hrm.entity.TempUnregPayroll;

/**
 *
 * @author rizkykojek
 */
public interface LogUnregListOfTransferService extends IService<LogUnregListOfTransfer> {

	public void deleteByUnregSalaryId(Long unregSalaryId) throws Exception;

	public void executeBatchUnregPayroll(TempUnregPayroll tempUnregPayroll, String createdBy, Date createdOn) throws Exception;

}
