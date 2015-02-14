package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.LogListOfTransfer;
import com.inkubator.hrm.web.model.BankTransferDistributionReportModel;
import com.inkubator.hrm.web.model.SalaryPerDepartmentReportModel;
import java.util.List;

/**
*
* @author rizkykojek
*/
public interface LogListOfTransferDao extends IDAO<LogListOfTransfer> {

	public void deleteByPeriodId(Long periodId);
        
        public List<BankTransferDistributionReportModel> getBankTransferDistributionByPayrollHistoryReport(Long periodeId);
	
}
