/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.PayTempKalkulasi;
import com.inkubator.hrm.web.model.PayTempKalkulasiModel;
import com.inkubator.hrm.web.search.PayTempKalkulasiSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author denifahri
 */
public interface PayTempKalkulasiService extends IService<PayTempKalkulasi> {

    public void calculatePayRoll() throws Exception;
    
    public List<PayTempKalkulasiModel> getByParam(String searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalPayTempKalkulasiByParam(String searchParameter);
    
    public PayTempKalkulasi getEntityByPkWithDetail(Long id);
}
