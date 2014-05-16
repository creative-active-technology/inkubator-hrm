package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.EducationLevel;

/**
*
* @author rizkykojek
*/
public interface EducationLevelDao extends IDAO<EducationLevel> {

	List<EducationLevel> getByParam(String parameter, int firstResult, int maxResults, Order orderable);

	Long getTotalByParam(String parameter);

}
