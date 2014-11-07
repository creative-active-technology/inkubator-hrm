package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.FinancialNonBanking;
import com.inkubator.hrm.web.search.FinancialNonBankingSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author rizkykojek
 */
public interface FinancialNonBankingService extends IService<FinancialNonBanking> {
    public List<FinancialNonBanking> getByParamWithDetail(FinancialNonBankingSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalFinancialNonBankingByParam(FinancialNonBankingSearchParameter searchParameter) throws Exception;
    
    public FinancialNonBanking getEntityByPkWithDetail(Long id) throws Exception;
}
