package com.inkubator.hrm.dao.impl;

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

}
