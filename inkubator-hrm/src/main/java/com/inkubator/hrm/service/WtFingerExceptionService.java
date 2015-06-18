/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.WtFingerException;
import com.inkubator.hrm.web.search.WtFingerExceptionSearchParameter;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface WtFingerExceptionService extends IService<WtFingerException>{
	
    public List<WtFingerException> getByParamWithDetail(WtFingerExceptionSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalWtFingerExceptionByParam(WtFingerExceptionSearchParameter searchParameter) throws Exception;

    public WtFingerException getEntityByParamWithDetail(Long id) throws Exception;

    public void saveMassFingerException(List<EmpData> data, Date startDate, Date endDate, Boolean extendException) throws Exception;
}
