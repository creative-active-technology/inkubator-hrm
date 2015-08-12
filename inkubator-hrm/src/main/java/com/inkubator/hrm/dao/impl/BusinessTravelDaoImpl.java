package com.inkubator.hrm.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.BusinessTravelDao;
import com.inkubator.hrm.entity.BusinessTravel;
import com.inkubator.hrm.web.search.BusinessTravelSearchParameter;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "businessTravelDao")
@Lazy
public class BusinessTravelDaoImpl extends IDAOImpl<BusinessTravel> implements BusinessTravelDao {

	@Override
    public Class<BusinessTravel> getEntityClass() {
        return BusinessTravel.class;

    }

    @Override
    public List<BusinessTravel> getByParam(BusinessTravelSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria);
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalByParam(BusinessTravelSearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchByParam(BusinessTravelSearchParameter parameter, Criteria criteria) {
        criteria.createAlias("empData", "empData", JoinType.INNER_JOIN);

        if (StringUtils.isNotEmpty(parameter.getBusinessTravelNumber())) {
            criteria.add(Restrictions.like("businessTravelNo", parameter.getBusinessTravelNumber(), MatchMode.ANYWHERE));
        }
        if (StringUtils.isNotEmpty(parameter.getDestination())) {
            criteria.add(Restrictions.like("destination", parameter.getDestination(), MatchMode.ANYWHERE));
        }
        if (StringUtils.isNotEmpty(parameter.getEmployee())) {
            criteria.createAlias("empData.bioData", "bioData", JoinType.INNER_JOIN);

            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("empData.nik", parameter.getEmployee(), MatchMode.ANYWHERE));
            disjunction.add(Restrictions.like("bioData.firstName", parameter.getEmployee(), MatchMode.ANYWHERE));
            disjunction.add(Restrictions.like("bioData.lastName", parameter.getEmployee(), MatchMode.ANYWHERE));
            criteria.add(disjunction);
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public BusinessTravel getEntityByPkWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("empData.golonganJabatan", FetchMode.JOIN);
        criteria.setFetchMode("empData.golonganJabatan.pangkat", FetchMode.JOIN);
        criteria.setFetchMode("travelZone", FetchMode.JOIN);
        criteria.setFetchMode("travelType", FetchMode.JOIN);
        return (BusinessTravel) criteria.uniqueResult();
    }

    @Override
    public BusinessTravel getEntityByBusinessTravelNoWithDetail(String businessTravelNo) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("businessTravelNo", businessTravelNo));
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("travelZone", FetchMode.JOIN);
        criteria.setFetchMode("travelType", FetchMode.JOIN);
        return (BusinessTravel) criteria.uniqueResult();
    }

    @Override
    public Long getTotalByBusinessTravelNo(String businessTravelNo) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("businessTravelNo", businessTravelNo));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByBusinessTravelNoAndNotId(String businessTravelNo, Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("businessTravelNo", businessTravelNo));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public BusinessTravel getEntityByApprovalActivityNumberWithDetail(String approvalActivityNumber) {
    	Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("approvalActivityNumber", approvalActivityNumber));
		criteria.setFetchMode("empData", FetchMode.JOIN);
		criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
		criteria.setFetchMode("travelZone", FetchMode.JOIN);
		criteria.setFetchMode("travelType", FetchMode.JOIN);
		return (BusinessTravel) criteria.uniqueResult();
    }

    @Override
    public List<BusinessTravel> getAllDataByEmpDataId(Long empDataId) {
    	 Criteria criteria = getCurrentSession().createCriteria(getEntityClass());       
         criteria.setFetchMode("empData", FetchMode.JOIN);
          criteria.add(Restrictions.eq("empData.id", empDataId));
         criteria.setFetchMode("travelZone", FetchMode.JOIN);
 	criteria.setFetchMode("travelType", FetchMode.JOIN);
         return criteria.list();
    }


		@Override
		public List<BusinessTravel> getListByStartDateBetweenDateAndEmpIdAndNotOff(
				Long empDataId, Date dateFrom, Date dateUntill) {
			Criteria criteria = getCurrentSession().createCriteria(getEntityClass());		
	        criteria.setFetchMode("empData", FetchMode.JOIN);       
	        criteria.setFetchMode("travelType", FetchMode.JOIN);
	        criteria.createAlias("travelType.attendanceStatus", "attendanceStatus", JoinType.INNER_JOIN);	   
	        
	        criteria.add(Restrictions.eq("empData.id", empDataId));  
	        criteria.add(Restrictions.ge("startDate", dateFrom));
	        criteria.add(Restrictions.le("startDate", dateUntill));
	        criteria.add(Restrictions.ne("attendanceStatus.code", "OFF"));
	        
	        return criteria.list();
		}

    @Override
    public BusinessTravel getByEmpIdAndDate(long empId, Date doDate) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.add(Restrictions.eq("empData.id", empId));
        criteria.add(Restrictions.le("startDate", doDate));
        criteria.add(Restrictions.ge("endDate", doDate));
        return (BusinessTravel) criteria.uniqueResult();
    }

	@Override
	public Long getTotalActualBusinessTravel(Date date) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.createAlias("empData", "empData", JoinType.INNER_JOIN);
        criteria.add(Restrictions.le("startDate", date));
        criteria.add(Restrictions.ge("endDate", date));
        criteria.add(Restrictions.not(Restrictions.eq("empData.status", HRMConstant.EMP_TERMINATION)));
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
        
        @Override
	public Long getCurrentMaxId() {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());        
        return (Long) criteria.setProjection(Projections.max("id")).uniqueResult();
	}
}
