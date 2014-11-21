package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.Disease;
import com.inkubator.hrm.web.search.DiseaseSearchParameter;

/**
*
* @author Taufik Hidayat
*/
public interface DiseaseService extends IService<Disease> {

	public List<Disease> getByParam(DiseaseSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(DiseaseSearchParameter parameter) throws Exception;

}
