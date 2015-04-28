package com.inkubator.hrm.dao.impl;
import com.inkubator.hrm.entity.SystemLetterReference;
import com.inkubator.datacore.dao.impl.IDAOImpl;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import com.inkubator.hrm.dao.SystemLetterReferenceDao;
import com.inkubator.hrm.web.search.SystemLetterReferenceSearchParameter;
/**
 *
 * @author WebGenX
 */
@Repository(value = "systemLetterReferenceDao")
@Lazy
public class SystemLetterReferenceDaoImpl extends IDAOImpl<SystemLetterReference> implements SystemLetterReferenceDao {
@Override
public Class<SystemLetterReference> getEntityClass() {
return SystemLetterReference.class;
}
@Override
public List<SystemLetterReference> getByParam(SystemLetterReferenceSearchParameter parameter, int firstResult, int maxResults, Order order){
Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
doSearchSystemLetterReferenceByParam(parameter, criteria);
criteria.addOrder(order);
criteria.setFirstResult(firstResult);
criteria.setMaxResults(maxResults);
return criteria.list();
}


@Override
public Long getTotalSystemLetterReferenceByParam(SystemLetterReferenceSearchParameter parameter){
Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
doSearchSystemLetterReferenceByParam(parameter, criteria);
return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
}


private void doSearchSystemLetterReferenceByParam(SystemLetterReferenceSearchParameter parameter, Criteria criteria){
if (parameter.getLetterSumary() != null){
criteria.add(Restrictions.like("letterSumary", parameter.getLetterSumary(), MatchMode.ANYWHERE));
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
