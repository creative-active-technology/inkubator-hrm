/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.KlasifikasiKerjaJabatanDao;
import com.inkubator.hrm.entity.KlasifikasiKerjaJabatan;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni Husni FR
 */

@Repository(value = "klasifikasiKerjaJabatanDao")
public class KlasifikasiKerjaJabatanDaoImpl extends IDAOImpl<KlasifikasiKerjaJabatan> implements KlasifikasiKerjaJabatanDao {

    @Override
    public Class<KlasifikasiKerjaJabatan> getEntityClass() {
        return KlasifikasiKerjaJabatan.class;
    }

    @Override
    public List<KlasifikasiKerjaJabatan> getByJabatanId(long id) {
         Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("jabatan", "jb");
        criteria.add(Restrictions.eq("jb.id", id));
        criteria.addOrder(Order.desc("klasifikasiKerja"));
        criteria.setFetchMode("klasifikasiKerja", FetchMode.JOIN);
        return criteria.list();
    }

}
