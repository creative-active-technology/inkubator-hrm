package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.TerminationType;
import com.inkubator.hrm.web.search.TerminationTypeSearchParameter;

/**
*
* @author Taufik hidayat
*/
public interface TerminationTypeDao extends IDAO<TerminationType> {

	public List<TerminationType> getByParam(TerminationTypeSearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalTerminationTypeByParam(TerminationTypeSearchParameter parameter);

}
