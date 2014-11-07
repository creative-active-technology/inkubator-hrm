package com.inkubator.hrm.dao.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.FinancialNonBankingDao;
import com.inkubator.hrm.entity.FinancialNonBanking;
import com.inkubator.hrm.web.search.FinancialNonBankingSearchParameter;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "financialNonBankingDao")
@Lazy
public class FinancialNonBankingDaoImpl extends IDAOImpl<FinancialNonBanking> implements FinancialNonBankingDao {

	@Override
	public Class<FinancialNonBanking> getEntityClass() {
		return FinancialNonBanking.class;
		
	}

    @Override
    public List<FinancialNonBanking> getByParamWithDetail(FinancialNonBankingSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
    Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearch(searchParameter, criteria);
        criteria.setFetchMode("city", FetchMode.JOIN);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();    
    }

    @Override
    public Long getTotalResourceTypeByParam(FinancialNonBankingSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearch(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public FinancialNonBanking getEntityByPkWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("city", FetchMode.JOIN);
        criteria.setFetchMode("city.province", FetchMode.JOIN);
        criteria.setFetchMode("city.province.country", FetchMode.JOIN);
        return (FinancialNonBanking) criteria.uniqueResult();
    }

    private void doSearch(FinancialNonBankingSearchParameter searchParameter, Criteria criteria) {
        if (searchParameter.getName()!=null) {
        	criteria.add(Restrictions.like("name", searchParameter.getName(), MatchMode.ANYWHERE));
        } 
        if (searchParameter.getCode()!=null) {
        	criteria.add(Restrictions.like("code", searchParameter.getCode(), MatchMode.ANYWHERE));
        } 
        criteria.add(Restrictions.isNotNull("id"));
    }
}
