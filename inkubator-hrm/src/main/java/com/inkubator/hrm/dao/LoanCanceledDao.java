/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.LoanCanceled;
import com.inkubator.hrm.web.search.LoanCanceledSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author deni
 */
public interface LoanCanceledDao extends IDAO<LoanCanceled> {

    public List<LoanCanceled> getByParam(LoanCanceledSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalByParam(LoanCanceledSearchParameter searchParameter);
    
    public LoanCanceled getEntityByPkWithDetail(Long id);
    
    public Long getCurrentMaxId();
}
