package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.SpecificationAbility;
import com.inkubator.hrm.web.search.SpecificationAbilitySearchParameter;

/**
*
* @author rizkykojek
*/
public interface SpecificationAbilityDao extends IDAO<SpecificationAbility> {

	public List<SpecificationAbility> getByParam(SpecificationAbilitySearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalByParam(SpecificationAbilitySearchParameter parameter);
	
	public Long getTotalByName(String name);
	
	public Long getTotalByNameAndNotId(String name, Long id);

}
