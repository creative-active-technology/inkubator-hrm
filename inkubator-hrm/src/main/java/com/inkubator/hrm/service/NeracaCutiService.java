/*
<<<<<<< HEAD
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
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
public interface NeracaCutiService extends IService<NeracaCuti> {

    public List<NeracaCuti> getByParamWithDetail(NeracaCutiSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalNeracaCutiByParam(NeracaCutiSearchParameter searchParameter) throws Exception;

    public NeracaCuti getEntityByParamWithDetail(Long id) throws Exception;
}
