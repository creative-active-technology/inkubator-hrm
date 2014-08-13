package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.BioIdCardDao;
import com.inkubator.hrm.entity.BioIdCard;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Taufik Hidayat
 */
@Repository(value = "bioIdCardDao")
@Lazy
public class BioIdCardDaoImpl extends IDAOImpl<BioIdCard> implements BioIdCardDao {

    @Override
    public Class<BioIdCard> getEntityClass() {
        return BioIdCard.class;
    }

    @Override
    public List<BioIdCard> getAllDataByBioDataId(Long bioDataId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("bioData.id", bioDataId));
        criteria.setFetchMode("city", FetchMode.JOIN);
        return criteria.list();
    }

    @Override
    public BioIdCard getEntityByPKWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("city", FetchMode.JOIN);
        return (BioIdCard) criteria.uniqueResult();
    }

    @Override
    public Long getTotalByCardNumber(String cardNumber) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("cardNumber", cardNumber));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByCardNumberAndNotId(String cardNumber, Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("cardNumber", cardNumber));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }
}
