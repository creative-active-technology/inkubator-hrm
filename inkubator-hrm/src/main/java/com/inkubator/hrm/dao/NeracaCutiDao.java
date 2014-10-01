/*
<<<<<<< HEAD
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.NeracaCuti;
import com.inkubator.hrm.web.search.NeracaCutiSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
/**
 *
 * @author Deni Husni FR
 */
public interface NeracaCutiDao extends IDAO<NeracaCuti>{
    public List<NeracaCuti> getByParam(NeracaCutiSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalNeracaCutiByParam(NeracaCutiSearchParameter searchParameter);
    
    public NeracaCuti getEntityByPkWithDetail(Long id);

    public void saveBacth(List<NeracaCuti> data);
}
