package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.LogSalaryJournal;
import com.inkubator.hrm.web.model.ReportSalaryJounalModel;

/**
*
* @author rizkykojek
*/
public interface LogSalaryJournalService extends IService<LogSalaryJournal> {

	public void deleteByPeriodId(Long periodeId) throws Exception;
	
	public List<ReportSalaryJounalModel> getAllDataReportGroupingByPeriod(int firstResult, int maxResults, Order orderable) throws Exception;
	
	public Long getTotalReportGroupingByPeriod() throws Exception;
	
	public List<LogSalaryJournal> getAllDataReportByParam(Long periodId, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalReportByParam(Long periodId) throws Exception;
	
}
