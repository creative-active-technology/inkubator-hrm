package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.BioFamilyRelationshipDao;
import com.inkubator.hrm.entity.BioFamilyRelationship;
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
@Repository(value = "bioFamilyRelationshipDao")
@Lazy
public class BioFamilyRelationshipDaoImpl extends IDAOImpl<BioFamilyRelationship> implements BioFamilyRelationshipDao {

    @Override
    public Class<BioFamilyRelationship> getEntityClass() {
        return BioFamilyRelationship.class;
    }

    @Override
    public List<BioFamilyRelationship> getAllDataByBioDataId(Long bioDataId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("bioData.id", bioDataId));
        criteria.setFetchMode("familyRelation", FetchMode.JOIN);
        criteria.setFetchMode("educationLevel", FetchMode.JOIN);
        return criteria.list();
    }

    @Override
    public BioFamilyRelationship getEntityByPKWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("familyRelation", FetchMode.JOIN);
        criteria.setFetchMode("educationLevel", FetchMode.JOIN);
        return (BioFamilyRelationship) criteria.uniqueResult();
    }
    
}
