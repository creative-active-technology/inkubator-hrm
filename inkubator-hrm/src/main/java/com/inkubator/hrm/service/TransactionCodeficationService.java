package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.TransactionCodefication;
import com.inkubator.hrm.web.search.TransactionCodeficationSearchParameter;

/**
*
* @author Taufik Hidayat
*/
public interface TransactionCodeficationService extends IService<TransactionCodefication> {

	public List<TransactionCodefication> getByParam(TransactionCodeficationSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(TransactionCodeficationSearchParameter parameter) throws Exception;
        
        public TransactionCodefication getEntityByModulCode(String modulCode);

}
