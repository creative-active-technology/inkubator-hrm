package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.PublicHolidayDao;
import com.inkubator.hrm.entity.PublicHoliday;
import com.inkubator.hrm.web.search.PublicHolidaySearchParameter;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "publicHolidayDao")
@Lazy
public class PublicHolidayDaoImpl extends IDAOImpl<PublicHoliday> implements PublicHolidayDao {

    @Override
    public Class<PublicHoliday> getEntityClass() {
        return PublicHoliday.class;
    }

    @Override
    public List<PublicHoliday> getByParam(String parameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchPublicHolidayByParam(parameter, criteria);
        criteria.setFetchMode("leaveScheme", FetchMode.JOIN);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalPublicHolidayByParam(String parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchPublicHolidayByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchPublicHolidayByParam(String parameter, Criteria criteria) {
        if (StringUtils.isNotEmpty(parameter)) {
            criteria.createAlias("leaveScheme", "l", JoinType.INNER_JOIN);
            criteria.add(Restrictions.like("l.name", parameter, MatchMode.START));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public PublicHoliday getEntityByPKWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("leaveScheme", FetchMode.JOIN);
        return (PublicHoliday) criteria.uniqueResult();
    }

    @Override
    public List<PublicHoliday> getAllWithDetail() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("leaveScheme", FetchMode.JOIN);
        return criteria.list();
    }

    @Override
    public List<PublicHoliday> getReportByParam(PublicHolidaySearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchReportByParam(parameter, criteria);
        criteria.setFetchMode("leaveScheme", FetchMode.JOIN);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getReportTotalByParam(PublicHolidaySearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchReportByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchReportByParam(PublicHolidaySearchParameter parameter, Criteria criteria) {
        //get approval definition ids

        if (parameter.getStartDate() != null) {
            criteria.add(Restrictions.eq("startDate", parameter.getStartDate()));
        }

        if (parameter.getEndDate() != null) {
            criteria.add(Restrictions.eq("endDate", parameter.getEndDate()));
        }

        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public List<PublicHoliday> getReportHistoryByParam(PublicHolidaySearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchReportByParam(parameter, criteria);
        criteria.setFetchMode("leaveScheme", FetchMode.JOIN);
        return criteria.list();
    }

    @Override
    public List<PublicHoliday> getByLeavShcemaId(long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("leaveScheme", "lv", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("lv.id", id));
        return criteria.list();
    }
}
