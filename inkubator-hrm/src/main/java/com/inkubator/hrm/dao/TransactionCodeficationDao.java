package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.TransactionCodefication;
import com.inkubator.hrm.web.search.TransactionCodeficationSearchParameter;

/**
*
* @author Taufik hidayat
*/
public interface TransactionCodeficationDao extends IDAO<TransactionCodefication> {

	public List<TransactionCodefication> getByParam(TransactionCodeficationSearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalTransactionCodeficationByParam(TransactionCodeficationSearchParameter parameter);

}
