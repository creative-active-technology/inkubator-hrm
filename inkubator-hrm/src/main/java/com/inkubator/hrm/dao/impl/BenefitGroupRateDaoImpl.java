package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.BenefitGroupRateDao;
import com.inkubator.hrm.entity.BenefitGroupRate;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Taufik Hidayat
 */
@Repository(value = "benefitGroupRateDao")
@Lazy
public class BenefitGroupRateDaoImpl extends IDAOImpl<BenefitGroupRate> implements BenefitGroupRateDao {

    @Override
    public Class<BenefitGroupRate> getEntityClass() {
        return BenefitGroupRate.class;
    }

    @Override
    public List<BenefitGroupRate> getAllDataByBenefitGroupId(Long benefitGroupId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("benefitGroup.id", benefitGroupId));
        criteria.setFetchMode("golonganJabatan", FetchMode.JOIN);
        return criteria.list();
    }

    @Override
    public BenefitGroupRate getEntityByPKWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("golonganJabatan", FetchMode.JOIN);
        return (BenefitGroupRate) criteria.uniqueResult();
    }

    @Override
    public Long getTotalByBenefitGroupAndGolonganJabatan(Long benefitId, Long golonganId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("benefitGroup", "b", JoinType.INNER_JOIN);
        criteria.createAlias("golonganJabatan", "g", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("b.id", benefitId));
        criteria.add(Restrictions.eq("g.id", golonganId));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByBenefitGroupAndGolonganJabatanAndNotId(Long benefitId, Long golonganId, Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("benefitGroup", "b", JoinType.INNER_JOIN);
        criteria.createAlias("golonganJabatan", "g", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("b.id", benefitId));
        criteria.add(Restrictions.eq("g.id", golonganId));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }
    
    @Override
    public List<BenefitGroupRate> getByGolonganJabatan(Long golonganId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("golonganJabatan", "g", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("g.id", golonganId));
        criteria.setFetchMode("golonganJabatan", FetchMode.JOIN);
        criteria.setFetchMode("benefitGroup", FetchMode.JOIN);
        return criteria.list();
    }
}
