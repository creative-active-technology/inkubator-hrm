/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.LoanCanceled;
import com.inkubator.hrm.web.search.LoanCanceledSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author deni
 */
public interface LoanCanceledService extends IService<LoanCanceled> {

    public List<LoanCanceled> getByParam(LoanCanceledSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalByParam(LoanCanceledSearchParameter searchParameter) throws Exception;
    
    public LoanCanceled getEntityByPkWithDetail(Long id) throws Exception;
}
