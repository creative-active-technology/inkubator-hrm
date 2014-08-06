/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.PeopleInterestDao;
import com.inkubator.hrm.entity.BioPeopleInterest;
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
@Repository(value = "peopleInterestDao")
@Lazy
public class PeopleInterestDaoImpl extends IDAOImpl<BioPeopleInterest> implements PeopleInterestDao{

    @Override
    public Class<BioPeopleInterest> getEntityClass() {
        return BioPeopleInterest.class;
    }

    @Override
    public BioPeopleInterest getAllDataByPK(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("biodata", FetchMode.JOIN);
        criteria.setFetchMode("interestType", FetchMode.JOIN);
        return (BioPeopleInterest) criteria.uniqueResult();
    }

    @Override
    public List<BioPeopleInterest> getAllDataByBioDataId(Long bioDataId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("biodata.id", bioDataId));
        criteria.setFetchMode("interestType", FetchMode.JOIN);
        return criteria.list();
    }
    
}
