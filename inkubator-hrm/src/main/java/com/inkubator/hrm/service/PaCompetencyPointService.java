package com.inkubator.hrm.service;
import com.inkubator.hrm.entity.PaCompetencyPoint;
import java.util.List;
import org.hibernate.criterion.Order;
import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.web.search.PaCompetencyPointSearchParameter;
/**
 *
 * @author WebGenX
 */
public interface PaCompetencyPointService extends IService<PaCompetencyPoint>{
public List<PaCompetencyPoint> getByParam(PaCompetencyPointSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception ;
public Long getTotalPaCompetencyPointByParam(PaCompetencyPointSearchParameter searchParameter) throws Exception;
}
