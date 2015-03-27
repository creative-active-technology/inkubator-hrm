package com.inkubator.hrm.dao.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.Query;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RmbsApplicationDao;
import com.inkubator.hrm.entity.RmbsApplication;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "rmbsApplicationDao")
@Lazy
public class RmbsApplicationDaoImpl extends IDAOImpl<RmbsApplication> implements RmbsApplicationDao {

	@Override
	public Class<RmbsApplication> getEntityClass() {
		return RmbsApplication.class;
		
	}

	@Override
	public BigDecimal getTotalNominalByEmpDataIdAndRmbsTypeIdAndDateBetween(Long empDataId, Long rmbsTypeId, Date startDate, Date endDate) {
		StringBuffer selectQuery = new StringBuffer(
    			"SELECT SUM(nominal) " +
    			"FROM RmbsApplication " +
    			"WHERE empData.id = :empDataId " +
    			"AND rmbsType.id = :rmbsTypeId " +
    			"AND applicationDate >= :startDate " +
    			"AND applicationDate <= :endDate");
		
    	Query hbm = getCurrentSession().createQuery(selectQuery.toString())
    			.setParameter("empDataId", empDataId)
    			.setParameter("rmbsTypeId", rmbsTypeId)
    			.setParameter("startDate", startDate)
    			.setParameter("endDate", endDate);
    	
    	return new BigDecimal(hbm.uniqueResult().toString());
		
	}

	
}
