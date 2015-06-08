/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.LogAttendanceRealizationDao;
import com.inkubator.hrm.entity.LogAttendanceRealization;
import com.inkubator.hrm.web.model.TempAttendanceRealizationViewModel;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.transform.Transformers;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@Repository(value = "logAttendanceRealizationDao")
@Lazy
public class LogAttendanceRealizationDaoImpl extends IDAOImpl<LogAttendanceRealization> implements LogAttendanceRealizationDao {

    @Override
    public Class<LogAttendanceRealization> getEntityClass() {
        return LogAttendanceRealization.class;
    }

    @Override
    public List<TempAttendanceRealizationViewModel> getListTempAttendanceRealizationViewModelByWtPeriodId(Long wtPeriodId, int firstResult, int maxResults, Order orderable) {
        final StringBuilder query = new StringBuilder("SELECT logAttendanceRealization.id as id,");
        query.append(" logAttendanceRealization.wtPeriodeId AS wtPeriodId,");
        query.append(" logAttendanceRealization.wtGroupWorkingId AS wtGroupWorkingId,");
        query.append(" logAttendanceRealization.wtGroupWorkingName AS wtGroupWorkingName,");
        query.append(" logAttendanceRealization.empDataId AS empId,");
        query.append(" logAttendanceRealization.empNik AS nik,");
        query.append(" logAttendanceRealization.empName AS empName,");       
        query.append(" logAttendanceRealization.attendanceDaysSchedule AS attendanceDaysSchedule,");
        query.append(" logAttendanceRealization.attendanceDaysPresent AS attendanceDaysPresent,");
        query.append(" logAttendanceRealization.leave AS leaves,");
        query.append(" logAttendanceRealization.overtime AS overTime,");
        query.append(" logAttendanceRealization.permit AS permit,");
        query.append(" logAttendanceRealization.sick AS sick,");
        query.append(" logAttendanceRealization.duty AS duty");       
        query.append(" FROM LogAttendanceRealization logAttendanceRealization");        
        query.append(" WHERE logAttendanceRealization.wtPeriodeId = :wtPeriodId ");
        
         return getCurrentSession().createQuery(query.toString())
                    .setParameter("wtPeriodId", wtPeriodId.intValue())                   
                    .setMaxResults(maxResults).setFirstResult(firstResult)                
                    .setResultTransformer(Transformers.aliasToBean(TempAttendanceRealizationViewModel.class))
                    .list();
    }

    @Override
    public Long getTotalListTempAttendanceRealizationViewModelByWtPeriodId(Long wtPeriodId) {
         final StringBuilder query = new StringBuilder("SELECT COUNT(*) ");       
        query.append(" FROM LogAttendanceRealization logAttendanceRealization");        
        query.append(" WHERE logAttendanceRealization.wtPeriodeId = :wtPeriodId ");
        
          Query hbm = getCurrentSession().createQuery(query.toString())
                        .setParameter("wtPeriodId", wtPeriodId.intValue());
        return Long.valueOf(hbm.uniqueResult().toString());
    }
    
}
