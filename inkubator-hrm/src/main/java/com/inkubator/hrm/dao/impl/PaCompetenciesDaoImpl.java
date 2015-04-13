package com.inkubator.hrm.dao.impl;
import com.inkubator.hrm.entity.PaCompetencies;
import com.inkubator.datacore.dao.impl.IDAOImpl;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import com.inkubator.hrm.dao.PaCompetenciesDao;
import com.inkubator.hrm.web.search.PaCompetenciesSearchParameter;
/**
 *
 * @author WebGenX
 */
@Repository(value = "paCompetenciesDao")
@Lazy
public class PaCompetenciesDaoImpl extends IDAOImpl<PaCompetencies> implements PaCompetenciesDao {
@Override
public Class<PaCompetencies> getEntityClass() {
return PaCompetencies.class;
}
@Override
public List<PaCompetencies> getByParam(PaCompetenciesSearchParameter parameter, int firstResult, int maxResults, Order order){
Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
doSearchPaCompetenciesByParam(parameter, criteria);
criteria.addOrder(order);
criteria.setFirstResult(firstResult);
criteria.setMaxResults(maxResults);
return criteria.list();
}


@Override
public Long getTotalPaCompetenciesByParam(PaCompetenciesSearchParameter parameter){
Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
doSearchPaCompetenciesByParam(parameter, criteria);
return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
}


private void doSearchPaCompetenciesByParam(PaCompetenciesSearchParameter parameter, Criteria criteria){
if (parameter.getDescription() != null){
criteria.add(Restrictions.like("description", parameter.getDescription(), MatchMode.ANYWHERE));
}
if (parameter.getName() != null){
criteria.add(Restrictions.like("name", parameter.getName(), MatchMode.ANYWHERE));
}
if (parameter.getCode() != null){
criteria.add(Restrictions.like("code", parameter.getCode(), MatchMode.ANYWHERE));
}
criteria.add(Restrictions.isNotNull("id"));
}
}
