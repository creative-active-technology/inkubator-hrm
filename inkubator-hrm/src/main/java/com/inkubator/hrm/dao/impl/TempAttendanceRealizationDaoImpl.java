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
import com.inkubator.hrm.web.search.TempAttendanceRealizationSearchParameter;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
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
    public List<TempAttendanceRealization> getByParam(TempAttendanceRealizationSearchParameter parameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchTempAttendanceRealizationByParam(parameter, criteria);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.jabatanByJabatanId", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        return criteria.list();
    }

    @Override
    public Long getTotalTempAttendanceRealizationByParam(TempAttendanceRealizationSearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchTempAttendanceRealizationByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchTempAttendanceRealizationByParam(TempAttendanceRealizationSearchParameter parameter, Criteria criteria) {
        if (parameter.getJabatan() != null) {
            criteria.createAlias("empData", "ce", JoinType.INNER_JOIN);
            criteria.createAlias("ce.jabatanByJabatanId", "jb", JoinType.INNER_JOIN);
            criteria.add(Restrictions.like("jb.name", parameter.getJabatan(), MatchMode.ANYWHERE));
        }

        if (parameter.getName() != null) {
            criteria.createAlias("empData", "ce", JoinType.INNER_JOIN);
            criteria.createAlias("ce.bioData", "bio", JoinType.INNER_JOIN);
            criteria.add(Restrictions.ilike("bio.combineName", parameter.getName().toLowerCase(), MatchMode.ANYWHERE));
        }
        if (parameter.getNik() != null) {
            criteria.createAlias("empData", "ce", JoinType.INNER_JOIN);
            criteria.add(Restrictions.like("ce.nik", parameter.getNik(), MatchMode.ANYWHERE));

        }
        if (parameter.getNoJab() != null) {
            criteria.createAlias("empData", "ce", JoinType.INNER_JOIN);
            criteria.createAlias("ce.jabatanByJabatanId", "jb", JoinType.INNER_JOIN);
            criteria.add(Restrictions.like("jb.code", parameter.getNoJab(), MatchMode.ANYWHERE));

        }
        criteria.add(Restrictions.isNotNull("id"));

    }

    @Override
    public Long getTotalEmpLeav() {           
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		Long result = (Long) criteria.setProjection(Projections.sum("leave")).uniqueResult();
		return result == null ? 0l : result;		
    }

    @Override
    public Long getTotalEmpPermit() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        Long result = (Long) criteria.setProjection(Projections.sum("permit")).uniqueResult();
        return result == null ? 0l : result;		
    }

    @Override
    public Long gettotalEmpOnDuty() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        Long result = (Long) criteria.setProjection(Projections.sum("duty")).uniqueResult();
        return result == null ? 0l : result;	
    }

    @Override
    public Long gettotalEmpOnSick() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        Long result = (Long) criteria.setProjection(Projections.sum("sick")).uniqueResult();
        return result == null ? 0l : result;
    }

    @Override
    public Long getTotalEmpLeav(long empId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("empData", "ce", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("ce.id", empId));
        Long result = (Long) criteria.setProjection(Projections.sum("leave")).uniqueResult();
        return result == null ? 0l : result;
    }

    @Override
    public Long getTotalEmpPermit(long empId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("empData", "ce", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("ce.id", empId));
        Long result = (Long) criteria.setProjection(Projections.sum("permit")).uniqueResult();
        return result == null ? 0l : result;
    }

    @Override
    public Long gettotalEmpOnDuty(long empId
    ) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("empData", "ce", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("ce.id", empId));
        Long result = (Long) criteria.setProjection(Projections.sum("duty")).uniqueResult();
        return result == null ? 0l : result;
    }

    @Override
    public Long gettotalEmpOnSick(long empId
    ) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("empData", "ce", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("ce.id", empId));
        Long result = (Long) criteria.setProjection(Projections.sum("sick")).uniqueResult();
        return result == null ? 0l : result;
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

    @Override
    public Long totalDayPresent() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        Long result = (Long) criteria.setProjection(Projections.sum("attendanceDaysPresent")).uniqueResult();
        return result == null ? 0l : result;
    }

    @Override
    public Long totalDaySchedule() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        Long result = (Long) criteria.setProjection(Projections.sum("attendanceDaysSchedule")).uniqueResult();
        return result == null ? 0l : result;
    }

    @Override
    public Long getTotalOverTime(long empId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("empData", "ce", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("ce.id", empId));
        Long result = (Long) criteria.setProjection(Projections.sum("overtime")).uniqueResult();
        return result == null ? 0l : result;
    }

    @Override
    public TempAttendanceRealization getByEmp(long empId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("empData", "ce", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("ce.id", empId));
        return (TempAttendanceRealization) criteria.uniqueResult();
    }
}
