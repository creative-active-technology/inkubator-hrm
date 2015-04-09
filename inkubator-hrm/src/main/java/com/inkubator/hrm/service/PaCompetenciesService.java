package com.inkubator.hrm.service;
import com.inkubator.hrm.entity.PaCompetencies;
import java.util.List;
import org.hibernate.criterion.Order;
import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.web.search.PaCompetenciesSearchParameter;
/**
 *
 * @author WebGenX
 */
public interface PaCompetenciesService extends IService<PaCompetencies>{
public List<PaCompetencies> getByParam(PaCompetenciesSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception ;
public Long getTotalPaCompetenciesByParam(PaCompetenciesSearchParameter searchParameter) throws Exception;
}
