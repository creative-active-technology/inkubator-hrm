/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.LoanNewType;
import com.inkubator.hrm.entity.LoanType;
import com.inkubator.hrm.web.search.LoanNewTypeSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface LoanNewTypeService extends IService<LoanNewType> {

	public List<LoanNewType> getByParam(LoanNewTypeSearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalLoanTypeByParam(LoanNewTypeSearchParameter parameter);
        
        public LoanNewType getEntityWithRelationByPk(Long id);
}
