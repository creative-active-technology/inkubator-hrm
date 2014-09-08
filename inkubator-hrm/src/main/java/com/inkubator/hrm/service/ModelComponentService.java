package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.ModelComponent;
import com.inkubator.hrm.web.search.ModelComponentSearchParameter;

/**
*
* @author Taufik Hidayat
*/
public interface ModelComponentService extends IService<ModelComponent> {

	public List<ModelComponent> getByParam(ModelComponentSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(ModelComponentSearchParameter parameter) throws Exception;
        
        public ModelComponent getEntityByPKWithDetail(Long id) throws Exception;

}
