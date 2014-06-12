/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RiwayatAksesDao;
import com.inkubator.hrm.entity.RiwayatAkses;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni Husni FR
 */
@Repository(value = "riwayatAksesDao")
@Lazy
public class RiwayatAksesDaoImpl extends IDAOImpl<RiwayatAkses> implements RiwayatAksesDao {

    @Override
    public Class<RiwayatAkses> getEntityClass() {
        return RiwayatAkses.class;
    }

  
    @Override
    public List<RiwayatAkses> getDataByUserId(String userID, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("userId", userID));
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

}
