/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.PermitDistributionDao;
import com.inkubator.hrm.entity.PermitDistribution;
import com.inkubator.hrm.web.search.PermitDistributionSearchParameter;

import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author taufik
 */
@Repository(value = "permitDistributionDao")
@Lazy
public class PermitDistributionDaoImpl extends IDAOImpl<PermitDistribution> implements PermitDistributionDao {

    @Override
    public Class<PermitDistribution> getEntityClass() {
        return PermitDistribution.class;
    }

    @Override
    public List<PermitDistribution> getByParamWithDetail(PermitDistributionSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearch(searchParameter, criteria);
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("permitClassification", FetchMode.JOIN);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalPermitDistributionByParam(PermitDistributionSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearch(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearch(PermitDistributionSearchParameter searchParameter, Criteria criteria) {
        if (StringUtils.isNotEmpty(searchParameter.getEmpData())) {
            criteria.createAlias("empData", "ed", JoinType.INNER_JOIN);
            criteria.createAlias("ed.bioData", "bio", JoinType.INNER_JOIN);
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("bio.firstName", searchParameter.getEmpData(), MatchMode.ANYWHERE));
            disjunction.add(Restrictions.like("bio.lastName", searchParameter.getEmpData(), MatchMode.ANYWHERE));
            criteria.add(disjunction);
        }
        if (StringUtils.isNotEmpty(searchParameter.getPermitClassification())) {
            criteria.createAlias("permitClassification", "p", JoinType.INNER_JOIN);
            criteria.add(Restrictions.like("p.name", searchParameter.getPermitClassification(), MatchMode.ANYWHERE));
        }
        if (searchParameter.getNik()!= null) {
            criteria.createAlias("empData", "ed", JoinType.INNER_JOIN);
            criteria.add(Restrictions.like("ed.nik", searchParameter.getNik(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public PermitDistribution getEntityByParamWithDetail(Long empId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("permitClassification", FetchMode.JOIN);
        criteria.add(Restrictions.eq("id", empId));
        return (PermitDistribution) criteria.uniqueResult();
    }

    @Override
    public List<PermitDistribution> getAllDataByEmpIdWithDetail() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("permitClassification", FetchMode.JOIN);
        return criteria.list();
    }
    
    @Override
    public void saveBatch(List<PermitDistribution> data) {
        int counter = 0;
        for (PermitDistribution distribution : data) {
            getCurrentSession().save(distribution);
            counter++;
            if (counter % 20 == 0) {
                getCurrentSession().flush();
                getCurrentSession().clear();
            }
        }
    }

	@Override
	public List<PermitDistribution> getAllDataByPermitClassificationIdAndIsActiveEmployee(Long permitClassificationId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("permitClassification.id", permitClassificationId));
		criteria.createAlias("empData", "empData");
		Disjunction disjunction = Restrictions.disjunction();
        disjunction.add(Restrictions.eq("empData.status", HRMConstant.EMP_PLACEMENT));
        disjunction.add(Restrictions.eq("empData.status", HRMConstant.EMP_ROTATION));
        criteria.add(disjunction);
		return criteria.list();
	}

	@Override
	public List<PermitDistribution> getAllDataByEndDateLessThan(Date date) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.lt("endDate", date));
		return criteria.list();
	}

	@Override
	public List<PermitDistribution> getAllDataByEmpIdFetchPermit(Long empDataId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("empData.id", empDataId));
        criteria.setFetchMode("permitClassification", FetchMode.JOIN);
        return criteria.list();
	}

	@Override
	public PermitDistribution getEntityByPermitClassificationIdAndEmpDataId(Long permitClassificationId, Long empDataId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("empData.id", empDataId));
		criteria.add(Restrictions.eq("permitClassification.id", permitClassificationId));
                criteria.setFetchMode("permitClassification", FetchMode.JOIN);
		return (PermitDistribution) criteria.uniqueResult();
	}
}
