package com.inkubator.hrm.dao.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matchers;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import ch.lambdaj.Lambda;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.LogMonthEndPayrollDao;
import com.inkubator.hrm.entity.LogMonthEndPayroll;
import com.inkubator.hrm.web.model.LogMonthEndPayrollViewModel;
import com.inkubator.hrm.web.model.PayrollHistoryReportModel;
import com.inkubator.hrm.web.model.ReportDataKomponenModel;
import com.inkubator.hrm.web.model.ReportSalaryNoteModel;
import com.inkubator.hrm.web.model.SalaryPerDepartmentReportModel;
import com.inkubator.hrm.web.search.LogMonthEndPayrollSearchParameter;
import com.inkubator.hrm.web.search.ReportDataComponentSearchParameter;
import com.inkubator.hrm.web.search.ReportPayrollHistorySearchParameter;
import com.inkubator.hrm.web.search.ReportPayrollHistoryViewSearchParameter;
import com.inkubator.hrm.web.search.ReportSalaryNoteSearchParameter;
import com.inkubator.hrm.web.search.WtAttendanceCalculationSearchParameter;
import com.inkubator.hrm.web.search.WtPeriodeSearchParameter;

import java.text.SimpleDateFormat;
import org.hibernate.criterion.Projections;
import org.hibernate.sql.JoinType;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

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
    	selectQuery.append("GROUP BY periodeId,empDataId ");
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
    public List<PayrollHistoryReportModel> getByParamForPayrollHistoryReport(ReportPayrollHistoryViewSearchParameter searchParameter, int firstResult, int maxResults, Order order) {

       final StringBuilder query = new StringBuilder(" SELECT logMonthEndPayroll.id AS id, "
       			+ " logMonthEndPayroll.periodeStart AS tglAwalPeriode,"
       			+ " logMonthEndPayroll.periodeEnd AS tglAkhirPeriode, "
                + " DATE_FORMAT(logMonthEndPayroll.periodeStart, '%d %M %Y') AS tglAwalPeriodeInString,"
                + " logMonthEndPayroll.periodeId AS periodeId,"
                + " COUNT(logMonthEndPayroll.empDataId) AS jumlahKaryawan,"
                + " SUM(logMonthEndPayroll.nominal) AS nominal FROM LogMonthEndPayroll logMonthEndPayroll "
                + " WHERE logMonthEndPayroll.paySalaryCompId = 100 ");
        
        query.append(doSearchPayrollHistoryViewByParam(searchParameter));
        query.append(" GROUP BY logMonthEndPayroll.periodeId ");
        query.append(" ORDER BY ").append(order);
        
        Query hbm = getCurrentSession().createQuery(query.toString());
        hbm = this.setValueQueryPayrollHistoryViewByParam(hbm, searchParameter);
        
        List<PayrollHistoryReportModel> listResult = hbm              
                .setMaxResults(maxResults).setFirstResult(firstResult)
                .setResultTransformer(Transformers.aliasToBean(PayrollHistoryReportModel.class))
                .list();
        
        return listResult;
        
      
    }

    @Override
    public Long getTotalByParamForPayrollHistoryReport(ReportPayrollHistoryViewSearchParameter searchParameter) {
    	
    	final StringBuilder query = new StringBuilder("SELECT COUNT(DISTINCT logMonthEndPayroll.periodeId) "
                + " FROM LogMonthEndPayroll logMonthEndPayroll "
                + " WHERE logMonthEndPayroll.paySalaryCompId = 100 ");
        
        query.append(doSearchPayrollHistoryViewByParam(searchParameter));
        Query hbm = getCurrentSession().createQuery(query.toString());
        
        hbm = this.setValueQueryPayrollHistoryViewByParam(hbm, searchParameter);
        return Long.valueOf(hbm.uniqueResult().toString());
    }
    
    private String doSearchPayrollHistoryViewByParam(ReportPayrollHistoryViewSearchParameter searchParameter) {
    	StringBuilder query = new StringBuilder();
    	
    	if (StringUtils.isNotBlank(searchParameter.getTahun()) && !StringUtils.equals(searchParameter.getTahun(), "0")) {
            query.append(" AND DATE_FORMAT(logMonthEndPayroll.periodeStart, '%Y') = :tahun ");
        }
    	
    	if (searchParameter.getBulan() != null && StringUtils.isNotBlank(String.valueOf(searchParameter.getBulan())) && searchParameter.getBulan() != 0) {
            query.append(" AND DATE_FORMAT(logMonthEndPayroll.periodeStart, '%c') = :bulan ");
        }
    	
        return query.toString();
    }
    
    private Query setValueQueryPayrollHistoryViewByParam(Query hbm, ReportPayrollHistoryViewSearchParameter parameter) {
        for (String param : hbm.getNamedParameters()) {
            if (StringUtils.equals(param, "tahun")) {
                hbm.setParameter("tahun", parameter.getTahun());
            }
            
            if (StringUtils.equals(param, "bulan")) {
                hbm.setParameter("bulan", String.valueOf(parameter.getBulan()));
            }
        }
        return hbm;
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

	@Override
	public List<ReportSalaryNoteModel> getByParamForReportSalaryNote(ReportSalaryNoteSearchParameter parameter, int firstResult, int maxResults, Order order) {
		
		StringBuffer selectQuery = new StringBuffer(
    			  "SELECT empDataId AS empDataId, "
    			+ "periodeId AS periodId, "
    			+ "empNik AS empNik, "
    			+ "empName AS empName, "
    			+ "periodeStart AS periodStart, "
    			+ "periodeEnd AS periodEnd, "
    			+ "empGolJabatan AS empGolJab, "
    			+ "SUM(CASE WHEN modelCompSpecific = 0 THEN nominal ELSE 0.0 END) AS basicSalary, "
    			+ "SUM(CASE WHEN factor = 1 and modelCompSpecific != 0 THEN nominal ELSE 0.0 END) AS income, "
    			+ "SUM(CASE WHEN factor = -1 THEN nominal ELSE 0.0 END) AS deduction,  "
    			+ "SUM(CASE WHEN modelCompSpecific = 2 THEN nominal ELSE 0.0 END) AS tax, "
    			+ "SUM(CASE WHEN modelCompSpecific = 100 THEN nominal ELSE 0.0 END) AS takeHomePay  "
    			+ "FROM LogMonthEndPayroll ");    	
    	selectQuery.append(this.getWhereQueryByParamForReportSalaryNote(parameter));
    	selectQuery.append("GROUP BY periodeId,empDataId ");
    	selectQuery.append("ORDER BY " + order);
        
    	Query hbm = getCurrentSession().createQuery(selectQuery.toString()).setMaxResults(maxResults).setFirstResult(firstResult)
                	.setResultTransformer(Transformers.aliasToBean(ReportSalaryNoteModel.class));
    	hbm = this.setValueQueryByParamForReportSalaryNote(hbm, parameter);
    	
    	return hbm.list();
	}

	@Override
	public Long getTotalByParamForReportSalaryNote(ReportSalaryNoteSearchParameter parameter) {
		StringBuffer selectQuery = new StringBuffer(
    			"SELECT id "
    			+ "FROM LogMonthEndPayroll ");    	
    	selectQuery.append(this.getWhereQueryByParamForReportSalaryNote(parameter));
    	selectQuery.append("GROUP BY periodeId,empDataId ");
    	
    	Query hbm = getCurrentSession().createQuery(selectQuery.toString());    	
    	hbm = this.setValueQueryByParamForReportSalaryNote(hbm, parameter);
    	
        return Long.valueOf(hbm.list().size());
	}
	
	private String getWhereQueryByParamForReportSalaryNote(ReportSalaryNoteSearchParameter parameter) {
    	StringBuffer whereQuery = new StringBuffer();
    	
    	if (!parameter.getListGolJab().isEmpty()) {
    		if(StringUtils.isNotEmpty(whereQuery)){
    			whereQuery.append("AND ");
    		}
    		whereQuery.append("empGolJabatan IN (:empGolJabatan) ");
        }    	
    	if (!parameter.getListDepartment().isEmpty()) {
    		if(StringUtils.isNotEmpty(whereQuery)){
    			whereQuery.append("AND ");
    		}
    		whereQuery.append("departmentId IN (:departmentId) ");
        }    
    	if (!parameter.getListEmpType().isEmpty()) {
    		if(StringUtils.isNotEmpty(whereQuery)){
    			whereQuery.append("AND ");
    		}
    		whereQuery.append("empTypeId IN (:empTypeId) ");
        }
        if (parameter.getPeriodeId() != null) {
        	if(StringUtils.isNotEmpty(whereQuery)){
    			whereQuery.append("AND ");
    		}
        	whereQuery.append("periodeId = :periodeId ");
        }       
        
        return StringUtils.isNotEmpty(whereQuery) ? "WHERE " + whereQuery.toString() : whereQuery.toString();
    } 
	
	private Query setValueQueryByParamForReportSalaryNote(Query hbm, ReportSalaryNoteSearchParameter parameter){
    	
    	for(String param : hbm.getNamedParameters()){
    		if(StringUtils.equals(param, "empGolJabatan")){
    			hbm.setParameterList("empGolJabatan", parameter.getListGolJab());
    		} else if(StringUtils.equals(param, "empTypeId")){
    			hbm.setParameterList("empTypeId", parameter.getListEmpType());
    		} else if(StringUtils.equals(param, "departmentId")){
    			hbm.setParameterList("departmentId", parameter.getListDepartment());
    		} else if(StringUtils.equals(param, "periodeId")){
    			hbm.setParameter("periodeId", parameter.getPeriodeId());
    		}
    	}
    	
    	return hbm;
    }

	@Override
	public List<LogMonthEndPayroll> getEntityByEmpDataIdAndPeriodIdAndCompSpecific(Long empDataId, Long periodId, Integer specific) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("empDataId", empDataId));
		criteria.add(Restrictions.eq("periodeId", periodId));
		criteria.add(Restrictions.eq("modelCompSpecific", specific));
		return criteria.list();
	}
	
	@Override
	public LogMonthEndPayroll getEntityByEmpDataIdAndPeriodIdAndPaySalaryCompId(Long empDataId, Long periodId, Long paySalaryCompId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("empDataId", empDataId));
		criteria.add(Restrictions.eq("periodeId", periodId));
		criteria.add(Restrictions.eq("paySalaryCompId", paySalaryCompId));
		return (LogMonthEndPayroll) criteria.uniqueResult();
	}

    @Override
    public List<ReportDataKomponenModel> getReportDataKomponenByParam(ReportDataComponentSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        /*
        Mekanismenya, select data komponen baik yang reguler maupun non reguler, lalu di gabung dengan union.
        Data komponen Reguler dari table log_month_end_payroll, dan Unreguler dari table log_unreg_payroll
        Sebelum di gabung di lakukan filter terlebih dahulu berdasarkan searchParameter.
        */
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        //Flag Untuk penanda apakah ada filter atau tidak
        boolean isFiltered = !searchParameter.getListDepartmentId().isEmpty() || !searchParameter.getListPaySalaryCompId().isEmpty() || 
                !searchParameter.getListGolJabatanId().isEmpty() || !searchParameter.getListEmployeeTypeId().isEmpty() || 
                null != searchParameter.getStartDate() || null != searchParameter.getEndDate();
        
        //Flag untuk penanda jika filter lebih dari satu
        boolean multipleFilterUnregSalary = Boolean.FALSE;
        boolean multipleFilterRegSalary = Boolean.FALSE;
        
        // Query for UnRequler Payroll
        final StringBuilder query = new StringBuilder("SELECT lup.id AS id, "
                + " CONCAT(lup.unreg_salary_name,'  ', MONTHNAME(wp.payroll_date) , ' - ', wp.tahun) "
                + " AS namaProsesGaji, "
                + " lup.department_name AS department, " 
                + " CONCAT(lup.emp_jabatan_code,'  ', lup.emp_jabatan_name) AS jabatan, " 
                + " lup.emp_gol_jabatan AS golonganJabatan, " 
                + " CONCAT(lup.emp_nik,'  ', lup.emp_name) AS karyawan, " 
                + " CONCAT(lup.pay_salary_comp_code,'  ', lup.pay_salary_desc) AS namaKomponen, " 
                + " lup.nominal AS nominal " 
                + " FROM log_unreg_payroll lup INNER JOIN "
                + " unreg_salary us ON lup.unreg_salary_id = us.id " 
                + " INNER JOIN wt_periode wp ON us.based_period_id = wp.id ");
        
        /* Begin Filtering for UnReguler Payroll */
        
        if(isFiltered){
            query.append(" WHERE ");
        }
        
        
        if(!searchParameter.getListDepartmentId().isEmpty()){            
            query.append(" lup.department_id IN( ");            
            
            int size = searchParameter.getListDepartmentId().size();
            //karena pakai native query, isi List harus di parsing satu per satu
            for(int i = 0; i<size ; i++){
                if (i<(size -1)){
                    query.append(String.valueOf(searchParameter.getListDepartmentId().get(i)));
                    query.append(" , ");
                }else{
                    query.append(String.valueOf(searchParameter.getListDepartmentId().get(i)));
                }
            }
            
            query.append(") ");
            multipleFilterUnregSalary = Boolean.TRUE;
        }
        
        if(!searchParameter.getListPaySalaryCompId().isEmpty()){
            if(multipleFilterUnregSalary){  
                query.append(" AND lup.pay_salary_comp_id IN( ");                      
            }else{               
                query.append(" lup.pay_salary_comp_id IN( ");     
                multipleFilterUnregSalary = Boolean.TRUE;
            }
            
            //karena pakai native query, isi List harus di parsing satu per satu
            int size = searchParameter.getListPaySalaryCompId().size();
            for(int i = 0; i<size ; i++){
                if (i<(size -1)){
                    query.append(String.valueOf(searchParameter.getListPaySalaryCompId().get(i)));
                    query.append(" , ");
                }else{
                    query.append(String.valueOf(searchParameter.getListPaySalaryCompId().get(i)));
                }
            }
            
            query.append(") ");               
        }
        
        if(!searchParameter.getListGolJabatanId().isEmpty()){
            if(multipleFilterUnregSalary){  
                query.append(" AND lup.emp_gol_jabatan IN( ");                      
            }else{               
                query.append(" lup.emp_gol_jabatan IN( ");     
                multipleFilterUnregSalary = Boolean.TRUE;
            }
            
            //karena pakai native query, isi List harus di parsing satu per satu
            int size = searchParameter.getListGolJabatanId().size();
            for(int i = 0; i<size ; i++){
                if (i<(size -1)){
                    query.append("'");
                    query.append(String.valueOf(searchParameter.getListGolJabatanId().get(i)));
                     query.append("'");
                    query.append(" , ");
                }else{
                    query.append("'");
                    query.append(String.valueOf(searchParameter.getListGolJabatanId().get(i)));
                    query.append("'");
                }
            }
            
            query.append(") ");               
        }
        
        if(!searchParameter.getListEmployeeTypeId().isEmpty()){
            if(multipleFilterUnregSalary){  
                query.append(" AND lup.emp_type_id IN( ");                    
            }else{               
                 query.append(" lup.emp_type_id IN( ");   
                multipleFilterUnregSalary = Boolean.TRUE;
            }
            
            //karena pakai native query, isi List harus di parsing satu per satu
            int size = searchParameter.getListEmployeeTypeId().size();
            for(int i = 0; i<size ; i++){
                if (i<(size -1)){
                    query.append(String.valueOf(searchParameter.getListEmployeeTypeId().get(i)));
                    query.append(" , ");
                }else{
                    query.append(String.valueOf(searchParameter.getListEmployeeTypeId().get(i)));
                }
            }
            
            query.append(") ");               
        }
        
        if(null != searchParameter.getStartDate()){
            if(multipleFilterUnregSalary){ 
                query.append(" AND lup.periode_start >= '");
                query.append(dateFormat.format(searchParameter.getStartDate()));
                query.append("' ");
                
            }else{
               query.append(" lup.periode_start >= '");
               query.append(dateFormat.format(searchParameter.getStartDate()));
               query.append("' ");
                multipleFilterUnregSalary = Boolean.TRUE;
            }
        }
        
        if(null != searchParameter.getEndDate()){
           if(multipleFilterUnregSalary){ 
                query.append(" AND lup.periode_start <= '");
                query.append(dateFormat.format(searchParameter.getEndDate()));
                query.append("' ");
                
            }else{
               query.append(" lup.periode_start <= '");
               query.append(dateFormat.format(searchParameter.getEndDate()));
               query.append("' ");
               multipleFilterUnregSalary = Boolean.TRUE;
            }      
                             
        }
        
        /* End Filtering for UnReguler Payroll */
        
        // Union table
        query.append(" UNION ");
        
        
        // Query for Requler Payroll
        query.append(" SELECT lmep.id as ID, "
                + " CONCAT(lmep.pay_salary_desc,' Reguler ', MONTHNAME(wp.payroll_date),' - ', wp.tahun) "
                + " AS namaProsesGaji , " 
                + " lmep.department_name AS department, " 
                + " CONCAT(lmep.emp_jabatan_code,'  ', lmep.emp_jabatan_name) AS jabatan, "
                + " lmep.emp_gol_jabatan AS golonganJabatan,  " 
                + " CONCAT(lmep.emp_nik,'  ', lmep.emp_name) AS karyawan, " 
                + " CONCAT(lmep.pay_salary_comp_code,'  ', lmep.pay_salary_desc) AS namaKomponen, " 
                + " lmep.nominal AS nominal " 
                + " FROM log_month_end_payroll lmep INNER JOIN " 
                + " wt_periode wp ON lmep.periode_id = wp.id ");
        
       /* Begin Filtering for Reguler Payroll */
        
        if(isFiltered){
            query.append(" WHERE ");
        }
        
        
        if(!searchParameter.getListDepartmentId().isEmpty()){            
            query.append(" lmep.department_id IN( ");            
            
            int size = searchParameter.getListDepartmentId().size();
            //karena pakai native query, isi List harus di parsing satu per satu
            for(int i = 0; i<size ; i++){
                if (i<(size -1)){
                    query.append(String.valueOf(searchParameter.getListDepartmentId().get(i)));
                    query.append(" , ");
                }else{
                    query.append(String.valueOf(searchParameter.getListDepartmentId().get(i)));
                }
            }
            
            query.append(") ");
            multipleFilterRegSalary = Boolean.TRUE;
        }
        
        if(!searchParameter.getListPaySalaryCompId().isEmpty()){
            if(multipleFilterRegSalary){  
                query.append(" AND lmep.pay_salary_comp_id IN( ");                      
            }else{               
                query.append(" lmep.pay_salary_comp_id IN( ");     
                multipleFilterRegSalary = Boolean.TRUE;
            }
            
            //karena pakai native query, isi List harus di parsing satu per satu
            int size = searchParameter.getListPaySalaryCompId().size();
            for(int i = 0; i<size ; i++){
                if (i<(size -1)){
                    query.append(String.valueOf(searchParameter.getListPaySalaryCompId().get(i)));
                    query.append(" , ");
                }else{
                    query.append(String.valueOf(searchParameter.getListPaySalaryCompId().get(i)));
                }
            }
            
            query.append(") ");               
        }
        
        if(!searchParameter.getListGolJabatanId().isEmpty()){
            if(multipleFilterRegSalary){  
                query.append(" AND lmep.emp_gol_jabatan IN( ");                      
            }else{               
                query.append(" lmep.emp_gol_jabatan IN( ");     
                multipleFilterRegSalary = Boolean.TRUE;
            }
            
            //karena pakai native query, isi List harus di parsing satu per satu
            int size = searchParameter.getListGolJabatanId().size();
            for(int i = 0; i<size ; i++){
                if (i<(size -1)){
                    query.append("'");
                    query.append(String.valueOf(searchParameter.getListGolJabatanId().get(i)));
                     query.append("'");
                    query.append(" , ");
                }else{
                    query.append("'");
                    query.append(String.valueOf(searchParameter.getListGolJabatanId().get(i)));
                    query.append("'");
                }
            }
            
            query.append(") ");               
        }
        
        if(!searchParameter.getListEmployeeTypeId().isEmpty()){
            if(multipleFilterRegSalary){  
                query.append(" AND lmep.emp_type_id IN( ");                    
            }else{               
                 query.append(" lmep.emp_type_id IN( ");   
                multipleFilterRegSalary = Boolean.TRUE;
            }
            
            //karena pakai native query, isi List harus di parsing satu per satu
            int size = searchParameter.getListEmployeeTypeId().size();
            for(int i = 0; i<size ; i++){
                if (i<(size -1)){
                    query.append(String.valueOf(searchParameter.getListEmployeeTypeId().get(i)));
                    query.append(" , ");
                }else{
                    query.append(String.valueOf(searchParameter.getListEmployeeTypeId().get(i)));
                }
            }
            
            query.append(") ");               
        }
        
        if(null != searchParameter.getStartDate()){
            if(multipleFilterRegSalary){ 
                query.append(" AND lmep.periode_start >= '");
                query.append(dateFormat.format(searchParameter.getStartDate()));
                query.append("' ");
                
            }else{
               query.append(" lmep.periode_start >= '");
               query.append(dateFormat.format(searchParameter.getStartDate()));
               query.append("' ");
               multipleFilterRegSalary = Boolean.TRUE;
            }
        }
        
        if(null != searchParameter.getEndDate()){
           if(multipleFilterRegSalary){ 
                query.append(" AND lmep.periode_start <= '");
                query.append(dateFormat.format(searchParameter.getEndDate()));
                query.append("' ");
                
            }else{
               query.append(" lmep.periode_start <= '");
               query.append(dateFormat.format(searchParameter.getEndDate()));
               query.append("' ");
               multipleFilterRegSalary = Boolean.TRUE;
            }      
                             
        }        
        /* End Filtering for Reguler Payroll */      
        query.append(" ORDER BY ").append(order);
        
        return getCurrentSession().createSQLQuery(query.toString())
                .addScalar("id", LongType.INSTANCE)
                .addScalar("namaProsesGaji", StringType.INSTANCE)
                .addScalar("department", StringType.INSTANCE)
                .addScalar("jabatan", StringType.INSTANCE)
                .addScalar("golonganJabatan", StringType.INSTANCE)
                .addScalar("karyawan", StringType.INSTANCE)
                .addScalar("namaKomponen", StringType.INSTANCE)
                .addScalar("nominal", BigDecimalType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(ReportDataKomponenModel.class))
                .list();
    }

    @Override
    public Long getTotalReportDataKomponenByParam(ReportDataComponentSearchParameter searchParameter) {
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        //Flag Untuk penanda apakah ada filter atau tidak
        boolean isFiltered = !searchParameter.getListDepartmentId().isEmpty() || !searchParameter.getListPaySalaryCompId().isEmpty() || 
                !searchParameter.getListGolJabatanId().isEmpty() || !searchParameter.getListEmployeeTypeId().isEmpty() || 
                null != searchParameter.getStartDate() || null != searchParameter.getEndDate();
        
        //Flag untuk penanda jika filter lebih dari satu
        boolean multipleFilterUnregSalary = Boolean.FALSE;
        boolean multipleFilterRegSalary = Boolean.FALSE;
        
        
        // Query for UnRequler Payroll
        final StringBuilder query = new StringBuilder("SELECT COUNT(*) FROM (SELECT "
                + " CONCAT(lup.unreg_salary_name,'  ', MONTHNAME(wp.payroll_date) , ' - ', wp.tahun) "
                + " AS namaProsesGaji, "
                + " lup.department_name AS department, " 
                + " CONCAT(lup.emp_jabatan_code,'  ', lup.emp_jabatan_name) AS jabatan, " 
                + " lup.emp_gol_jabatan AS golonganJabatan, " 
                + " CONCAT(lup.emp_nik,'  ', lup.emp_name) AS karyawan, " 
                + " CONCAT(lup.pay_salary_comp_code,'  ', lup.pay_salary_desc) AS namaKomponen, " 
                + " lup.nominal AS nominal " 
                + " FROM log_unreg_payroll lup INNER JOIN "
                + " unreg_salary us ON lup.unreg_salary_id = us.id " 
                + " INNER JOIN wt_periode wp ON us.based_period_id = wp.id ");
        
        /* Begin Filtering for UnReguler Payroll */
        
        if(isFiltered){
            query.append(" WHERE ");
        }
        
        
        if(!searchParameter.getListDepartmentId().isEmpty()){            
            query.append(" lup.department_id IN( ");            
            
            int size = searchParameter.getListDepartmentId().size();
            //karena pakai native query, isi List harus di parsing satu per satu
            for(int i = 0; i<size ; i++){
                if (i<(size -1)){
                    query.append(String.valueOf(searchParameter.getListDepartmentId().get(i)));
                    query.append(" , ");
                }else{
                    query.append(String.valueOf(searchParameter.getListDepartmentId().get(i)));
                }
            }
            
            query.append(") ");
            multipleFilterUnregSalary = Boolean.TRUE;
        }
        
        if(!searchParameter.getListPaySalaryCompId().isEmpty()){
            if(multipleFilterUnregSalary){  
                query.append(" AND lup.pay_salary_comp_id IN( ");                      
            }else{               
                query.append(" lup.pay_salary_comp_id IN( ");     
                multipleFilterUnregSalary = Boolean.TRUE;
            }
            
            //karena pakai native query, isi List harus di parsing satu per satu
            int size = searchParameter.getListPaySalaryCompId().size();
            for(int i = 0; i<size ; i++){
                if (i<(size -1)){
                    query.append(String.valueOf(searchParameter.getListPaySalaryCompId().get(i)));
                    query.append(" , ");
                }else{
                    query.append(String.valueOf(searchParameter.getListPaySalaryCompId().get(i)));
                }
            }
            
            query.append(") ");               
        }
        
        if(!searchParameter.getListGolJabatanId().isEmpty()){
            if(multipleFilterUnregSalary){  
                query.append(" AND lup.emp_gol_jabatan IN( ");                      
            }else{               
                query.append(" lup.emp_gol_jabatan IN( ");     
                multipleFilterUnregSalary = Boolean.TRUE;
            }
            
            //karena pakai native query, isi List harus di parsing satu per satu
            int size = searchParameter.getListGolJabatanId().size();
            for(int i = 0; i<size ; i++){
                if (i<(size -1)){
                    query.append("'");
                    query.append(String.valueOf(searchParameter.getListGolJabatanId().get(i)));
                     query.append("'");
                    query.append(" , ");
                }else{
                    query.append("'");
                    query.append(String.valueOf(searchParameter.getListGolJabatanId().get(i)));
                    query.append("'");
                }
            }
            
            query.append(") ");               
        }
        
        if(!searchParameter.getListEmployeeTypeId().isEmpty()){
            if(multipleFilterUnregSalary){  
                query.append(" AND lup.emp_type_id IN( ");                    
            }else{               
                 query.append(" lup.emp_type_id IN( ");   
                multipleFilterUnregSalary = Boolean.TRUE;
            }
            
            //karena pakai native query, isi List harus di parsing satu per satu
            int size = searchParameter.getListEmployeeTypeId().size();
            for(int i = 0; i<size ; i++){
                if (i<(size -1)){
                    query.append(String.valueOf(searchParameter.getListEmployeeTypeId().get(i)));
                    query.append(" , ");
                }else{
                    query.append(String.valueOf(searchParameter.getListEmployeeTypeId().get(i)));
                }
            }
            
            query.append(") ");               
        }
        
        if(null != searchParameter.getStartDate()){
            if(multipleFilterUnregSalary){ 
                query.append(" AND lup.periode_start >= '");
                query.append(dateFormat.format(searchParameter.getStartDate()));
                query.append("' ");
                
            }else{
               query.append(" lup.periode_start >= '");
               query.append(dateFormat.format(searchParameter.getStartDate()));
               query.append("' ");
               multipleFilterUnregSalary = Boolean.TRUE;
            }
        }
        
        if(null != searchParameter.getEndDate()){
           if(multipleFilterUnregSalary){ 
                query.append(" AND lup.periode_start <= '");
                query.append(dateFormat.format(searchParameter.getEndDate()));
                query.append("' ");
                
            }else{
               query.append(" lup.periode_start <= '");
               query.append(dateFormat.format(searchParameter.getEndDate()));
               query.append("' ");
               multipleFilterUnregSalary = Boolean.TRUE;
            }      
                             
        }
        
        /* End Filtering for UnReguler Payroll */
        
        // Union table
        query.append(" UNION ");
        
        
        // Query for Requler Payroll
        query.append(" SELECT CONCAT(lmep.pay_salary_desc,' Reguler ', MONTHNAME(wp.payroll_date),' - ', wp.tahun) "
                + " AS namaProsesGaji , " 
                + " lmep.department_name AS department, " 
                + " CONCAT(lmep.emp_jabatan_code,'  ', lmep.emp_jabatan_name) AS jabatan, "
                + " lmep.emp_gol_jabatan AS golonganJabatan,  " 
                + " CONCAT(lmep.emp_nik,'  ', lmep.emp_name) AS karyawan, " 
                + " CONCAT(lmep.pay_salary_comp_code,'  ', lmep.pay_salary_desc) AS namaKomponen, " 
                + " lmep.nominal AS nominal " 
                + " FROM log_month_end_payroll lmep INNER JOIN " 
                + " wt_periode wp ON lmep.periode_id = wp.id ");
        
       /* Begin Filtering for Reguler Payroll */
        
        if(isFiltered){
            query.append(" WHERE ");
        }
        
        
        if(!searchParameter.getListDepartmentId().isEmpty()){            
            query.append(" lmep.department_id IN( ");            
            
            int size = searchParameter.getListDepartmentId().size();
            //karena pakai native query, isi List harus di parsing satu per satu
            for(int i = 0; i<size ; i++){
                if (i<(size -1)){
                    query.append(String.valueOf(searchParameter.getListDepartmentId().get(i)));
                    query.append(" , ");
                }else{
                    query.append(String.valueOf(searchParameter.getListDepartmentId().get(i)));
                }
            }
            
            query.append(") ");
            multipleFilterRegSalary = Boolean.TRUE;
        }
        
        if(!searchParameter.getListPaySalaryCompId().isEmpty()){
            if(multipleFilterRegSalary){  
                query.append(" AND lmep.pay_salary_comp_id IN( ");                      
            }else{               
                query.append(" lmep.pay_salary_comp_id IN( ");     
                multipleFilterRegSalary = Boolean.TRUE;
            }
            
            //karena pakai native query, isi List harus di parsing satu per satu
            int size = searchParameter.getListPaySalaryCompId().size();
            for(int i = 0; i<size ; i++){
                if (i<(size -1)){
                    query.append(String.valueOf(searchParameter.getListPaySalaryCompId().get(i)));
                    query.append(" , ");
                }else{
                    query.append(String.valueOf(searchParameter.getListPaySalaryCompId().get(i)));
                }
            }
            
            query.append(") ");               
        }
        
        if(!searchParameter.getListGolJabatanId().isEmpty()){
            if(multipleFilterRegSalary){  
                query.append(" AND lmep.emp_gol_jabatan IN( ");                      
            }else{               
                query.append(" lmep.emp_gol_jabatan IN( ");     
                multipleFilterRegSalary = Boolean.TRUE;
            }
            
            //karena pakai native query, isi List harus di parsing satu per satu
            int size = searchParameter.getListGolJabatanId().size();
            for(int i = 0; i<size ; i++){
                if (i<(size -1)){
                    query.append("'");
                    query.append(String.valueOf(searchParameter.getListGolJabatanId().get(i)));
                     query.append("'");
                    query.append(" , ");
                }else{
                    query.append("'");
                    query.append(String.valueOf(searchParameter.getListGolJabatanId().get(i)));
                    query.append("'");
                }
            }
            
            query.append(") ");               
        }
        
        if(!searchParameter.getListEmployeeTypeId().isEmpty()){
            if(multipleFilterRegSalary){  
                query.append(" AND lmep.emp_type_id IN( ");                    
            }else{               
                 query.append(" lmep.emp_type_id IN( ");   
                multipleFilterRegSalary = Boolean.TRUE;
            }
            
            //karena pakai native query, isi List harus di parsing satu per satu
            int size = searchParameter.getListEmployeeTypeId().size();
            for(int i = 0; i<size ; i++){
                if (i<(size -1)){
                    query.append(String.valueOf(searchParameter.getListEmployeeTypeId().get(i)));
                    query.append(" , ");
                }else{
                    query.append(String.valueOf(searchParameter.getListEmployeeTypeId().get(i)));
                }
            }
            
            query.append(") ");               
        }
        
        if(null != searchParameter.getStartDate()){
            if(multipleFilterRegSalary){ 
                query.append(" AND lmep.periode_start >= '");
                query.append(dateFormat.format(searchParameter.getStartDate()));
                query.append("' ");
                
            }else{
               query.append(" lmep.periode_start >= '");
               query.append(dateFormat.format(searchParameter.getStartDate()));
               query.append("' ");
               multipleFilterRegSalary = Boolean.TRUE;
            }
        }
        
        if(null != searchParameter.getEndDate()){
           if(multipleFilterRegSalary){ 
                query.append(" AND lmep.periode_start <= '");
                query.append(dateFormat.format(searchParameter.getEndDate()));
                query.append("' ");
                
            }else{
               query.append(" lmep.periode_start <= '");
               query.append(dateFormat.format(searchParameter.getEndDate()));
               query.append("' ");
               multipleFilterRegSalary = Boolean.TRUE;
            }      
                             
        }
        
        /* End Filtering for Reguler Payroll */
       
        
        query.append(" ) AS jumlahRow ");          
        return Long.valueOf(getCurrentSession().createSQLQuery(query.toString()).uniqueResult().toString());
    }

	@Override
	public Collection<Long> getAllDataEmpIdByParam(ReportSalaryNoteSearchParameter searchParameter) {
		StringBuffer selectQuery = new StringBuffer(
  			  "SELECT empDataId AS empDataId "
  			+ "FROM LogMonthEndPayroll ");    	
	  	selectQuery.append(this.getWhereQueryByParamForReportSalaryNote(searchParameter));
	  	selectQuery.append("GROUP BY periodeId,empDataId ");
		
	  	Query hbm = getCurrentSession().createQuery(selectQuery.toString());
	  	hbm = this.setValueQueryByParamForReportSalaryNote(hbm, searchParameter);
	
	  	return hbm.list();
	}

	@Override
	public List<LogMonthEndPayroll> getListByEmpDataIdAndWtPeriodId(Long empDataId, Long wtPeriodeId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("empDataId", empDataId));
        criteria.add(Restrictions.eq("periodeId", wtPeriodeId));
        return criteria.list();
	}
    

}
