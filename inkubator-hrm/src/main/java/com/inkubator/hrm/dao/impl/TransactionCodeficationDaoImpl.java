package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.TransactionCodeficationDao;
import com.inkubator.hrm.entity.TransactionCodefication;
import com.inkubator.hrm.web.search.TransactionCodeficationSearchParameter;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
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
@Repository(value = "transactionCodeficationDao")
@Lazy
public class TransactionCodeficationDaoImpl extends IDAOImpl<TransactionCodefication> implements TransactionCodeficationDao {

    @Override
    public Class<TransactionCodefication> getEntityClass() {
        return TransactionCodefication.class;
    }

    @Override
    public List<TransactionCodefication> getByParam(TransactionCodeficationSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchTransactionCodeficationByParam(parameter, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }
    
    @Override
    public TransactionCodefication getEntityByModulCode(String modulCode) {
         Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
         criteria.add(Restrictions.eq("moduleCode", modulCode));
         return (TransactionCodefication) criteria.uniqueResult();
    }

    @Override
    public Long getTotalTransactionCodeficationByParam(TransactionCodeficationSearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchTransactionCodeficationByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchTransactionCodeficationByParam(TransactionCodeficationSearchParameter parameter, Criteria criteria) {
        if (parameter.getCode()!= null) {
            criteria.add(Restrictions.like("code", parameter.getCode(), MatchMode.ANYWHERE));
        }
        
        if (parameter.getName()!= null) {
            criteria.add(Restrictions.like("name", parameter.getName(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    

}
