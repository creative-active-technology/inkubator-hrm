package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.PublicHolidayExceptionDao;
import com.inkubator.hrm.entity.PublicHolidayException;
import com.inkubator.hrm.web.search.PublicHolidayExceptionSearchParameter;
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
 * @author Taufik Hidayat
 */
@Repository(value = "publicHolidayExceptionDao")
@Lazy
public class PublicHolidayExceptionDaoImpl extends IDAOImpl<PublicHolidayException> implements PublicHolidayExceptionDao {

    @Override
    public Class<PublicHolidayException> getEntityClass() {
        return PublicHolidayException.class;
    }

    @Override
    public List<PublicHolidayException> getByParam(PublicHolidayExceptionSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchPublicHolidayExceptionByParam(parameter, criteria);
        criteria.setFetchMode("publicHoliday", FetchMode.JOIN);
        criteria.setFetchMode("publicHoliday.leaveScheme", FetchMode.JOIN);
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalPublicHolidayExceptionByParam(PublicHolidayExceptionSearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchPublicHolidayExceptionByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchPublicHolidayExceptionByParam(PublicHolidayExceptionSearchParameter parameter, Criteria criteria) {
        System.out.println("Parameternyah : " +  parameter.getEmpDataName());
        if (parameter.getEmpDataName()!= null) {
            criteria.createAlias("empData", "e", JoinType.INNER_JOIN);
            criteria.createAlias("e.bioData", "b", JoinType.INNER_JOIN);
            criteria.add(Restrictions.or(Restrictions.like("b.firstName", parameter.getEmpDataName(), MatchMode.ANYWHERE),
                    Restrictions.like("b.lastName", parameter.getEmpDataName(), MatchMode.ANYWHERE)));
            criteria.add(Restrictions.isNotNull("e.nik"));
        }
        
        if (parameter.getPublicHolidayName()!= null) {
            criteria.createAlias("publicHoliday", "p", JoinType.INNER_JOIN);
            criteria.createAlias("p.leaveScheme", "l", JoinType.INNER_JOIN);
            criteria.add(Restrictions.like("l.name", parameter.getPublicHolidayName(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }
    
    @Override
    public PublicHolidayException getEntityByPKWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("publicHoliday", FetchMode.JOIN);
        criteria.setFetchMode("publicHoliday.leaveScheme", FetchMode.JOIN);
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        return (PublicHolidayException) criteria.uniqueResult();
    }

}
