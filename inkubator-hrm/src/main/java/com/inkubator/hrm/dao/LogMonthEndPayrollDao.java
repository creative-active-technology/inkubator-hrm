package com.inkubator.hrm.dao;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
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
public interface LogMonthEndPayrollDao extends IDAO<LogMonthEndPayroll> {
	
	public List<LogMonthEndPayrollViewModel> getByParam(LogMonthEndPayrollSearchParameter parameter, int firstResult, int maxResults, Order orderable);
	
	public Long getTotalByParam(LogMonthEndPayrollSearchParameter parameter);

	public BigDecimal getTotalTakeHomePayByPeriodeId(Long periodeId);

	public void deleteByPeriodId(Long periodId);
        
    public List<PayrollHistoryReportModel> getByParamForPayrollHistoryReport(String searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalByParamForPayrollHistoryReport(String searchParameter);        
                
    public List<SalaryPerDepartmentReportModel> getSalaryPerDepartmentPayrollHistoryReport(Long periodeId);

	public List<LogMonthEndPayroll> getAllDataByPaySalaryCompAndPeriodeId(Long paySalaryCompId, String paySalaryCompCode, String paySalaryCompName, Long periodeId);
        
    public List<PayrollHistoryReportModel> getDataForPayrollHistoryReport();
        
    public PayrollHistoryReportModel getDataPayrollHistoryReportModelByPeriodeId(Long periodeId);
        
    public List<PayrollHistoryReportModel> getByParamForPayrollHistoryReport(ReportPayrollHistorySearchParameter searchParameter);
    
    public List<ReportSalaryNoteModel> getByParamForReportSalaryNote(ReportSalaryNoteSearchParameter searchParameter, int firstResult, int maxResults, Order order);
    
    public Long getTotalByParamForReportSalaryNote(ReportSalaryNoteSearchParameter searchParameter);
    
    public List<LogMonthEndPayroll> getEntityByEmpDataIdAndPeriodIdAndCompSpecific(Long empDataId, Long periodId, Integer specific);

    public LogMonthEndPayroll getEntityByEmpDataIdAndPeriodIdAndPaySalaryCompId(Long empDataId, Long periodId, Long paySalaryCompId);
    
    public List<ReportDataKomponenModel> getReportDataKomponenByParam(ReportDataComponentSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalReportDataKomponenByParam(ReportDataComponentSearchParameter searchParameter);
    
    public Collection<Long> getAllDataEmpIdByParam(ReportSalaryNoteSearchParameter searchParameter);
    
}
