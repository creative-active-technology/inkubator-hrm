/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.DepartementUnitLocationDao;
import com.inkubator.hrm.entity.DepartementUnitLocation;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 *
 * @author deni.fahri
 */
@Service(value = "departementUnitLocationDao")
@Lazy
public class DepartementUnitLocationDaoImpl extends IDAOImpl<DepartementUnitLocation> implements DepartementUnitLocationDao {

    @Override
    public Class<DepartementUnitLocation> getEntityClass() {
        return DepartementUnitLocation.class;
    }

    @Override
    public List<DepartementUnitLocation> getByDepartementId(long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("department", "dp");
        criteria.add(Restrictions.eq("dp.id", id));
        criteria.addOrder(Order.desc("unitKerja"));
        criteria.setFetchMode("unitKerja", FetchMode.JOIN);
        return criteria.list();
    }

}
