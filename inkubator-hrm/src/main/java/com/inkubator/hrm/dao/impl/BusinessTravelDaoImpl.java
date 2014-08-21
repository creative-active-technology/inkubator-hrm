package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.BusinessTravelDao;
import com.inkubator.hrm.entity.BusinessTravel;
import com.inkubator.hrm.entity.Leave;
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
        if (StringUtils.isNotEmpty(parameter.getBusinessTravelNumber())) {
            criteria.add(Restrictions.like("businessTravelNumber", parameter.getBusinessTravelNumber(), MatchMode.ANYWHERE));
        }
        if (StringUtils.isNotEmpty(parameter.getDestination())) {
            criteria.add(Restrictions.like("destination", parameter.getDestination(), MatchMode.ANYWHERE));
        }
        if (StringUtils.isNotEmpty(parameter.getEmployeeName())) {
            criteria.add(Restrictions.like("employeeName", parameter.getEmployeeName(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

}
