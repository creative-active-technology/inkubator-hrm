package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.BusinessTravelComponentDao;
import com.inkubator.hrm.entity.BusinessTravelComponent;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "businessTravelComponentDao")
@Lazy
public class BusinessTravelComponentDaoImpl extends IDAOImpl<BusinessTravelComponent> implements BusinessTravelComponentDao {

	@Override
	public Class<BusinessTravelComponent> getEntityClass() {
		return BusinessTravelComponent.class;
		
	}

	@Override
	public List<BusinessTravelComponent> getAllDataByBusinessTravelId(Long businessTravelId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("businessTravel.id", businessTravelId));
		criteria.setFetchMode("travelComponent", FetchMode.JOIN);
		criteria.setFetchMode("costCenter", FetchMode.JOIN);
		return criteria.list();
	}	

}
