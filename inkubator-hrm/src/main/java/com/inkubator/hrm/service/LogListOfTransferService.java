package com.inkubator.hrm.service;

import java.util.Date;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.LogListOfTransfer;
import com.inkubator.hrm.entity.PayTempKalkulasi;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.web.model.BankTransferDistributionReportModel;
import com.inkubator.hrm.web.search.ReportBankTransferDataSearchParameter;

import java.util.List;

import org.hibernate.criterion.Order;

/**
 *
 * @author rizkykojek
 */
public interface LogListOfTransferService extends IService<LogListOfTransfer> {

	public void deleteByPeriodId(Long periodId) throws Exception;

	public void executeBatchMonthEndPayroll(PayTempKalkulasi payTempKalkulasi, WtPeriode periode, String createdBy, Date createdOn) throws Exception;

	public List<BankTransferDistributionReportModel> getBankTransferDistributionByPayrollHistoryReport(Long periodeId);

	public Long getTotalBankTransferByPayrollHistoryReport(Long periodeId);
	
	public List<LogListOfTransfer> getAllDataReportBankTransferData(ReportBankTransferDataSearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalReportBankTransferData(ReportBankTransferDataSearchParameter parameter);

}
