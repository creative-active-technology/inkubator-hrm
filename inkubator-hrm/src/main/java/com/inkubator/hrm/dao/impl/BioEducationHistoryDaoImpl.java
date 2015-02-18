/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.BioEducationHistoryDao;
import com.inkubator.hrm.entity.BioEducationHistory;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni
 */
@Repository(value = "bioEducationHistoryDao")
@Lazy
public class BioEducationHistoryDaoImpl extends IDAOImpl<BioEducationHistory> implements BioEducationHistoryDao {

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
        criteria.setFetchMode("city", FetchMode.JOIN);
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

    @Override
    public Long getTotalByGenderMaleAndEducationLevel(Long educationId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("biodata", "biodata", JoinType.INNER_JOIN);
        criteria.createAlias("educationLevel", "educationLevel", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("biodata.gender", HRMConstant.GLOBAL_MALE));
        criteria.add(Restrictions.eq("educationLevel.id", educationId));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByGenderFemaleAndEducationLevel(Long educationId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("biodata", "biodata", JoinType.INNER_JOIN);
        criteria.createAlias("educationLevel", "educationLevel", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("biodata.gender", HRMConstant.GLOBAL_FEMALE));
        criteria.add(Restrictions.eq("educationLevel.id", educationId));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public List<BioEducationHistory> getByBioDataIdAndEducationLevelId(Long bioDataid) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("biodata", "biodata", JoinType.INNER_JOIN);
        criteria.createAlias("educationLevel", "educationLevel", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("biodata.id", bioDataid));
        criteria.addOrder(Order.desc("educationLevel.level"));
        return criteria.list();
    }

}
