package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.BioEmploymentHistoryDao;
import com.inkubator.hrm.entity.BioEmploymentHistory;
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
@Repository(value = "bioEmploymentHistoryDao")
@Lazy
public class BioEmploymentHistoryDaoImpl extends IDAOImpl<BioEmploymentHistory> implements BioEmploymentHistoryDao {

    @Override
    public Class<BioEmploymentHistory> getEntityClass() {
        return BioEmploymentHistory.class;
    }

    @Override
    public List<BioEmploymentHistory> getAllDataByBioDataId(Long bioDataId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("bioData.id", bioDataId));
        criteria.setFetchMode("city", FetchMode.JOIN);
        return criteria.list();
    }

    @Override
    public BioEmploymentHistory getEntityByPKWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("city", FetchMode.JOIN);
        return (BioEmploymentHistory) criteria.uniqueResult();
    }
}
