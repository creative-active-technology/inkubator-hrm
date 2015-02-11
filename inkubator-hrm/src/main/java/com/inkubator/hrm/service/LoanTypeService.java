package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.LoanType;
import com.inkubator.hrm.web.search.LoanTypeSearchParameter;

/**
*
* @author Ahmad Mudzakkir Amal
*/
public interface LoanTypeService extends IService<LoanType> {

	public List<LoanType> getByParam(LoanTypeSearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalLoanTypeByParam(LoanTypeSearchParameter parameter);
        
        public LoanType getEntityWithRelationByPk(Long id);

}
