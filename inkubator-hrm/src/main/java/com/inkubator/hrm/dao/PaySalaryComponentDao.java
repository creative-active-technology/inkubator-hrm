/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.PaySalaryComponent;
import com.inkubator.hrm.web.search.PaySalaryComponentSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni Husni FR
 */
public interface PaySalaryComponentDao extends IDAO<PaySalaryComponent>{
    public List<PaySalaryComponent> getByParamWithDetail(PaySalaryComponentSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalResourceTypeByParam(PaySalaryComponentSearchParameter searchParameter);
    
    public PaySalaryComponent getEntityByPkWithDetail(Long id);

	public List<PaySalaryComponent> getAllDataComponentUploadByParam(PaySalaryComponentSearchParameter searchParameter, int firstResult, int maxResults, Order order);

	public Long getTotalComponentUploadByParam(PaySalaryComponentSearchParameter searchParameter);
	
}
