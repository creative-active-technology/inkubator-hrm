package com.inkubator.hrm.dao;
import com.inkubator.hrm.entity.PaCompetencyPoint;
import com.inkubator.datacore.dao.IDAO;
import java.util.List;
import org.hibernate.criterion.Order;
import com.inkubator.hrm.web.search.PaCompetencyPointSearchParameter;
/**
 *
 * @author WebGenX
 */
public interface PaCompetencyPointDao extends IDAO<PaCompetencyPoint>{
public List<PaCompetencyPoint> getByParam(PaCompetencyPointSearchParameter searchParameter, int firstResult, int maxResults, Order order);
public Long getTotalPaCompetencyPointByParam(PaCompetencyPointSearchParameter searchParameter);
}
