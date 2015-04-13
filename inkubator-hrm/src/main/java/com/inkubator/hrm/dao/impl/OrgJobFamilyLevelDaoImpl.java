package com.inkubator.hrm.dao.impl;
import com.inkubator.hrm.entity.OrgJobFamilyLevel;
import com.inkubator.datacore.dao.impl.IDAOImpl;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import com.inkubator.hrm.dao.OrgJobFamilyLevelDao;
import com.inkubator.hrm.web.search.OrgJobFamilyLevelSearchParameter;
/**
 *
 * @author WebGenX
 */
@Repository(value = "orgJobFamilyLevelDao")
@Lazy
public class OrgJobFamilyLevelDaoImpl extends IDAOImpl<OrgJobFamilyLevel> implements OrgJobFamilyLevelDao {
@Override
public Class<OrgJobFamilyLevel> getEntityClass() {
return OrgJobFamilyLevel.class;
}
@Override
public List<OrgJobFamilyLevel> getByParam(OrgJobFamilyLevelSearchParameter parameter, int firstResult, int maxResults, Order order){
Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
doSearchOrgJobFamilyLevelByParam(parameter, criteria);
criteria.addOrder(order);
criteria.setFirstResult(firstResult);
criteria.setMaxResults(maxResults);
return criteria.list();
}


@Override
public Long getTotalOrgJobFamilyLevelByParam(OrgJobFamilyLevelSearchParameter parameter){
Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
doSearchOrgJobFamilyLevelByParam(parameter, criteria);
return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
}


private void doSearchOrgJobFamilyLevelByParam(OrgJobFamilyLevelSearchParameter parameter, Criteria criteria){
if (parameter.getCreatedBy() != null){
criteria.add(Restrictions.like("createdBy", parameter.getCreatedBy(), MatchMode.ANYWHERE));
}
criteria.add(Restrictions.isNotNull("id"));
}
}
