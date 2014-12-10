/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.PayTempKalkulasi;
import com.inkubator.hrm.web.model.PayTempKalkulasiModel;
import com.inkubator.hrm.web.search.PayTempKalkulasiSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author denifahri
 */
public interface PayTempKalkulasiDao extends IDAO<PayTempKalkulasi>{
    public void saveBatch(List<PayTempKalkulasi>data);
    
    public List<PayTempKalkulasiModel> getByParam(String searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalPayTempKalkulasiByParam(String searchParameter);
    
    public PayTempKalkulasi getEntityByPkWithDetail(Long id);
}
