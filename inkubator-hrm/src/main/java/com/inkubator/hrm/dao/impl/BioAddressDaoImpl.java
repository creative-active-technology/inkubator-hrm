package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.BioAddressDao;
import com.inkubator.hrm.entity.BioAddress;

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
	public List<BioAddress> getAllDataByBioDataId(Long bioDataId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("bioData.id", bioDataId));
		criteria.setFetchMode("city", FetchMode.JOIN);
		criteria.setFetchMode("city.province", FetchMode.JOIN);
		return criteria.list();
	}

	@Override
	public BioAddress getEntityByPKWithDetail(long id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("id", id));
		criteria.setFetchMode("city", FetchMode.JOIN);
		criteria.setFetchMode("city.province", FetchMode.JOIN);
		criteria.setFetchMode("city.province.country", FetchMode.JOIN);
		return (BioAddress) criteria.uniqueResult();
	}
		
}
