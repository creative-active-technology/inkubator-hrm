/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.WtFingerException;
import com.inkubator.hrm.web.search.WtFingerExceptionSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface WtFingerExceptionDao extends IDAO<WtFingerException> {

    public List<WtFingerException> getByParamWithDetail(WtFingerExceptionSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalWtFingerExceptionByParam(WtFingerExceptionSearchParameter searchParameter);

    public WtFingerException getEntityByParamWithDetail(Long id);
    
    public List<WtFingerException> getAllDataByEmpIdWithDetail();

    public void saveBatch(List<WtFingerException> data);
}
