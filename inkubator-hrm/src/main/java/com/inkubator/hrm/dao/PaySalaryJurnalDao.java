package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.PaySalaryJurnal;
import com.inkubator.hrm.web.search.PaySalaryJurnalSearchParameter;

/**
*
* @author Taufik hidayat
*/
public interface PaySalaryJurnalDao extends IDAO<PaySalaryJurnal> {

	public List<PaySalaryJurnal> getByParam(PaySalaryJurnalSearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalPaySalaryJurnalByParam(PaySalaryJurnalSearchParameter parameter);
        
        public PaySalaryJurnal getEntityByPKWithDetail(Long id);
        

}
