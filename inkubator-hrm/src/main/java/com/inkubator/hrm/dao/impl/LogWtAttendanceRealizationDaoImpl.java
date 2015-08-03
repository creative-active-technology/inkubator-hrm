/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.LogWtAttendanceRealizationDao;
import com.inkubator.hrm.entity.LogWtAttendanceRealization;
import com.inkubator.hrm.web.model.TempAttendanceRealizationViewModel;
import com.inkubator.hrm.web.search.WtAttendanceCalculationSearchParameter;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@Repository(value = "logWtAttendanceRealizationDao")
@Lazy
public class LogWtAttendanceRealizationDaoImpl extends IDAOImpl<LogWtAttendanceRealization> implements LogWtAttendanceRealizationDao {

    @Override
    public Class<LogWtAttendanceRealization> getEntityClass() {
        return LogWtAttendanceRealization.class;
    }

    @Override
    public List<TempAttendanceRealizationViewModel> getListTempAttendanceRealizationViewModelByWtPeriodId(WtAttendanceCalculationSearchParameter searchParameter, Long wtPeriodId, int firstResult, int maxResults, Order orderable) {
        final StringBuilder query = new StringBuilder("SELECT logAttendanceRealization.id as id,");
        query.append(" logAttendanceRealization.wtPeriodeId AS wtPeriodId,");
        query.append(" logAttendanceRealization.wtGroupWorkingId AS wtGroupWorkingId,");
        query.append(" logAttendanceRealization.wtGroupWorkingName AS wtGroupWorkingName,");
        query.append(" logAttendanceRealization.empDataId AS empId,");
        query.append(" logAttendanceRealization.empNik AS nik,");
        query.append(" logAttendanceRealization.empName AS empName,");       
        query.append(" logAttendanceRealization.attendanceDaysSchedule AS attendanceDaysSchedule,");
        query.append(" logAttendanceRealization.attendanceDaysPresent AS attendanceDaysPresent,");
        query.append(" logAttendanceRealization.leaves AS leaves,");
        query.append(" logAttendanceRealization.overtime AS overTime,");
        query.append(" logAttendanceRealization.permit AS permit,");
        query.append(" logAttendanceRealization.sick AS sick,");
        query.append(" logAttendanceRealization.duty AS duty");       
        query.append(" FROM LogWtAttendanceRealization logAttendanceRealization");        
        query.append(" WHERE logAttendanceRealization.wtPeriodeId = :wtPeriodId ");
        
      //filter by search param
        query.append(doSearchLogWtAttendanceCalculationByParam(searchParameter));
        
        Query hbm = getCurrentSession().createQuery(query.toString())
        		.setParameter("wtPeriodId", wtPeriodId);
        hbm = this.setValueQueryLogWtAttendanceCalculationByParam(hbm, searchParameter);
        
        return 	hbm                 
                .setMaxResults(maxResults).setFirstResult(firstResult)                
                .setResultTransformer(Transformers.aliasToBean(TempAttendanceRealizationViewModel.class))
                .list();
    }

    @Override
    public Long getTotalListTempAttendanceRealizationViewModelByWtPeriodId(WtAttendanceCalculationSearchParameter searchParameter, Long wtPeriodId) {
         final StringBuilder query = new StringBuilder("SELECT COUNT(*) ");       
        query.append(" FROM LogWtAttendanceRealization logAttendanceRealization");        
        query.append(" WHERE logAttendanceRealization.wtPeriodeId = :wtPeriodId ");
        
        //filter by search param
        query.append(doSearchLogWtAttendanceCalculationByParam(searchParameter));
        
         Query hbm = getCurrentSession().createQuery(query.toString())
                       .setParameter("wtPeriodId", wtPeriodId);
         hbm = this.setValueQueryLogWtAttendanceCalculationByParam(hbm, searchParameter);
        return Long.valueOf(hbm.uniqueResult().toString());
    }
    
    private String doSearchLogWtAttendanceCalculationByParam(WtAttendanceCalculationSearchParameter searchParameter) {
    	StringBuilder query = new StringBuilder();
    	
    	if(!StringUtils.equals(searchParameter.getNik(), null)){
    		query.append(" AND logAttendanceRealization.empNik LIKE :nik ");
    	}
    	
    	if(!StringUtils.equals(searchParameter.getEmpName(), null)){
    		query.append(" AND logAttendanceRealization.empName LIKE :empName ");
    	}
    	
    	if(!StringUtils.equals(searchParameter.getWtGroupWorkingName(), null)){
    		query.append(" AND logAttendanceRealization.wtGroupWorkingName LIKE :wtGroupWorkingName  ");
    	}    	
    	
    	
    	return query.toString();
    }
    
    private Query setValueQueryLogWtAttendanceCalculationByParam(Query hbm, WtAttendanceCalculationSearchParameter parameter){    	
    	for(String param : hbm.getNamedParameters()){
    		if(StringUtils.equals(param, "empName")){
    			hbm.setParameter("empName", "%" + parameter.getEmpName() + "%");
    		} else if(StringUtils.equals(param, "nik")){
    			hbm.setParameter("nik", "%" + parameter.getNik() + "%");
    		} else if(StringUtils.equals(param, "wtGroupWorkingName")){
    			hbm.setParameter("wtGroupWorkingName", "%" + parameter.getWtGroupWorkingName() + "%");
    		} 
    	}    	
    	return hbm;
    }


	@Override
	public void deleteByPeriodId(Long periodId) {
		Query query = getCurrentSession().createQuery("DELETE FROM LogWtAttendanceRealization temp WHERE temp.wtPeriodeId = :periodId")
				.setLong("periodId", periodId);
        query.executeUpdate();
		
	}

	@Override
	public List<LogWtAttendanceRealization> getAllDataByPeriodId(Long wtPeriodId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("wtPeriodeId", wtPeriodId));
		return criteria.list();
	}
    
}
