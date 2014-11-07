package com.inkubator.hrm.dao;

import java.util.List;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.FinancialNonBanking;
import com.inkubator.hrm.web.search.FinancialNonBankingSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author rizkykojek
 */
public interface FinancialNonBankingDao extends IDAO<FinancialNonBanking> {
    public List<FinancialNonBanking> getByParamWithDetail(FinancialNonBankingSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalFinancialNonBankingByParam(FinancialNonBankingSearchParameter searchParameter);
    
    public FinancialNonBanking getEntityByPkWithDetail(Long id);

    public List<FinancialNonBanking> getAllDataByFinancialService(String financialService);
	
}
