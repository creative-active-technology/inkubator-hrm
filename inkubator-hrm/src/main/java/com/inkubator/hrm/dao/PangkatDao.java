package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.Pangkat;
import com.inkubator.hrm.web.search.PangkatSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface PangkatDao extends IDAO<Pangkat> {

	public List<Pangkat> getByParam(PangkatSearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalByParam(PangkatSearchParameter parameter);
	
	public Long getTotalByPangkatName(String pangkatName);
	
	public Long getTotalByPangkatNameAndNotId(String pangkatName, Long id);
	
	public Long getTotalByPangkatCode(String pangkatCode);
	
	public Long getTotalByPangkatCodeAndNotId(String pangkatCode, Long id);
	
	
}
