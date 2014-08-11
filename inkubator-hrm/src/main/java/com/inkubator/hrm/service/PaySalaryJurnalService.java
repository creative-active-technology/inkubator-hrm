package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.PaySalaryJurnal;
import com.inkubator.hrm.web.search.PaySalaryJurnalSearchParameter;

/**
*
* @author Taufik Hidayat
*/
public interface PaySalaryJurnalService extends IService<PaySalaryJurnal> {

	public List<PaySalaryJurnal> getByParam(PaySalaryJurnalSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(PaySalaryJurnalSearchParameter parameter) throws Exception;
        
        public PaySalaryJurnal getEntityByPKWithDetail(Long id) throws Exception;

}
