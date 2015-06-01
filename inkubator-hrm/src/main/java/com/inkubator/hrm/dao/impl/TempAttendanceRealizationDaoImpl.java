package com.inkubator.hrm.dao.impl;
import com.inkubator.hrm.entity.TempAttendanceRealization;
import com.inkubator.datacore.dao.impl.IDAOImpl;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import com.inkubator.hrm.dao.TempAttendanceRealizationDao;
import com.inkubator.hrm.web.search.TempAttendanceRealizationSearchParameter;
/**
 *
 * @author WebGenX
 */
@Repository(value = "tempAttendanceRealizationDao")
@Lazy
public class TempAttendanceRealizationDaoImpl extends IDAOImpl<TempAttendanceRealization> implements TempAttendanceRealizationDao {
@Override
public Class<TempAttendanceRealization> getEntityClass() {
return TempAttendanceRealization.class;
}
@Override
public List<TempAttendanceRealization> getByParam(TempAttendanceRealizationSearchParameter parameter, int firstResult, int maxResults, Order order){
Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
doSearchTempAttendanceRealizationByParam(parameter, criteria);
criteria.addOrder(order);
criteria.setFirstResult(firstResult);
criteria.setMaxResults(maxResults);
return criteria.list();
}


@Override
public Long getTotalTempAttendanceRealizationByParam(TempAttendanceRealizationSearchParameter parameter){
Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
doSearchTempAttendanceRealizationByParam(parameter, criteria);
return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
}


private void doSearchTempAttendanceRealizationByParam(TempAttendanceRealizationSearchParameter parameter, Criteria criteria){
if (parameter.getCreatedBy() != null){
criteria.add(Restrictions.like("createdBy", parameter.getCreatedBy(), MatchMode.ANYWHERE));
}
criteria.add(Restrictions.isNotNull("id"));
}
}
