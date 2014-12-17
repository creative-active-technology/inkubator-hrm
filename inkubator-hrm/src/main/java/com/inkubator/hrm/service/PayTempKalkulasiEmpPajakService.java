/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.PayTempKalkulasiEmpPajak;
import com.inkubator.hrm.web.model.PayTempKalkulasiEmpPajakModel;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author denifahri
 */
public interface PayTempKalkulasiEmpPajakService extends IService<PayTempKalkulasiEmpPajak> {

    public void deleteAllData() throws Exception;
    
    public List<PayTempKalkulasiEmpPajakModel> getByParam() throws Exception;

    public Long getTotalPayTempKalkulasiEmpPajakByParam() throws Exception;
    
    public List<PayTempKalkulasiEmpPajak> getByParamForDetail(String searchParameter, int firstResult, int maxResults, Order order, Long taxComponentId) throws Exception;

    public Long getTotalPayTempKalkulasiEmpPajakByParamForDetail(String searchParameter, Long taxComponentId) throws Exception;

}
