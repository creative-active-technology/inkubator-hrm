package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.BioProjectDao;
import com.inkubator.hrm.entity.BioProject;
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
@Repository(value = "bioProjectDao")
@Lazy
public class BioProjectDaoImpl extends IDAOImpl<BioProject> implements BioProjectDao {

    @Override
    public Class<BioProject> getEntityClass() {
        return BioProject.class;
    }

    @Override
    public List<BioProject> getAllDataByBioDataId(Long bioDataId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("bioData.id", bioDataId));
        return criteria.list();
    }

    @Override
    public BioProject getEntityByPKWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        return (BioProject) criteria.uniqueResult();
    }
}
