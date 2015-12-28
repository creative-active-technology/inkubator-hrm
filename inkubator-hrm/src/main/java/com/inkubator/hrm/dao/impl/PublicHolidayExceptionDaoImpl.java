package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.PublicHolidayExceptionDao;
import com.inkubator.hrm.entity.PublicHolidayException;
import com.inkubator.hrm.web.search.PublicHolidayExceptionSearchParameter;
import java.util.List;
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
        
        criteria.createAlias("empData", "empData", JoinType.INNER_JOIN);
        criteria.createAlias("empData.bioData", "bioData", JoinType.INNER_JOIN);
        criteria.createAlias("publicHoliday", "publicHoliday", JoinType.INNER_JOIN);
        criteria.createAlias("publicHoliday.leave", "leave", JoinType.INNER_JOIN);
        
        if (parameter.getEmpDataName()!= null) {
            /*criteria.add(Restrictions.or(Restrictions.like("bioData.firstName", parameter.getEmpDataName(), MatchMode.ANYWHERE),
                    Restrictions.like("bioData.lastName", parameter.getEmpDataName(), MatchMode.ANYWHERE)));*/
            
            criteria.add(Restrictions.ilike("bioData.combineName", parameter.getEmpDataName().toLowerCase(), MatchMode.ANYWHERE));
            criteria.add(Restrictions.isNotNull("empData.nik"));
        }
        
        if (parameter.getPublicHolidayName()!= null) {
            criteria.add(Restrictions.like("leave.name", parameter.getPublicHolidayName(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }
    
    @Override
    public PublicHolidayException getEntityByPKWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("publicHoliday", FetchMode.JOIN);
        criteria.setFetchMode("publicHoliday.leave", FetchMode.JOIN);
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        return (PublicHolidayException) criteria.uniqueResult();
    }

}
