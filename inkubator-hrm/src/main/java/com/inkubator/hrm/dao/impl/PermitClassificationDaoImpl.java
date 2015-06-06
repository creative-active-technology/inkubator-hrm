package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.PermitClassificationDao;
import com.inkubator.hrm.entity.PermitClassification;
import com.inkubator.hrm.web.search.PermitClassificationSearchParameter;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Taufik Hidayat
 */
@Repository(value = "permitClassificationDao")
@Lazy
public class PermitClassificationDaoImpl extends IDAOImpl<PermitClassification> implements PermitClassificationDao {

    @Override
    public Class<PermitClassification> getEntityClass() {
        return PermitClassification.class;
    }

    @Override
    public List<PermitClassification> getByParam(PermitClassificationSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchPermitClassificationByParam(parameter, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalPermitClassificationByParam(PermitClassificationSearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchPermitClassificationByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchPermitClassificationByParam(PermitClassificationSearchParameter parameter, Criteria criteria) {
        if (parameter.getCode() != null) {
            criteria.add(Restrictions.like("code", parameter.getCode(), MatchMode.ANYWHERE));
        }

        if (parameter.getName() != null) {
            criteria.add(Restrictions.like("name", parameter.getName(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }


    @Override
    public PermitClassification getEntityByPKWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("attendanceStatus", FetchMode.JOIN);
        return (PermitClassification) criteria.uniqueResult();
    }

    public PermitClassification getEntityByPkFetchApprovalDefinition(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("approvalDefinitionPermits", FetchMode.JOIN);
        criteria.setFetchMode("approvalDefinitionPermits.approvalDefinition", FetchMode.JOIN);
        criteria.add(Restrictions.eq("id", id));
        return (PermitClassification) criteria.uniqueResult();
    }

	@Override
	public List<PermitClassification> getAllDataByIsActiveAndOnePerEmployee(Boolean isActive, Boolean onePerEmployee) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("isActive", isActive));
		criteria.add(Restrictions.eq("onePerEmployee", onePerEmployee));
		return criteria.list();
	}

}
