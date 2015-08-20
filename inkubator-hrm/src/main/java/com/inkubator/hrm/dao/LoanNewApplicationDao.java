package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.LoanNewApplication;
import com.inkubator.hrm.web.model.LoanHistoryViewModel;
import com.inkubator.hrm.web.model.LoanNewApplicationBoxViewModel;
import com.inkubator.hrm.web.search.LoanNewApplicationBoxSearchParameter;
import com.inkubator.hrm.web.search.LoanNewSearchParameter;


/**
 * 
 * @author Ahmad Mudzakkir Amal
 */
public interface LoanNewApplicationDao extends IDAO<LoanNewApplication> {

//	public List<LoanNewApplication> getByParam(CitySearchParameter parameter, int firstResult, int maxResults, Order orderable);
//
//	public Long getTotalCityByParam(CitySearchParameter parameter);
        
	public Long getTotalByCode(String name);

	public Long getTotalByCodeAndNotId(String code, Long id);

	public LoanNewApplication getEntityByIdWithDetail(Long id);	
	
        public Long getCurrentMaxId();
        
        public List<LoanNewApplication> getListUnpaidLoanByEmpDataIdAndLoanNewTypeId(Long empDataId, Long loanNewTypeId);
        
        public List<LoanNewApplication> getListLoanDisbursedOrPaidByEmpDataIdAndLoanNewSchemaId(Long empDataId, Long loanNewSchemaId);
        
        public List<LoanNewApplication> getByParamByStatusUndisbursed(LoanNewSearchParameter parameter, int firstResult, int maxResults, Order orderable);
    
        public Long getTotalByParamByStatusUndisbursed(LoanNewSearchParameter parameter);
        
        public List<LoanNewApplicationBoxViewModel> getUndisbursedActivityByParam(LoanNewApplicationBoxSearchParameter parameter, int firstResult, int maxResults, Order orderable);
        
        public Long getTotalUndisbursedActivityByParam(LoanNewApplicationBoxSearchParameter parameter);
        
        public List<LoanHistoryViewModel> getListLoanHistoryByEmpDataId(Long empDataId);
        
        public List<LoanNewApplication> getListLoanDisbursedOrPaidByEmpDataIdAndLoanNewTypeId(Long empDataId, Long loanNewTypeId);
}
