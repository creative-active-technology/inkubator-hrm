package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.PaySalaryJurnalDao;
import com.inkubator.hrm.entity.PaySalaryJurnal;
import com.inkubator.hrm.web.search.PaySalaryJurnalSearchParameter;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Taufik Hidayat
 */
@Repository(value = "paySalaryJurnalDao")
@Lazy
public class PaySalaryJurnalDaoImpl extends IDAOImpl<PaySalaryJurnal> implements PaySalaryJurnalDao {

    @Override
    public Class<PaySalaryJurnal> getEntityClass() {
        return PaySalaryJurnal.class;
    }

    @Override
    public List<PaySalaryJurnal> getByParam(PaySalaryJurnalSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchPaySalaryJurnalByParam(parameter, criteria);
        criteria.setFetchMode("costCenter", FetchMode.JOIN);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalPaySalaryJurnalByParam(PaySalaryJurnalSearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchPaySalaryJurnalByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchPaySalaryJurnalByParam(PaySalaryJurnalSearchParameter parameter, Criteria criteria) {
        if (parameter.getCode()!= null) {
            criteria.add(Restrictions.like("code", parameter.getCode(), MatchMode.ANYWHERE));
        }
        
        if (parameter.getName()!= null) {
            criteria.add(Restrictions.like("name", parameter.getName(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }
    
    @Override
    public PaySalaryJurnal getEntityByPKWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("costCenter", FetchMode.JOIN);
        return (PaySalaryJurnal) criteria.uniqueResult();
    }

}
