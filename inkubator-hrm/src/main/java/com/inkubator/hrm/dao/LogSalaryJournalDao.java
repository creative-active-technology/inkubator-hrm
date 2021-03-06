package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.LogSalaryJournal;
import com.inkubator.hrm.web.model.ReportSalaryJounalModel;

/**
*
* @author rizkykojek
*/
public interface LogSalaryJournalDao extends IDAO<LogSalaryJournal> {

	public void deleteByPeriodId(Long periodeId);
	
	public List<ReportSalaryJounalModel> getAllDataReportGroupingByPeriod(int firstResult, int maxResults, Order orderable);
	
	public List<LogSalaryJournal> getAllDataReportByParam(Long periodId, int firstResult, int maxResults, Order orderable);

	public Long getTotalReportGroupingByPeriod();

	public Long getTotalReportByParam(Long periodId);
	
}
