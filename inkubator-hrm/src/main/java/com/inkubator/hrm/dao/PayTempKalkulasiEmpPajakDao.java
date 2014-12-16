/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.PayTempKalkulasiEmpPajak;
import com.inkubator.hrm.web.model.PayTempKalkulasiEmpPajakModel;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author denifahri
 */
public interface PayTempKalkulasiEmpPajakDao extends IDAO<PayTempKalkulasiEmpPajak> {

    public void deleteAllData();

    public List<PayTempKalkulasiEmpPajakModel> getByParam();

    public Long getTotalPayTempKalkulasiEmpPajakByParam();

    public List<PayTempKalkulasiEmpPajak> getByParamForDetail(String searchParameter, int firstResult, int maxResults, Order order, Long taxComponentId);

    public Long getTotalPayTempKalkulasiEmpPajakByParamForDetail(String searchParameter, Long taxComponentId);

}
