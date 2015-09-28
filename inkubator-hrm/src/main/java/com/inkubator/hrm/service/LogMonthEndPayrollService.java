package com.inkubator.hrm.service;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.criterion.Order;
import org.primefaces.model.StreamedContent;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.LogMonthEndPayroll;
import com.inkubator.hrm.web.model.LogMonthEndPayrollViewModel;
import com.inkubator.hrm.web.model.PayrollHistoryReportModel;
import com.inkubator.hrm.web.model.ReportDataKomponenModel;
import com.inkubator.hrm.web.model.ReportSalaryNoteModel;
import com.inkubator.hrm.web.model.SalaryPerDepartmentReportModel;
import com.inkubator.hrm.web.search.LogMonthEndPayrollSearchParameter;
import com.inkubator.hrm.web.search.ReportDataComponentSearchParameter;
import com.inkubator.hrm.web.search.ReportPayrollHistorySearchParameter;
import com.inkubator.hrm.web.search.ReportSalaryNoteSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface LogMonthEndPayrollService extends IService<LogMonthEndPayroll> {

	public List<LogMonthEndPayrollViewModel> getByParam(LogMonthEndPayrollSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(LogMonthEndPayrollSearchParameter parameter) throws Exception;

	public BigDecimal getTotalTakeHomePayByPeriodeId(Long periodeId)throws Exception;

	public void afterMonthEndProcess() throws Exception;

	public void deleteByPeriodId(Long periodId) throws Exception;

	public List<PayrollHistoryReportModel> getByParamForPayrollHistoryReport(String searchParameter, int firstResult, int maxResults, Order order);

	public Long getTotalByParamForPayrollHistoryReport(String searchParameter);

	public List<SalaryPerDepartmentReportModel> getSalaryPerDepartmentPayrollHistoryReport(Long periodeId);

	public List<PayrollHistoryReportModel> getDataForPayrollHistoryReport();

	public PayrollHistoryReportModel getDataPayrollHistoryReportModelByPeriodeId(Long periodeId);

	public List<PayrollHistoryReportModel> getByParamForPayrollHistoryReport(ReportPayrollHistorySearchParameter searchParameter);
	
	public List<ReportSalaryNoteModel> getByParamForReportSalaryNote(ReportSalaryNoteSearchParameter searchParameter, int firstResult, int maxResults, Order order);
    
        public Long getTotalByParamForReportSalaryNote(ReportSalaryNoteSearchParameter searchParameter);

	public StreamedContent generatePersonalSalarySlip(Long periodId, Long empDataId) throws Exception;
	
	public StreamedContent generateMassSalarySlip(ReportSalaryNoteSearchParameter searchParameter) throws Exception;
        
        public List<ReportDataKomponenModel> getReportDataKomponenByParam(ReportDataComponentSearchParameter searchParameter, int firstResult, int maxResults, Order order);

        public Long getTotalReportDataKomponenByParam(ReportDataComponentSearchParameter searchParameter);

}
