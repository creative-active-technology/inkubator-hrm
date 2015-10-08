package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.EducationLevel;
import com.inkubator.hrm.entity.PaySalaryGrade;
import com.inkubator.hrm.web.search.EducationLevelSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface EducationLevelDao extends IDAO<EducationLevel> {

	public List<EducationLevel> getByParam(EducationLevelSearchParameter searchParameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalByParam(EducationLevelSearchParameter searchParameter);

	public Long getTotalByNameAndNotId(String name, Long id);

	public Long getTotalByLevelAndNotId(Integer level, Long id);

	public Long getTotalByName(String name);

	public Long getTotalByLevel(Integer level);

	public List<EducationLevel> getAllDataOrderByLevel();

	public List<EducationLevel> getAllNameOrderByLevel();

	public EducationLevel getByGradeNumber(int number);

	public void saveAndMarge(EducationLevel entity);

	public Integer getCurrentMaxLevel();
	
	public EducationLevel getEntityByName(String name);

}
