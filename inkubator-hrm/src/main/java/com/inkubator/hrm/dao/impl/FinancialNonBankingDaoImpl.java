package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.FinancialNonBankingDao;
import com.inkubator.hrm.entity.FinancialNonBanking;
import com.inkubator.hrm.web.search.FinancialNonBankingSearchParameter;
import com.inkubator.webcore.util.FacesUtil;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import org.apache.commons.lang3.StringUtils;
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
    public Long getTotalFinancialNonBankingByParam(FinancialNonBankingSearchParameter searchParameter) {
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
        ResourceBundle resourceBundle = ResourceBundle.getBundle("messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
        
        if (searchParameter.getName()!=null) {
        	criteria.add(Restrictions.like("name", searchParameter.getName(), MatchMode.START));
        } 
        if (searchParameter.getCode()!=null) {
        	criteria.add(Restrictions.like("code", searchParameter.getCode(), MatchMode.START));
        }
        if (searchParameter.getFinancialService()!=null) {
            String financeService = StringUtils.EMPTY;
            if(searchParameter.getFinancialService().toLowerCase().equals(resourceBundle.getString("finance.insurance").toLowerCase())){
                financeService = HRMConstant.FINANCIAL_SERVICE_INSURANCE;
            }else if(searchParameter.getFinancialService().toLowerCase().equals(resourceBundle.getString("finance.financing").toLowerCase())){
                financeService = HRMConstant.FINANCIAL_SERVICE_FINANCING;
            }else if(searchParameter.getFinancialService().toLowerCase().equals(resourceBundle.getString("finance.pension").toLowerCase())){
                financeService = HRMConstant.FINANCIAL_SERVICE_PENSION;
            }
        	criteria.add(Restrictions.like("financialService", financeService, MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public List<FinancialNonBanking> getAllDataByFinancialService(String financialService) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("financialService", financialService));
        return criteria.list();		
    }

}
