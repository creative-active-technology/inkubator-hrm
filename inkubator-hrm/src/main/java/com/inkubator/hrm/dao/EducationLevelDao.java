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

	public List<EducationLevel> getByParam(String parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalByParam(String parameter);
	
	public Long getTotalByNameAndNotId(String name, Long id);
	
	public Long getTotalByLevelAndNotId(Integer level, Long id);
	
	public Long getTotalByName(String name);
	
	public Long getTotalByLevel(Integer level);
        
        public List<EducationLevel> getAllDataOrderByLevel();

}
