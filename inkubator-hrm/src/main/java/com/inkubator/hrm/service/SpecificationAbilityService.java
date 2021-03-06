package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.SpecificationAbility;
import com.inkubator.hrm.web.model.SpecificationAbilityModelView;
import com.inkubator.hrm.web.search.SpecificationAbilitySearchParameter;

/**
*
* @author rizkykojek
*/
public interface SpecificationAbilityService extends IService<SpecificationAbility> {

	public List<SpecificationAbility> getByParam(SpecificationAbilitySearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(SpecificationAbilitySearchParameter parameter) throws Exception;
        
        public SpecificationAbility getByName(String name) throws Exception;

        public List<SpecificationAbilityModelView> getDataBySpecAbilityNotExistInJabatanSpec(SpecificationAbilitySearchParameter parameter, int firstResult, int maxResult, Order order) throws Exception;
}
