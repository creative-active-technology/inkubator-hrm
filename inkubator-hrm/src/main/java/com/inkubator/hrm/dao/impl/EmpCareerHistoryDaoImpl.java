/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.EmpCareerHistoryDao;
import com.inkubator.hrm.entity.EmpCareerHistory;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni Husni FR
 */
@Repository(value = "empCareerHistoryDao")
@Lazy
public class EmpCareerHistoryDaoImpl extends IDAOImpl<EmpCareerHistory> implements EmpCareerHistoryDao {

    @Override
    public Class<EmpCareerHistory> getEntityClass() {
        return EmpCareerHistory.class;
    }

    @Override
    public List<EmpCareerHistory> getEmployeeCareerByBioId(long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("bioData", "bio", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("bio.id", id));
        criteria.setFetchMode("jabatan", FetchMode.JOIN);
        criteria.setFetchMode("golonganJabatan", FetchMode.JOIN);
        criteria.setFetchMode("golonganJabatan.pangkat", FetchMode.JOIN);
        criteria.addOrder(Order.asc("tglPenganngkatan"));
        return criteria.list();
    }

    @Override
    public EmpCareerHistory getByBioIdandStatus(long id, String status) {
        DetachedCriteria maxEvaluationScore = DetachedCriteria.forClass(getEntityClass())
                .setProjection(Property.forName("createdOn").max())
                .createAlias("bioData", "bio", JoinType.INNER_JOIN)
                .add(Restrictions.eq("bio.id", id))
                .add(Restrictions.eq("status", status));

        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("bioData", "bio", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("bio.id", id));
        criteria.add(Restrictions.eq("status", status));
        criteria.add(Property.forName("createdOn").eq(maxEvaluationScore));
        return (EmpCareerHistory) criteria.uniqueResult();
    }

}
