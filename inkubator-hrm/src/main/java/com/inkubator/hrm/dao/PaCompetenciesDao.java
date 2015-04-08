package com.inkubator.hrm.dao;
import com.inkubator.hrm.entity.PaCompetencies;
import com.inkubator.datacore.dao.IDAO;
import java.util.List;
import org.hibernate.criterion.Order;
import com.inkubator.hrm.web.search.PaCompetenciesSearchParameter;
/**
 *
 * @author WebGenX
 */
public interface PaCompetenciesDao extends IDAO<PaCompetencies>{
public List<PaCompetencies> getByParam(PaCompetenciesSearchParameter searchParameter, int firstResult, int maxResults, Order order);
public Long getTotalPaCompetenciesByParam(PaCompetenciesSearchParameter searchParameter);
}
