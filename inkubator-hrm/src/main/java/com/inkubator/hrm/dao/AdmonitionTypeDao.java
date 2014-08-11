package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.AdmonitionType;
import com.inkubator.hrm.web.search.AdmonitionTypeSearchParameter;

/**
*
* @author Taufik hidayat
*/
public interface AdmonitionTypeDao extends IDAO<AdmonitionType> {

	public List<AdmonitionType> getByParam(AdmonitionTypeSearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalAdmonitionTypeByParam(AdmonitionTypeSearchParameter parameter);

}
