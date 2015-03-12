/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.LoanNewSchema;
import com.inkubator.hrm.web.search.LoanNewSchemaSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface LoanNewSchemaDao extends IDAO<LoanNewSchema> {
    public List<LoanNewSchema> getAllDataByParam(LoanNewSchemaSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalDataByParam(LoanNewSchemaSearchParameter searchParameter);

}
