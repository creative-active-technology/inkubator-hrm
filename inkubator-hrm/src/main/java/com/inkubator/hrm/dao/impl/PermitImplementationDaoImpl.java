package com.inkubator.hrm.dao.impl;

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
import com.inkubator.hrm.dao.PermitImplementationDao;
import com.inkubator.hrm.entity.PermitImplementation;
import com.inkubator.hrm.web.search.PermitImplementationReportSearchParameter;
import com.inkubator.hrm.web.search.PermitImplementationSearchParameter;

/**
 *
 * @author Taufik
 */
@Repository(value = "permitImplementationDao")
@Lazy
public class PermitImplementationDaoImpl extends IDAOImpl<PermitImplementation> implements PermitImplementationDao {

    @Override
    public Class<PermitImplementation> getEntityClass() {
        return PermitImplementation.class;

    }

    @Override
    public List<PermitImplementation> getByParam(PermitImplementationSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria);
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("permitClassification", FetchMode.JOIN);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalByParam(PermitImplementationSearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchByParam(PermitImplementationSearchParameter parameter, Criteria criteria) {
        if (StringUtils.isNotEmpty(parameter.getPermit())) {
            criteria.createAlias("permitClassification", "permit", JoinType.INNER_JOIN);
            criteria.add(Restrictions.like("permit.name", parameter.getPermit(), MatchMode.ANYWHERE));
        }
        if (StringUtils.isNotEmpty(parameter.getEmployee())) {
            criteria.createAlias("empData.bioData", "bio", JoinType.INNER_JOIN);
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("bio.nik", parameter.getEmployee(), MatchMode.ANYWHERE));
            disjunction.add(Restrictions.like("bio.firstName", parameter.getEmployee(), MatchMode.ANYWHERE));
            disjunction.add(Restrictions.like("bio.lastName", parameter.getEmployee(), MatchMode.ANYWHERE));
        }
        if (StringUtils.isNotEmpty(parameter.getNumberFilling())) {
            criteria.add(Restrictions.like("numberFilling", parameter.getNumberFilling(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public PermitImplementation getEntityByPkWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
//        criteria.setFetchMode("temporaryActing", FetchMode.JOIN);
//        criteria.setFetchMode("temporaryActing.bioData", FetchMode.JOIN);
        criteria.setFetchMode("permitClassification", FetchMode.JOIN);
        return (PermitImplementation) criteria.uniqueResult();
    }

    @Override
    public List<PermitImplementation> getAllDataByEmpDataId(Long empDataId, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("empData.id", empDataId));
        criteria.addOrder(orderable);
        return criteria.list();
    }

    @Override
    public long getTotalByNumberFilling(String numberFilling) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("numberFilling", numberFilling));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public long getTotalByNumberFillingAndNotId(String numberFilling, Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("numberFilling", numberFilling));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public PermitImplementation getEntityByApprovalActivityNumberWithDetail(String activityNumber) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("approvalActivityNumber", activityNumber));
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
//        criteria.setFetchMode("temporaryActing", FetchMode.JOIN);
//        criteria.setFetchMode("temporaryActing.bioData", FetchMode.JOIN);
        criteria.setFetchMode("permitClassification", FetchMode.JOIN);
        return (PermitImplementation) criteria.uniqueResult();
    }

    @Override
    public List<PermitImplementation> getReportByParam(PermitImplementationReportSearchParameter parameter, List<String> activityNumbers, Long empDataId, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchReportByParam(parameter, activityNumbers, empDataId, criteria);
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("permitClassification", FetchMode.JOIN);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getReportTotalByParam(PermitImplementationReportSearchParameter parameter, List<String> activityNumbers, Long empDataId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchReportByParam(parameter, activityNumbers, empDataId, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchReportByParam(PermitImplementationReportSearchParameter parameter, List<String> activityNumbers, Long empDataId, Criteria criteria) {
        //get approval definition ids

        if (parameter.getStartDate() != null) {
            criteria.add(Restrictions.eq("startDate", parameter.getStartDate()));
        }

        if (parameter.getEndDate() != null) {
            criteria.add(Restrictions.eq("endDate", parameter.getEndDate()));
        }
        
        if (StringUtils.isNotEmpty(parameter.getApprovalStatus())) {
            if (!activityNumbers.isEmpty()) {

                criteria.add(Restrictions.in("approvalActivityNumber", activityNumbers));
            }else{
                criteria.add(Restrictions.eq("approvalActivityNumber", "0"));
            }
        }
        
        if(empDataId != 0L){
            criteria.add(Restrictions.eq("empData.id", empDataId));
        }

        criteria.add(Restrictions.isNotNull("id"));
    }
    
    @Override
    public List<PermitImplementation> getReportHistoryByParam(PermitImplementationReportSearchParameter parameter, List<String> activityNumbers, Long empDataId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchReportByParam(parameter, activityNumbers, empDataId, criteria);
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("permit", FetchMode.JOIN);
        return criteria.list();
    }
}
