package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RmbsSchemaListOfTypeDao;
import com.inkubator.hrm.entity.RmbsSchemaListOfType;
import com.inkubator.hrm.entity.RmbsSchemaListOfTypeId;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "rmbsSchemaListOfTypeDao")
@Lazy
public class RmbsSchemaListOfTypeDaoImpl extends IDAOImpl<RmbsSchemaListOfType> implements RmbsSchemaListOfTypeDao {

	@Override
	public Class<RmbsSchemaListOfType> getEntityClass() {
		return RmbsSchemaListOfType.class;
		
	}
	
	@Override
	public List<RmbsSchemaListOfType> getAllDataByRmbsSchemaId(Long rmbsSchemaId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("rmbsSchema.id", rmbsSchemaId));
		criteria.setFetchMode("rmbsType", FetchMode.JOIN);
		return criteria.list();
	}

	@Override
	public RmbsSchemaListOfType getEntityByPk(RmbsSchemaListOfTypeId id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("id.rmbsSchemaId", id.getRmbsSchemaId()));
		criteria.add(Restrictions.eq("id.rmbsTypeId", id.getRmbsTypeId()));
		criteria.setFetchMode("rmbsType", FetchMode.JOIN);
		
		return (RmbsSchemaListOfType) criteria.uniqueResult();
		
	}
	
}
