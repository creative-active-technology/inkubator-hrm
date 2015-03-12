/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.LoanNewType;
import com.inkubator.hrm.web.search.LoanNewTypeSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface LoanNewTypeDao extends IDAO<LoanNewType>{

	public List<LoanNewType> getByParam(LoanNewTypeSearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalLoanTypeByParam(LoanNewTypeSearchParameter parameter);
        
        public Long getTotalByLoanNewTypeCode(String loanTypeCode);
        
        public Long getTotalByLoanNewTypeCodeAndNotId(String code, Long id);
        
        public Long getTotalByLoanNewTypeName(String loanTypeName);
        
        public Long getTotalByLoanNewTypeNameAndNotId(String name, Long id);
        
        public LoanNewType getEntityByPkWithDetail(Long id);
}

