package com.inkubator.hrm.dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.LogMonthEndPayroll;
import com.inkubator.hrm.web.model.LogMonthEndPayrollViewModel;
import com.inkubator.hrm.web.model.PayrollHistoryReportModel;
import com.inkubator.hrm.web.model.SalaryPerDepartmentReportModel;
import com.inkubator.hrm.web.search.LogMonthEndPayrollSearchParameter;

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

}
