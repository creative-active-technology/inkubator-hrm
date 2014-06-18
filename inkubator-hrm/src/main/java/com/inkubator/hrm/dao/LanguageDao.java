package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.LanguageUsed;

/**
*
* @author Taufik hidayat
*/
public interface LanguageDao extends IDAO<LanguageUsed> {

	public List<LanguageUsed> getByParam(String parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalLanguageByParam(String parameter);
	
	public Long getTotalByName(String name);
	
	public Long getTotalByNameAndNotId(String name, Long id);

}
