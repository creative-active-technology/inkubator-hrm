package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.OrganizationLetter;

/**
*
* @author rizkykojek
*/
public interface OrganizationLetterDao extends IDAO<OrganizationLetter> {

	public List<OrganizationLetter> getByParam(String parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalByParam(String parameter);
	
	public Long getTotalByLetterNumber(String letterNumber);
	
	public Long getTotalByLetterNumberAndNotId(String letterNumber, Long id);

}
