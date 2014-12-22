package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.LogMonthEndPayrollDao;
import com.inkubator.hrm.entity.LogMonthEndPayroll;
import com.inkubator.hrm.web.search.LogMonthEndPayrollSearchParameter;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "logMonthEndPayrollDao")
@Lazy
public class LogMonthEndPayrollDaoImpl extends IDAOImpl<LogMonthEndPayroll> implements LogMonthEndPayrollDao {

    @Override
    public Class<LogMonthEndPayroll> getEntityClass() {
        return LogMonthEndPayroll.class;
    }

    @Override
    public List<LogMonthEndPayroll> getByParam(LogMonthEndPayrollSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalByParam(LogMonthEndPayrollSearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchByParam(LogMonthEndPayrollSearchParameter parameter, Criteria criteria) {
        if (StringUtils.isNotEmpty(parameter.getName())) {
            criteria.add(Restrictions.like("name", parameter.getName(), MatchMode.START));
        }
        if (StringUtils.isNotEmpty(parameter.getNik())) {
            criteria.add(Restrictions.like("nik", parameter.getNik(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }
    
}
