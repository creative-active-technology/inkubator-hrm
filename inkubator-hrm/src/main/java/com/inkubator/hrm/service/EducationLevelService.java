package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.EducationLevel;

/**
*
* @author rizkykojek
*/
public interface EducationLevelService extends IService<EducationLevel> {

	public List<EducationLevel> getByParam(String parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(String parameter) throws Exception;

}
