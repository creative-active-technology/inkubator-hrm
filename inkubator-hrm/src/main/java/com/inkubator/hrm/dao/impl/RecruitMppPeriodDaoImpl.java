package com.inkubator.hrm.dao.impl;
import com.inkubator.hrm.entity.RecruitMppPeriod;
import com.inkubator.datacore.dao.impl.IDAOImpl;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import com.inkubator.hrm.dao.RecruitMppPeriodDao;
import com.inkubator.hrm.web.search.RecruitMppPeriodSearchParameter;
/**
 *
 * @author WebGenX
 */
@Repository(value = "recruitMppPeriodDao")
@Lazy
public class RecruitMppPeriodDaoImpl extends IDAOImpl<RecruitMppPeriod> implements RecruitMppPeriodDao {
@Override
public Class<RecruitMppPeriod> getEntityClass() {
return RecruitMppPeriod.class;
}
@Override
public List<RecruitMppPeriod> getByParam(RecruitMppPeriodSearchParameter parameter, int firstResult, int maxResults, Order order){
Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
doSearchRecruitMppPeriodByParam(parameter, criteria);
criteria.addOrder(order);
criteria.setFirstResult(firstResult);
criteria.setMaxResults(maxResults);
return criteria.list();
}


@Override
public Long getTotalRecruitMppPeriodByParam(RecruitMppPeriodSearchParameter parameter){
Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
doSearchRecruitMppPeriodByParam(parameter, criteria);
return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
}


private void doSearchRecruitMppPeriodByParam(RecruitMppPeriodSearchParameter parameter, Criteria criteria){
if (parameter.getName() != null){
criteria.add(Restrictions.like("name", parameter.getName(), MatchMode.ANYWHERE));
}
if (parameter.getCode() != null){
criteria.add(Restrictions.like("code", parameter.getCode(), MatchMode.ANYWHERE));
}
criteria.add(Restrictions.isNotNull("id"));
}
}
