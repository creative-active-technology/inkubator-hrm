package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.EducationNonFormal;

/**
*
* @author rizkykojek
*/
public interface EducationNonFormalDao extends IDAO<EducationNonFormal> {

	public List<EducationNonFormal> getByParam(String parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalByParam(String parameter);
	
	public EducationNonFormal getEntityByPkWithDetail(Long id);

}
