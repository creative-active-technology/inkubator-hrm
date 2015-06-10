/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.ApprovalDefinitionPermitDao;
import com.inkubator.hrm.entity.ApprovalDefinitionPermit;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni
 */
@Repository
@Lazy
public class ApprovalDefinitionPermitDaoImpl extends IDAOImpl<ApprovalDefinitionPermit> implements ApprovalDefinitionPermitDao{

    @Override
    public Class<ApprovalDefinitionPermit> getEntityClass() {
        return ApprovalDefinitionPermit.class;
    }

    @Override
    public List<ApprovalDefinitionPermit> getByPermitId(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("permitClassification", FetchMode.JOIN);
        criteria.setFetchMode("approvalDefinition", FetchMode.JOIN);
        criteria.add(Restrictions.eq("permitClassification.id", id));
        return criteria.list();
    }
    
}
