package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.DiseaseDao;
import com.inkubator.hrm.entity.Disease;
import com.inkubator.hrm.web.search.DiseaseSearchParameter;
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
@Repository(value = "diseaseDao")
@Lazy
public class DiseaseDaoImpl extends IDAOImpl<Disease> implements DiseaseDao {

    @Override
    public Class<Disease> getEntityClass() {
        return Disease.class;
    }

    @Override
    public List<Disease> getByParam(DiseaseSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchDiseaseByParam(parameter, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalDiseaseByParam(DiseaseSearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchDiseaseByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchDiseaseByParam(DiseaseSearchParameter parameter, Criteria criteria) {
        if (parameter.getCode()!= null) {
            criteria.add(Restrictions.like("code", parameter.getCode(), MatchMode.ANYWHERE));
        }
        
        if (parameter.getName()!= null) {
            criteria.add(Restrictions.like("name", parameter.getName(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

}
