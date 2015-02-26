package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.LogMonthEndTaxes;
import com.inkubator.hrm.web.model.PphReportModel;
import com.inkubator.hrm.web.search.LogMonthEndTaxesSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
*
* @author rizkykojek
*/
public interface LogMonthEndTaxesDao extends IDAO<LogMonthEndTaxes> {

	public void deleteByPeriodId(Long periodId);
        
        public List<PphReportModel> getAllDataByParam(LogMonthEndTaxesSearchParameter searchParameter, int firstResult, int maxResults, Order order);
	
}
