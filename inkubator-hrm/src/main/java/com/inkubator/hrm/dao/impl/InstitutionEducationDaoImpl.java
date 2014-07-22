package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.InstitutionEducationDao;
import com.inkubator.hrm.entity.InstitutionEducation;
import com.inkubator.hrm.web.search.InstitutionEducationSearchParameter;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Taufik Hidayat
 */
@Repository(value = "institutionEducationDao")
@Lazy
public class InstitutionEducationDaoImpl extends IDAOImpl<InstitutionEducation> implements InstitutionEducationDao {

    @Override
    public Class<InstitutionEducation> getEntityClass() {
        return InstitutionEducation.class;
    }

    @Override
    public List<InstitutionEducation> getByParam(InstitutionEducationSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchInstitutionEducationByParam(parameter, criteria);
        criteria.setFetchMode("city", FetchMode.JOIN);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalInstitutionEducationByParam(InstitutionEducationSearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchInstitutionEducationByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchInstitutionEducationByParam(InstitutionEducationSearchParameter parameter, Criteria criteria) {
        if (parameter.getInstitutionEducationCode() != null) {
            criteria.add(Restrictions.like("institutionEducationCode", parameter.getInstitutionEducationCode(), MatchMode.ANYWHERE));
        }
        
        if (parameter.getInstitutionEducationName()!= null) {
            criteria.add(Restrictions.like("institutionEducationName", parameter.getInstitutionEducationName(), MatchMode.ANYWHERE));
        }
        
        if (parameter.getCityName()!= null) {
            criteria.createAlias("city", "c", JoinType.INNER_JOIN);
            criteria.add(Restrictions.like("c.cityName", parameter.getCityName(), MatchMode.ANYWHERE));
        }
        
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public Long getTotalByCode(String code) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("institutionEducationCode", code));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByCodeAndNotId(String code, Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("institutionEducationCode", code));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }
    
    @Override
    public InstitutionEducation getInstitutionEducationByIdWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("city", FetchMode.JOIN);
        criteria.setFetchMode("city.province", FetchMode.JOIN);
        criteria.setFetchMode("province.country", FetchMode.JOIN);
        return (InstitutionEducation) criteria.uniqueResult();
    }

}
