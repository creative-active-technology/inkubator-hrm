/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.ImplementationOfOverTimeDao;
import com.inkubator.hrm.entity.ImplementationOfOverTime;
import com.inkubator.hrm.web.model.OvertimeImplSearchingModel;
import com.inkubator.hrm.web.search.ImplementationOfOvertimeSearchParameter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.primefaces.model.DualListModel;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni
 */
@Repository(value = "implementationOfOverTimeDao")
@Lazy
public class ImplementationOfOverTimeDaoImpl extends IDAOImpl<ImplementationOfOverTime> implements ImplementationOfOverTimeDao{

    @Override
    public Class<ImplementationOfOverTime> getEntityClass() {
        return ImplementationOfOverTime.class;
    }

    @Override
    public List<ImplementationOfOverTime> getAllDataWithDetail(ImplementationOfOvertimeSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        //criteria.setFetchMode("empData", FetchMode.JOIN);
        //criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        //criteria.setFetchMode("wtOverTime", FetchMode.JOIN);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalImplementationOfOvertimeByParam(ImplementationOfOvertimeSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public ImplementationOfOverTime getEntityByPkWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("wtOverTime", FetchMode.JOIN);
        return (ImplementationOfOverTime) criteria.uniqueResult();
    }

    @Override
    public Long getByCode(String code) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.like("code", code, MatchMode.ANYWHERE));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }
    
    private void doSearchByParam(ImplementationOfOvertimeSearchParameter searchParameter, Criteria criteria) {
         criteria.createAlias("wtOverTime", "wtOverTime", JoinType.INNER_JOIN);
        criteria.createAlias("empData", "empData", JoinType.INNER_JOIN);
        criteria.createAlias("empData.bioData", "bioData", JoinType.INNER_JOIN);
        if (searchParameter.getEmployeeName()!= null) {
             Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("empData.nik", searchParameter.getEmployeeName(), MatchMode.START));
            disjunction.add(Restrictions.ilike("bioData.combineName", searchParameter.getEmployeeName().toLowerCase(), MatchMode.ANYWHERE));
//            disjunction.add(Restrictions.like("bioData.lastName", searchParameter.getEmployeeName(), MatchMode.START));
            criteria.add(disjunction);
        }
        if (searchParameter.getOverTimeName()!= null) {
             criteria.add(Restrictions.like("wtOverTime.name", searchParameter.getOverTimeName(), MatchMode.ANYWHERE));
        }

        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public ImplementationOfOverTime getEntityByApprovalActivityNumberWithDetail(String activityNumber) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("approvalActivityNumber", activityNumber));
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("wtOverTime", FetchMode.JOIN);
        return (ImplementationOfOverTime) criteria.uniqueResult();
    }
    
    @Override
	public List<ImplementationOfOverTime> getAllEmpOtImplBetweenStartDateAndEndDate(Long empDataId, Date startDate, Date endDate) {
		 	Criteria criteria = getCurrentSession().createCriteria(getEntityClass());	        
	        criteria.setFetchMode("empData", FetchMode.JOIN);
	        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
	        criteria.setFetchMode("wtOverTime", FetchMode.JOIN);
	        criteria.setFetchMode("wtOverTime.wtHitungLembur", FetchMode.JOIN);
	        criteria.add(Restrictions.eq("empData.id", empDataId));
	        criteria.add(Restrictions.ge("implementationDate", startDate));
	        criteria.add(Restrictions.le("implementationDate", endDate));
	        return criteria.list();
	}

    @Override
    public Long getCurrentMaxId() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());        
        return (Long) criteria.setProjection(Projections.max("id")).uniqueResult();
    }

	@Override
	public List<ImplementationOfOverTime> getListSearchByParam(OvertimeImplSearchingModel overtimeImplSearchingModel,
			int firstResult, int maxResults, Order order) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		doSearchForOtImplSearchingByParam(overtimeImplSearchingModel, criteria);
		criteria.addOrder(order);
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResults);
		return criteria.list();
	}

	@Override
	public Long getTotalListSearchByParam(OvertimeImplSearchingModel overtimeImplSearchingModel) {
		 Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		 doSearchForOtImplSearchingByParam(overtimeImplSearchingModel, criteria);
	     return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	private void doSearchForOtImplSearchingByParam(OvertimeImplSearchingModel overtimeImplSearchingModel, Criteria criteria) {
		criteria.createAlias("wtOverTime", "wtOverTime", JoinType.INNER_JOIN);
		criteria.createAlias("empData", "empData", JoinType.INNER_JOIN);
		criteria.createAlias("empData.bioData", "bioData", JoinType.INNER_JOIN);
		criteria.createAlias("empData.golonganJabatan", "golonganJabatan", JoinType.INNER_JOIN);
		criteria.createAlias("empData.jabatanByJabatanId", "jabatan", JoinType.INNER_JOIN);
		criteria.createAlias("jabatan.department", "department", JoinType.INNER_JOIN);
		
		if(overtimeImplSearchingModel.getStartDatePeriod() != null){
			criteria.add(Restrictions.ge("implementationDate", overtimeImplSearchingModel.getStartDatePeriod()));
		}
		
		if(overtimeImplSearchingModel.getEndDatePeriod() != null){
			criteria.add(Restrictions.le("implementationDate", overtimeImplSearchingModel.getEndDatePeriod()));
		}
		
		if(!overtimeImplSearchingModel.getListSelectedWtOvertime().isEmpty()){
			List<Long> listIdSelectedWtOvertime = overtimeImplSearchingModel.getListSelectedWtOvertime()
					.stream().map(wtOvertime -> new Long(wtOvertime.getId()))
					.collect(Collectors.toList());
					
			criteria.add(Restrictions.in("wtOverTime.id", listIdSelectedWtOvertime));
		}
		
		if (!overtimeImplSearchingModel.getListSelectedGolJabatan().isEmpty()) {
			List<Long> listIdSelectedGolonganJabatan = overtimeImplSearchingModel.getListSelectedGolJabatan()
														.stream().map(golJab -> new Long(golJab.getId()))
														.collect(Collectors.toList());
														
			criteria.add(Restrictions.in("golonganJabatan.id", listIdSelectedGolonganJabatan));
		}
		
		if (!overtimeImplSearchingModel.getListSelectedDepartment().isEmpty()) {
			List<Long> listIdSelectedDepartemen = overtimeImplSearchingModel.getListSelectedDepartment()
														.stream().map(departemen -> new Long(departemen.getId()))
														.collect(Collectors.toList());
														
			criteria.add(Restrictions.in("department.id", listIdSelectedDepartemen));
		}
		

       criteria.add(Restrictions.isNotNull("id"));
   }
    
}
