/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.BioSpesifikasiAbilityDao;
import com.inkubator.hrm.entity.BioSpesifikasiAbility;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni
 */
@Repository(value = "bioSpesifikasiAbility")
@Lazy
public class BioSpesifikasiAbilityDaoImpl extends IDAOImpl<BioSpesifikasiAbility> implements BioSpesifikasiAbilityDao{

    @Override
    public Class<BioSpesifikasiAbility> getEntityClass() {
        return BioSpesifikasiAbility.class;
    }

    @Override
    public BioSpesifikasiAbility getAllDataByPK(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("biodata", FetchMode.JOIN);
        criteria.setFetchMode("specificationAbility", FetchMode.JOIN);
        return (BioSpesifikasiAbility) criteria.uniqueResult();
    }

    @Override
    public List<BioSpesifikasiAbility> getAllDataByBiodataId(Long bioDataId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("biodata.id", bioDataId));
        criteria.setFetchMode("specificationAbility", FetchMode.JOIN);
        return criteria.list();
    }
    
}
