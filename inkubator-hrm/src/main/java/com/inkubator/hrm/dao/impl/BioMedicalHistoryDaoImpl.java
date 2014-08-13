package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.BioMedicalHistoryDao;
import com.inkubator.hrm.entity.BioMedicalHistory;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Taufik Hidayat
 */
@Repository(value = "bioMedicalHistoryDao")
@Lazy
public class BioMedicalHistoryDaoImpl extends IDAOImpl<BioMedicalHistory> implements BioMedicalHistoryDao {

    @Override
    public Class<BioMedicalHistory> getEntityClass() {
        return BioMedicalHistory.class;
    }

    @Override
    public List<BioMedicalHistory> getAllDataByBioDataId(Long bioDataId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("bioData.id", bioDataId));
        return criteria.list();
    }

}
