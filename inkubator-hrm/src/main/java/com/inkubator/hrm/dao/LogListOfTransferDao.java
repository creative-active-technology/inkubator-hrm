package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.LogListOfTransfer;
import com.inkubator.hrm.web.model.BankTransferDistributionReportModel;
import com.inkubator.hrm.web.search.ReportBankTransferDataSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface LogListOfTransferDao extends IDAO<LogListOfTransfer> {

	public void deleteByPeriodId(Long periodId);

	public List<BankTransferDistributionReportModel> getBankTransferDistributionByPayrollHistoryReport(Long periodeId);

	public Long getTotalBankTransferByPayrollHistoryReport(Long periodeId);
	
	public List<LogListOfTransfer> getAllDataReportBankTransferData(ReportBankTransferDataSearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalReportBankTransferData(ReportBankTransferDataSearchParameter parameter);

}
