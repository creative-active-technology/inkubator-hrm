package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.EducationNonFormal;
import com.inkubator.hrm.web.search.EducationNonFormalSearchParameter;

/**
*
* @author rizkykojek
*/
public interface EducationNonFormalDao extends IDAO<EducationNonFormal> {

	public List<EducationNonFormal> getByParam(EducationNonFormalSearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalByParam(EducationNonFormalSearchParameter parameter);
	
	public EducationNonFormal getEntityByPkWithDetail(Long id);

}
