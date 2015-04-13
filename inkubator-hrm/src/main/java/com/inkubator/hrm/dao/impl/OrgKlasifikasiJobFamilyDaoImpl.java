/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.OrgKlasifikasiJobFamilyDao;
import com.inkubator.hrm.entity.OrgKlasifikasiJobFamily;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni
 */
@Repository(value = "orgKlasifikasiJobFamilyDao")
@Lazy
public class OrgKlasifikasiJobFamilyDaoImpl extends IDAOImpl<OrgKlasifikasiJobFamily> implements OrgKlasifikasiJobFamilyDao {

    @Override
    public Class<OrgKlasifikasiJobFamily> getEntityClass() {
        return OrgKlasifikasiJobFamily.class;
    }

    @Override
    public List<OrgKlasifikasiJobFamily> getByKlasifikasiId(long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("klasifikasiKerja", "kk");
        criteria.add(Restrictions.eq("kk.id", id));
        criteria.addOrder(Order.desc("golonganJabatan"));
        criteria.setFetchMode("golonganJabatan", FetchMode.JOIN);
        return criteria.list();
    }
    
}
