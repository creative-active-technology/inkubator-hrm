/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.TempAttendanceRealizationDao;
import com.inkubator.hrm.entity.TempAttendanceRealization;
import com.inkubator.hrm.web.model.TempAttendanceRealizationViewModel;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@Repository(value = "tempAttendanceRealizationDao")
@Lazy
public class TempAttendanceRealizationDaoImpl extends IDAOImpl<TempAttendanceRealization> implements TempAttendanceRealizationDao {

    @Override
    public Class<TempAttendanceRealization> getEntityClass() {
         return TempAttendanceRealization.class;
    }

    @Override
    public List<TempAttendanceRealizationViewModel> getListTempAttendanceRealizationViewModelByWtPeriodId(Long wtPeriodId, int firstResult, int maxResults, Order orderable) {
       
        final StringBuilder query = new StringBuilder("SELECT tempAttendanceRealization.id as id,");
        query.append(" wtPeriod.id AS wtPeriodId,");
        query.append(" wtGroupWorking.id AS wtGroupWorkingId,");
        query.append(" wtGroupWorking.name AS wtGroupWorkingName,");
        query.append(" empData.id AS empId,");
        query.append(" empData.nik AS nik,");
        query.append(" CONCAT(bioData.firstName, ' ', bioData.lastName)  AS empName,");      
        query.append(" tempAttendanceRealization.attendanceDaysSchedule AS attendanceDaysSchedule,");
        query.append(" tempAttendanceRealization.attendanceDaysPresent AS attendanceDaysPresent,");
        query.append(" tempAttendanceRealization.leave AS leaves,");
        query.append(" tempAttendanceRealization.overtime AS overTime,");
        query.append(" tempAttendanceRealization.permit AS permit,");
        query.append(" tempAttendanceRealization.sick AS sick,");
        query.append(" tempAttendanceRealization.duty AS duty,");
        query.append(" wtPeriod.absen AS absenStatus");
        query.append(" FROM TempAttendanceRealization tempAttendanceRealization");
        query.append(" INNER JOIN tempAttendanceRealization.empData empData");
        query.append(" INNER JOIN empData.bioData bioData");
        query.append(" INNER JOIN tempAttendanceRealization.wtGroupWorking wtGroupWorking");
        query.append(" INNER JOIN tempAttendanceRealization.wtPeriod wtPeriod");
        query.append(" WHERE wtPeriod.id = :wtPeriodId ");
        
         return getCurrentSession().createQuery(query.toString())
                    .setParameter("wtPeriodId", wtPeriodId)                   
                    .setMaxResults(maxResults).setFirstResult(firstResult)                
                    .setResultTransformer(Transformers.aliasToBean(TempAttendanceRealizationViewModel.class))
                    .list();
        
    }

    @Override
    public Long getTotalListTempAttendanceRealizationViewModelByWtPeriodId(Long wtPeriodId) {
        final StringBuilder query = new StringBuilder("SELECT COUNT(*) ");       
        query.append(" FROM TempAttendanceRealization tempAttendanceRealization");
        query.append(" INNER JOIN tempAttendanceRealization.empData empData");
        query.append(" INNER JOIN empData.bioData bioData");
        query.append(" INNER JOIN tempAttendanceRealization.wtGroupWorking wtGroupWorking");
        query.append(" INNER JOIN tempAttendanceRealization.wtPeriod wtPeriod");
        query.append(" WHERE wtPeriod.id = :wtPeriodId ");
        
          Query hbm = getCurrentSession().createQuery(query.toString())
                        .setParameter("wtPeriodId", wtPeriodId);
        return Long.valueOf(hbm.uniqueResult().toString());
    }

	@Override
	public void deleteAllData() {
		  Query query = getCurrentSession().createQuery("delete from TempAttendanceRealization");
	        query.executeUpdate();
	}
    
}
