package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.LeaveScheme;
import com.inkubator.hrm.web.search.LeaveSchemeSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface LeaveSchemeDao extends IDAO<LeaveScheme> {

	public List<LeaveScheme> getByParam(LeaveSchemeSearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalByParam(LeaveSchemeSearchParameter parameter);
	
	public Long getTotalByName(String name);
	
	public Long getTotalByNameAndNotId(String name, Long id);
	
	public Long getTotalByCode(String code);
	
	public Long getTotalByCodeAndNotId(String code, Long id);
	
	
}
