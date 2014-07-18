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
import com.inkubator.hrm.dao.BioAddressDao;
import com.inkubator.hrm.entity.BioAddress;
import com.inkubator.hrm.web.search.BioAddressSearchParameter;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "bioAddressDao")
@Lazy
public class BioAddressDaoImpl extends IDAOImpl<BioAddress> implements BioAddressDao {

	@Override
	public Class<BioAddress> getEntityClass() {
		return BioAddress.class;		
	}

	@Override
	public List<BioAddress> getByParam(BioAddressSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria = doSearchByParam(parameter, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
		
	}

	@Override
	public Long getTotalByParam(BioAddressSearchParameter parameter) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria = doSearchByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();		
	}
	
	private Criteria doSearchByParam(BioAddressSearchParameter parameter, Criteria criteria) {        
        if (StringUtils.isNotEmpty(parameter.getAddressDetail())) {
            criteria.add(Restrictions.like("addressDetail", parameter.getAddressDetail(), MatchMode.ANYWHERE));
        }
        if (StringUtils.isNotEmpty(parameter.getCityName())) {
        	criteria.createAlias("city", "city");
            criteria.add(Restrictions.like("city.cityName", parameter.getCityName(), MatchMode.ANYWHERE));
        }
        if (StringUtils.isNotEmpty(parameter.getProvinceName())) {
        	criteria.createAlias("province", "province");
            criteria.add(Restrictions.like("province.provinceName", parameter.getProvinceName(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
        
        return criteria;
    }
		
}
