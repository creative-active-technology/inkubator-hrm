package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.WtWorkingHour;
import com.inkubator.hrm.web.search.WorkingHourSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author rizkykojek
 */
public interface WtWorkingHourDao extends IDAO<WtWorkingHour> {

	public List<WtWorkingHour> getByParam(WorkingHourSearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalByParam(WorkingHourSearchParameter parameter);
	
	public Long getTotalByName(String name);
	
	public Long getTotalByNameAndNotId(String name, Long id);
	
	public Long getTotalByCode(String code);
	
	public Long getTotalByCodeAndNotId(String code, Long id);
        
        public WtWorkingHour getByCode(String code);
	
	
}
