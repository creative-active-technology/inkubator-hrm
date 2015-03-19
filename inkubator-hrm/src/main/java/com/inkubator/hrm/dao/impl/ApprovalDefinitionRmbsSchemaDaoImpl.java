package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.ApprovalDefinitionRmbsSchemaDao;
import com.inkubator.hrm.entity.ApprovalDefinitionRmbsSchema;

/**
 *
 * @author rizkykojek
 */
@Repository
@Lazy
public class ApprovalDefinitionRmbsSchemaDaoImpl extends IDAOImpl<ApprovalDefinitionRmbsSchema> implements ApprovalDefinitionRmbsSchemaDao {

	@Override
	public Class<ApprovalDefinitionRmbsSchema> getEntityClass() {
		return ApprovalDefinitionRmbsSchema.class;
		
	}
	
	@Override
	public List<ApprovalDefinitionRmbsSchema> getAllDataByRmbsSchemaId(Long rmbsSchemaId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("rmbsSchema", FetchMode.JOIN);
        criteria.setFetchMode("approvalDefinition", FetchMode.JOIN);
        criteria.add(Restrictions.eq("rmbsSchema.id", rmbsSchemaId));
        return criteria.list();
		
	}

	@Override
	public ApprovalDefinitionRmbsSchema getEntityByPk(Long appDefId, Long rmbsSchemaId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("rmbsSchema.id", rmbsSchemaId));
        criteria.add(Restrictions.eq("approvalDefinition.id", appDefId));
        return (ApprovalDefinitionRmbsSchema) criteria.uniqueResult();
		
	}

}
