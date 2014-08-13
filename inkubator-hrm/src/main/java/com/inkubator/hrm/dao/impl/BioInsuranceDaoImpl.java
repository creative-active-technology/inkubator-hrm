package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.BioInsuranceDao;
import com.inkubator.hrm.entity.BioInsurance;
import com.inkubator.hrm.web.search.BioInsuranceSearchParameter;
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
@Repository(value = "bioInsuranceDao")
@Lazy
public class BioInsuranceDaoImpl extends IDAOImpl<BioInsurance> implements BioInsuranceDao {

    @Override
    public Class<BioInsurance> getEntityClass() {
        return BioInsurance.class;
    }

    @Override
    public List<BioInsurance> getAllDataByBioDataId(Long bioDataId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("bioData.id", bioDataId));
        return criteria.list();
    }

    @Override
    public Long getTotalByNoPolicy(String noPolicy) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("noPolicy", noPolicy));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByNoPolicyAndNotId(String noPolicy, Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("noPolicy", noPolicy));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

}
