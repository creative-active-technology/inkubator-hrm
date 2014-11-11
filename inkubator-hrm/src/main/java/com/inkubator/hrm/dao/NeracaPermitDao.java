/*
<<<<<<< HEAD
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.NeracaPermit;
import com.inkubator.hrm.web.search.NeracaPermitSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Taufik
 */
public interface NeracaPermitDao extends IDAO<NeracaPermit>{
    public List<NeracaPermit> getByParam(NeracaPermitSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalNeracaPermitByParam(NeracaPermitSearchParameter searchParameter);
    
    public NeracaPermit getEntityByPkWithDetail(Long id);

    public void saveBacth(List<NeracaPermit> data);
}
