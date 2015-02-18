package com.inkubator.hrm.dao.impl;

import ch.lambdaj.Lambda;
import com.inkubator.common.util.DateTimeUtil;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.LogMonthEndPayrollDao;
import com.inkubator.hrm.entity.LogMonthEndPayroll;
import com.inkubator.hrm.web.model.LogMonthEndPayrollViewModel;
import com.inkubator.hrm.web.model.PayrollHistoryReportModel;
import com.inkubator.hrm.web.model.SalaryPerDepartmentReportModel;
import com.inkubator.hrm.web.search.LogMonthEndPayrollSearchParameter;
import com.inkubator.hrm.web.search.ReportPayrollHistorySearchParameter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.hamcrest.Matchers;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "logMonthEndPayrollDao")
@Lazy
public class LogMonthEndPayrollDaoImpl extends IDAOImpl<LogMonthEndPayroll> implements LogMonthEndPayrollDao {

    @Override
    public Class<LogMonthEndPayroll> getEntityClass() {
        return LogMonthEndPayroll.class;
    }

    @Override
    public BigDecimal getTotalTakeHomePayByPeriodeId(Long periodeId){
    	StringBuffer selectQuery = new StringBuffer(
    			"SELECT SUM(CASE WHEN modelCompSpecific = 100 THEN nominal ELSE 0.0 END) "
    			+ "FROM LogMonthEndPayroll "
    			+ "WHERE periodeId = :periodeId");
    	Query hbm = getCurrentSession().createQuery(selectQuery.toString()).setParameter("periodeId", periodeId);
    	
    	return new BigDecimal(hbm.uniqueResult().toString());
    }
    
    @Override
    public List<LogMonthEndPayrollViewModel> getByParam(LogMonthEndPayrollSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
    	StringBuffer selectQuery = new StringBuffer(
    			"SELECT id as id, "
    			+ "empNik as empNik, "
    			+ "empName as empName, "
    			+ "SUM(CASE WHEN factor = 1 THEN nominal ELSE 0.0 END) AS income, "
    			+ "SUM(CASE WHEN factor = -1 THEN nominal ELSE 0.0 END) AS deduction, "
    			+ "SUM(CASE WHEN factor = 0 AND modelCompSpecific != 100 THEN nominal ELSE 0.0 END) AS subsidy, "
    			+ "SUM(CASE WHEN modelCompSpecific = 100 THEN nominal ELSE 0.0 END) AS takeHomePay "
    			+ "FROM LogMonthEndPayroll ");    	
    	selectQuery.append(this.getWhereQueryByParam(parameter));
    	selectQuery.append("GROUP BY empDataId ");
    	selectQuery.append("ORDER BY " + orderable);
        
    	Query hbm = getCurrentSession().createQuery(selectQuery.toString()).setMaxResults(maxResults).setFirstResult(firstResult)
                	.setResultTransformer(Transformers.aliasToBean(LogMonthEndPayrollViewModel.class));
    	hbm = this.setValueQueryByParam(hbm, parameter);
    	
    	return hbm.list();                
    }
    
    @Override
    public Long getTotalByParam(LogMonthEndPayrollSearchParameter parameter) {
    	StringBuffer selectQuery = new StringBuffer(
    			"SELECT count(distinct empDataId) "
    			+ "FROM LogMonthEndPayroll ");    	
    	selectQuery.append(this.getWhereQueryByParam(parameter));
    	
    	Query hbm = getCurrentSession().createQuery(selectQuery.toString());    	
    	hbm = this.setValueQueryByParam(hbm, parameter);
    	
        return Long.valueOf(hbm.uniqueResult().toString());
    }

    private String getWhereQueryByParam(LogMonthEndPayrollSearchParameter parameter) {
    	StringBuffer whereQuery = new StringBuffer();
    	
    	if (StringUtils.isNotEmpty(parameter.getName())) {
    		if(StringUtils.isNotEmpty(whereQuery)){
    			whereQuery.append("AND ");
    		}
    		whereQuery.append("empName LIKE :empName ");
        }
    	
        if (StringUtils.isNotEmpty(parameter.getNik())) {
        	if(StringUtils.isNotEmpty(whereQuery)){
    			whereQuery.append("AND ");
    		}
        	whereQuery.append("empNik LIKE :empNik ");
        }
        
        if (parameter.getPeriodeId() != null) {
        	if(StringUtils.isNotEmpty(whereQuery)){
    			whereQuery.append("AND ");
    		}
        	whereQuery.append("periodeId = :periodeId ");
        }
        
        
        return StringUtils.isNotEmpty(whereQuery) ? "WHERE " + whereQuery.toString() : whereQuery.toString();
    }
    
    private Query setValueQueryByParam(Query hbm, LogMonthEndPayrollSearchParameter parameter){
    	
    	for(String param : hbm.getNamedParameters()){
    		if(StringUtils.equals(param, "empName")){
    			hbm.setParameter("empName", "%" + parameter.getName() + "%");
    		} else if(StringUtils.equals(param, "empNik")){
    			hbm.setParameter("empNik", "%" + parameter.getNik() + "%");
    		} else if(StringUtils.equals(param, "periodeId")){
    			hbm.setParameter("periodeId", parameter.getPeriodeId());
    		}
    	}
    	
    	return hbm;
    }

	@Override
	public void deleteByPeriodId(Long periodId) {
		Query query = getCurrentSession().createQuery("DELETE FROM LogMonthEndPayroll temp WHERE temp.periodeId = :periodId")
				.setLong("periodId", periodId);
        query.executeUpdate();
		
	}
        
    @Override
    public List<PayrollHistoryReportModel> getByParamForPayrollHistoryReport(String searchParameter, int firstResult, int maxResults, Order order) {
       
       final StringBuilder query = new StringBuilder("SELECT lme.id AS id, lme.periodeStart AS tglAwalPeriode, lme.periodeEnd AS tglAkhirPeriode, "
                + " DATE_FORMAT(lme.periodeStart, '%d %M %Y') AS tglAwalPeriodeInString, lme.periodeId AS periodeId,  COUNT(lme.empDataId) AS jumlahKaryawan, SUM(lme.nominal) AS nominal FROM LogMonthEndPayroll lme "
                + " WHERE lme.paySalaryCompId = 100 ");
        
        query.append(" GROUP BY lme.periodeId ");
      
        if (order.toString().contains("jumlahKaryawan") || order.toString().contains("nominal")) {
            query.append(" ORDER BY ").append(order);
        } else {
            query.append(" ORDER BY lme.").append(order);
        }
        if (searchParameter != null) {
           
            List<PayrollHistoryReportModel> listResult = getCurrentSession().createQuery(query.toString())                   
                    .setMaxResults(maxResults).setFirstResult(firstResult)
                    .setResultTransformer(Transformers.aliasToBean(PayrollHistoryReportModel.class))
                    .list();
            
            //Filter tglAwalPeriode based on searchParameter            
            listResult = Lambda.select(listResult, Lambda.having(Lambda.on(PayrollHistoryReportModel.class).getTglAwalPeriodeInString(), Matchers.containsString(searchParameter)));
           
            return listResult;
        } else {
            return getCurrentSession().createQuery(query.toString())
                    .setMaxResults(maxResults).setFirstResult(firstResult)
                    .setResultTransformer(Transformers.aliasToBean(PayrollHistoryReportModel.class))
                    .list();
        }
    }

    @Override
    public Long getTotalByParamForPayrollHistoryReport(String searchParameter) {
        final StringBuilder query = new StringBuilder("SELECT COUNT(*) FROM ( SELECT lme.id AS id, "
                + " lme.periode_start AS tglAwalPeriode, lme.periode_end AS tglAkhirPeriode, "              
                + " COUNT(lme.emp_data_id) AS jumlahKaryawan, SUM(lme.nominal) AS nominal "
                + " FROM log_month_end_payroll lme "
                + " WHERE lme.pay_salary_comp_id = 100 GROUP BY lme.periode_id  ) AS jumlahRow ");
        
        Query hbm = getCurrentSession().createSQLQuery(query.toString());
            return Long.valueOf(hbm.uniqueResult().toString());
    }

    @Override
    public List<SalaryPerDepartmentReportModel> getSalaryPerDepartmentPayrollHistoryReport(Long periodeId) {
        
        final StringBuilder query = new StringBuilder("SELECT D.department_name AS departmentName, D.id AS departmentId, " +               
                " SUM(A.nominal) as nominal from log_month_end_payroll A " +
                " INNER JOIN pay_salary_component B ON A.pay_salary_comp_id = B.id AND A.pay_salary_comp_id = 100 " +
                " INNER JOIN wt_periode W ON A.periode_id = W.id AND A.periode_id = :periodeId " +
                " INNER JOIN emp_data E ON A.emp_data_id = E.id  " +
                " INNER JOIN jabatan J ON E.jabatan_id = J.id " +
                " INNER JOIN department D ON J.departement_id = D.id " +
                " GROUP BY D.department_name ");
        
        return getCurrentSession().createSQLQuery(query.toString())   
                    .setParameter("periodeId",periodeId)
                    .setResultTransformer(Transformers.aliasToBean(SalaryPerDepartmentReportModel.class))
                    .list();
    }

	@Override
	public List<LogMonthEndPayroll> getAllDataByPaySalaryCompAndPeriodeId(Long paySalaryCompId, String paySalaryCompCode, String paySalaryCompName, Long periodeId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("paySalaryCompId", paySalaryCompId));
		criteria.add(Restrictions.eq("paySalaryCompCode", paySalaryCompCode));
		criteria.add(Restrictions.eq("paySalaryDesc", paySalaryCompName));
		criteria.add(Restrictions.eq("periodeId", periodeId));
		return criteria.list();
	}

    @Override
    public List<PayrollHistoryReportModel> getDataForPayrollHistoryReport() {
       final StringBuilder query = new StringBuilder("SELECT lme.id AS id, lme.periodeStart AS tglAwalPeriode, lme.periodeEnd AS tglAkhirPeriode, "
                + " lme.periodeId AS periodeId,  COUNT(lme.empDataId) AS jumlahKaryawan, SUM(lme.nominal) AS nominal FROM LogMonthEndPayroll lme "
                + " WHERE lme.paySalaryCompId = 100 ");        
        query.append(" GROUP BY lme.periodeId ");
        
         return getCurrentSession().createQuery(query.toString())                   
                    .setResultTransformer(Transformers.aliasToBean(PayrollHistoryReportModel.class))
                    .list();
        
    }

    @Override
    public PayrollHistoryReportModel getDataPayrollHistoryReportModelByPeriodeId(Long periodeId) {
        final StringBuilder query = new StringBuilder("SELECT lme.id AS id, lme.periodeStart AS tglAwalPeriode, lme.periodeEnd AS tglAkhirPeriode, "
                + " lme.periodeId AS periodeId,  COUNT(lme.empDataId) AS jumlahKaryawan, SUM(lme.nominal) AS nominal FROM LogMonthEndPayroll lme "
                + " WHERE lme.paySalaryCompId = 100 AND lme.periodeId = :periodeId ");       
        
         return (PayrollHistoryReportModel) getCurrentSession().createQuery(query.toString())    
                    .setParameter("periodeId", periodeId)
                    .setResultTransformer(Transformers.aliasToBean(PayrollHistoryReportModel.class))
                    .uniqueResult();
    }

    @Override
    public List<PayrollHistoryReportModel> getByParamForPayrollHistoryReport(ReportPayrollHistorySearchParameter searchParameter) {
       
        final StringBuilder query = new StringBuilder("SELECT lme.id AS id, lme.periodeStart AS tglAwalPeriode, lme.periodeEnd AS tglAkhirPeriode, "
                + " lme.periodeId AS periodeId,  COUNT(lme.empDataId) AS jumlahKaryawan, SUM(lme.nominal) AS nominal FROM LogMonthEndPayroll lme "
                + " WHERE lme.paySalaryCompId = 100 ");
        
        //filter beginning salary period
        if(searchParameter.getStartDate() != null){            
             query.append(" AND lme.periodeStart >= :startDate ");
        }
        
        //filter end salary period
        if (searchParameter.getEndDate() != null) {
            query.append(" AND lme.periodeStart <= :endDate ");
        }
        
        query.append(" GROUP BY lme.periodeId ");        
        Query hbm = getCurrentSession().createQuery(query.toString());
        
        if(searchParameter.getStartDate() != null){
            
            //Set date to first date of month from given startDate
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(searchParameter.getStartDate());
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            
            hbm.setParameter("startDate", searchParameter.getStartDate());            
        }
         
        if(searchParameter.getEndDate() != null){
            
            //Set date to first date of month from given startDate
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(searchParameter.getEndDate());
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            
            hbm.setParameter("endDate", calendar.getTime());            
        }
        
        return  hbm.setResultTransformer(Transformers.aliasToBean(PayrollHistoryReportModel.class))
                    .list();
        
    }
    
}
