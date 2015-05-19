package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.LogMonthEndTaxes;
import com.inkubator.hrm.web.model.PphReportModel;
import com.inkubator.hrm.web.search.LogMonthEndTaxesSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;
import org.primefaces.model.StreamedContent;

/**
*
* @author rizkykojek
*/
public interface LogMonthEndTaxesService extends IService<LogMonthEndTaxes> {

	public void deleteByPeriodId(Long periodId) throws Exception;
	
        public List<PphReportModel> getAllDataByParam(LogMonthEndTaxesSearchParameter searchParameter, int firstResult, int maxResults, Order order);
    
        public Long getTotalDataByParam(LogMonthEndTaxesSearchParameter searchParameter) throws Exception;
        
        public StreamedContent generatedPph(long id) throws Exception;
}
