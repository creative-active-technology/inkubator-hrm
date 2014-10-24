/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.MecineFingerDao;
import com.inkubator.hrm.entity.MecineFinger;
import com.inkubator.hrm.web.search.MecineFingerSearchParameter;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni Husni FR
 */
@Repository(value = "mecineFingerDao")
@Lazy
public class MecineFingerDaoImpl extends IDAOImpl<MecineFinger> implements MecineFingerDao {

    @Override
    public Class<MecineFinger> getEntityClass() {
        return MecineFinger.class;
    }

    @Override
    public List<MecineFinger> getByParam(MecineFingerSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchMecineFingerByParam(parameter, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalByParam(MecineFingerSearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchMecineFingerByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();

    }

    private void doSearchMecineFingerByParam(MecineFingerSearchParameter parameter, Criteria criteria) {

        if (parameter.getCode() != null) {
            criteria.add(Restrictions.like("code", parameter.getCode(), MatchMode.ANYWHERE));
        }
        if (parameter.getName() != null) {
            criteria.add(Restrictions.like("name", parameter.getName(), MatchMode.ANYWHERE));
        }

        if (parameter.getMethodType() != null) {
            criteria.add(Restrictions.like("methodType", parameter.getMethodType(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public MecineFinger getMecineFingerAndDetaiUploadByFK(long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("macineFingerUploads", FetchMode.JOIN);
        criteria.setFetchMode("departementUploadCaptures", FetchMode.JOIN);
        return (MecineFinger) criteria.uniqueResult();
    }

    @Override
    public void saveAndMerge(MecineFinger finger) {
        getCurrentSession().update(finger);
        getCurrentSession().flush();
    }

    public Long getByCode(String code) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.like("code", code, MatchMode.ANYWHERE));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

}
