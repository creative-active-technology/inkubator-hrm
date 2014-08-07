/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.EducationHistoryDao;
import com.inkubator.hrm.entity.BioEducationHistory;
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
@Repository(value = "educationHistoryDao")
@Lazy
public class EducationHistoryDaoImpl extends IDAOImpl<BioEducationHistory> implements EducationHistoryDao{

    @Override
    public Class<BioEducationHistory> getEntityClass() {
        return BioEducationHistory.class;
    }

    @Override
    public BioEducationHistory getAllDataByPK(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("biodata", FetchMode.JOIN);
        criteria.setFetchMode("educationLevel", FetchMode.JOIN);
        criteria.setFetchMode("institutionEducation.city.cityName", FetchMode.JOIN);
        criteria.setFetchMode("faculty", FetchMode.JOIN);
        criteria.setFetchMode("major", FetchMode.JOIN);
        return (BioEducationHistory) criteria.uniqueResult();
    }

    @Override
    public List<BioEducationHistory> getAllDataByBioDataId(Long bioDataId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("biodata.id", bioDataId));
        criteria.setFetchMode("educationLevel", FetchMode.JOIN);
        criteria.setFetchMode("institutionEducation", FetchMode.JOIN);
        criteria.setFetchMode("city", FetchMode.JOIN);
        criteria.setFetchMode("faculty", FetchMode.JOIN);
        criteria.setFetchMode("major", FetchMode.JOIN);
        return criteria.list();
    }
    
}
