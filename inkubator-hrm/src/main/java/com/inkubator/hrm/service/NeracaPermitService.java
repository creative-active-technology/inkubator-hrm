/*
<<<<<<< HEAD
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.NeracaPermit;
import com.inkubator.hrm.web.search.NeracaPermitSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Taufik
 */

public interface NeracaPermitService extends IService<NeracaPermit> {

    public List<NeracaPermit> getByParamWithDetail(NeracaPermitSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalNeracaPermitByParam(NeracaPermitSearchParameter searchParameter) throws Exception;

    public NeracaPermit getEntityByParamWithDetail(Long id) throws Exception;
}
