package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.BenefitGroup;
import com.inkubator.hrm.web.search.BenefitGroupSearchParameter;

/**
*
* @author Taufik hidayat
*/
public interface BenefitGroupDao extends IDAO<BenefitGroup> {

	public List<BenefitGroup> getByParam(BenefitGroupSearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalBenefitGroupByParam(BenefitGroupSearchParameter parameter);

}
