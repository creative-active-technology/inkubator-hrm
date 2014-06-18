package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.Language;

/**
*
* @author Taufik Hidayat
*/
public interface LanguageDao extends IDAO<Language> {

	public List<Language> getByParam(String parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalLanguageByParam(String parameter);
	
	public Long getTotalByName(String name);
	
	public Long getTotalByNameAndNotId(String name, Long id);

}
