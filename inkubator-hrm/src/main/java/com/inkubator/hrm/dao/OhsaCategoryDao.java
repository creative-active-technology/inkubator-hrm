/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.OhsaCategory;
import com.inkubator.hrm.web.search.OhsaCategorySearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author EKA
 */
public interface OhsaCategoryDao extends IDAO<OhsaCategory>{
    public List<OhsaCategory> getByParam(OhsaCategorySearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalOhsaCategoryByParam(OhsaCategorySearchParameter searchParameter);
}
