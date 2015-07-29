/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.PayTempOvertime;
import com.inkubator.hrm.web.search.PayTempOvertimeSearchParameter;

import java.util.List;

import org.hibernate.criterion.Order;

/**
 *
 * @author deni
 */
public interface PayTempOvertimeDao extends IDAO<PayTempOvertime> {
	
    public List<PayTempOvertime> getByParam(PayTempOvertimeSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalByParam(PayTempOvertimeSearchParameter searchParameter);
    
    public PayTempOvertime getAllDataByNik(String nik);
    
    public PayTempOvertime getEntityByPkWithDetail(Long id);

	public void deleteAllData();

	public PayTempOvertime getEntityByEmpDataId(Long empDataId);
}
